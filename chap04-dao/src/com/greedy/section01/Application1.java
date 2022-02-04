package com.greedy.section01;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.greedy.section01.model.dao.MenuDAO;

import static com.greedy.common.JDBCTemplate.*;

public class Application1 {

	public static void main(String[] args) {
		
		MenuDAO menuDAO = new MenuDAO();
		
		Connection con = getConnection();
		
		/*1. 메뉴의 마지막 번호 조회 */
		int maxMenuCode = menuDAO.selectLastMenuCode(con);
		System.out.println("maxMenuCode : " + maxMenuCode);
		
		/*2. 카테고리 조회*/
		List<Map<Integer, String>> categoryList = menuDAO.selectAllCategory(con);
		for(Map<Integer, String> category : categoryList) {
			System.out.println(category);
		}
		
		/* 3. 신규메뉴 등록*/
	}

}
