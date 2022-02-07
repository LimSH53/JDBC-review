package com.greedy.section01.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.greedy.section01.controller.OrderController;
import com.greedy.section01.model.dto.MenuDTO;
import com.greedy.section01.model.dto.CategoryDTO;
import com.greedy.section01.model.dto.OrderMenuDTO;

public class OrderMenu {
	
	private OrderController orderController = new OrderController();

	public void displayMenu() {
		/* �ݺ�
		 * ------------------------
		 * 1. ī�װ� ��ȸ
		 * 2. �ش� ī�װ��� �޴� ��ȸ
		 * 3. ����ڿ��� � �޴��� �ֹ� ���� ������ �Է�
		 * 4. �ֹ��� ���� �Է�
		 * ------------------------
		 * 5. �ֹ� 
		 * */
		
		Scanner sc = new Scanner(System.in);
		
		List<OrderMenuDTO> orderMenuList = new ArrayList<OrderMenuDTO>();
		int totalOrderPrice = 0;
		
		do {
			System.out.println("======== ���� �ֹ� ���α׷� ========");
			
			List<CategoryDTO> categoryList = orderController.selectAllCategory();
			for(CategoryDTO category : categoryList) {
				System.out.println(category.getCategoryName());
			}
			
			System.out.println("==============================");
			System.out.println("�ֹ��Ͻ� ī�װ��� �������ּ��� : ");
			String inputCategory = sc.nextLine();
			
			int categoryCode = 0;
			for(CategoryDTO cateogry : categoryList) {
				if(cateogry.getCategoryName().equals(inputCategory)) {
					categoryCode = cateogry.getCategoryCode();
				}
			}
			
			System.out.println("====== �ֹ� ���� �޴� ======");
			List<MenuDTO> menuList = orderController.selectMenuByCategory(categoryCode);
			for(MenuDTO menu : menuList) {
				System.out.println(menu);
			}
			
			System.out.println("�ֹ��Ͻ� �޴��� �������ּ��� : ");
			String inputMenu = sc.nextLine();
			
			int menuCode = 0;
			int menuPrice = 0;
			
			for(int i = 0; i <menuList.size(); i++) {
				MenuDTO menu = menuList.get(i);
				if(menu.getName().equals(inputMenu)) {
					menuCode = menu.getCode();
					menuPrice = menu.getPrice();
				}
			}
			
			System.out.println("�ֹ��Ͻ� ������ �Է��ϼ��� : ");
			int orderAmount = sc.nextInt();
			OrderMenuDTO orderMenu = new OrderMenuDTO();
			orderMenu.setMenuCode(menuCode);
			orderMenu.setOrderAmount(orderAmount);
			
			orderMenuList.add(orderMenu);
			totalOrderPrice += (menuPrice * orderAmount);
			
			System.out.println("��� �ֹ��Ͻðڽ��ϱ�?(��/�ƴϿ�) : ");
			sc.nextLine();
			boolean isContinue = sc.nextLine().equals("��") ? true : false;
			
			if(!isContinue) break;

		} while (true);
		
		Map<String, Object> requestMap = new HashMap<>();
		requestMap.put("totalOrderPrice", totalOrderPrice);
		requestMap.put("orderMenuList", orderMenuList);
		
		orderController.registOrder(requestMap);
		
	}

}
