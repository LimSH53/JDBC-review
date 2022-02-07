package com.greedy.section01.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.greedy.section01.model.dto.MenuDTO;
import com.greedy.section01.model.dto.OrderDTO;
import com.greedy.section01.model.dto.OrderMenuDTO;
import com.greedy.section01.model.dto.CategoryDTO;
import com.greedy.section01.model.service.OrderService;
import com.greedy.section01.view.ResultView;

public class OrderController {
	
	private OrderService orderService = new OrderService();

	public List<CategoryDTO> selectAllCategory() {
		
		List<CategoryDTO> categoryList = orderService.selectAllCategory();
		
		return categoryList;
	}

	/* 메뉴 조회*/
	public List<MenuDTO> selectMenuByCategory(int categoryCode) {
		
		List<MenuDTO> menuList = orderService.selectMenuByCategory(categoryCode);
	
		return menuList;
	}

	/* 메뉴등록 */
	public void registOrder(Map<String, Object> requestMap) {
		int totalOrderPrice = (Integer)requestMap.get("totalOrderPrice");
		List<OrderMenuDTO> orderMenuList = (List<OrderMenuDTO>)requestMap.get("orderMenuList");
		
		java.util.Date orderTime = new java.util.Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		String date = dateFormat.format(orderTime);
		String time = timeFormat.format(orderTime);
		
		OrderDTO order = new OrderDTO();
		order.setDate(date);
		order.setTime(time);
		order.setTotalOrderPrice(totalOrderPrice);
		order.setOrderMenuList(orderMenuList);
		
		int result = orderService.registOrder(order);
		
		ResultView resultView = new ResultView();
		if(result > 0) {
			resultView.success();
		} else {
			resultView.failed();
		}
		
	}

}
