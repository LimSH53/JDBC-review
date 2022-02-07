package com.greedy.section01.model.service;

import java.sql.Connection;
import java.util.List;

import com.greedy.section01.model.dto.MenuDTO;
import com.greedy.section01.model.dto.OrderDTO;
import com.greedy.section01.model.dto.OrderMenuDTO;
import com.greedy.section01.model.dao.OrderDAO;
import com.greedy.section01.model.dto.CategoryDTO;
import static com.greedy.common.JDBCTemplate.*;

public class OrderService {
	
	private OrderDAO orderDAO = new OrderDAO();

	/* ī�װ� ��ȸ*/
	public List<CategoryDTO> selectAllCategory() {
		
		Connection con = getConnection();
		
		List<CategoryDTO> categoryList = orderDAO.selectAllCategory(con);
		
		return categoryList;
	}

	/* �޴� ��ȸ*/
	public List<MenuDTO> selectMenuByCategory(int categoryCode) {
		
		Connection con = getConnection();
		
		List<MenuDTO> menuList = orderDAO.selectMenuByCategory(con, categoryCode);
		
		return menuList;
	}
	
	/* �ֹ���Ͽ� �޼ҵ�*/
	public int registOrder(OrderDTO order) {
		
		Connection con = getConnection();
		
		int result = 0;
		
		int orderResult = orderDAO.insertOrder(con, order);
		
		/* order Menu table���� insert*/
		List<OrderMenuDTO> orderMenuList = order.getOrderMenuList();
		int orderMenuResult = 0;
		for(OrderMenuDTO orderMenu : orderMenuList) {
			orderMenuResult += orderDAO.insertOrderMenu(con, orderMenu);
		}
		
		/* �������� �Ǵ� �� Ʈ����� ó�� */
		if(orderResult > 0 && orderMenuResult == orderMenuList.size()) {
			commit(con);
			result = 1;
		} else {
			rollback(con);
		} 
		
		close(con);
		
		
		return result;
	}
	
	

}
