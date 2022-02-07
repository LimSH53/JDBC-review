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
		/* 반복
		 * ------------------------
		 * 1. 카테고리 조회
		 * 2. 해당 카테고리의 메뉴 조회
		 * 3. 사용자에게 어떤 메뉴를 주문 받을 것인지 입력
		 * 4. 주문할 수량 입력
		 * ------------------------
		 * 5. 주문 
		 * */
		
		Scanner sc = new Scanner(System.in);
		
		List<OrderMenuDTO> orderMenuList = new ArrayList<OrderMenuDTO>();
		int totalOrderPrice = 0;
		
		do {
			System.out.println("======== 음식 주문 프로그램 ========");
			
			List<CategoryDTO> categoryList = orderController.selectAllCategory();
			for(CategoryDTO category : categoryList) {
				System.out.println(category.getCategoryName());
			}
			
			System.out.println("==============================");
			System.out.println("주문하실 카테고리를 선택해주세요 : ");
			String inputCategory = sc.nextLine();
			
			int categoryCode = 0;
			for(CategoryDTO cateogry : categoryList) {
				if(cateogry.getCategoryName().equals(inputCategory)) {
					categoryCode = cateogry.getCategoryCode();
				}
			}
			
			System.out.println("====== 주문 가능 메뉴 ======");
			List<MenuDTO> menuList = orderController.selectMenuByCategory(categoryCode);
			for(MenuDTO menu : menuList) {
				System.out.println(menu);
			}
			
			System.out.println("주문하실 메뉴를 선택해주세요 : ");
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
			
			System.out.println("주문하실 수량을 입력하세요 : ");
			int orderAmount = sc.nextInt();
			OrderMenuDTO orderMenu = new OrderMenuDTO();
			orderMenu.setMenuCode(menuCode);
			orderMenu.setOrderAmount(orderAmount);
			
			orderMenuList.add(orderMenu);
			totalOrderPrice += (menuPrice * orderAmount);
			
			System.out.println("계속 주문하시겠습니까?(예/아니오) : ");
			sc.nextLine();
			boolean isContinue = sc.nextLine().equals("예") ? true : false;
			
			if(!isContinue) break;

		} while (true);
		
		Map<String, Object> requestMap = new HashMap<>();
		requestMap.put("totalOrderPrice", totalOrderPrice);
		requestMap.put("orderMenuList", orderMenuList);
		
		orderController.registOrder(requestMap);
		
	}

}
