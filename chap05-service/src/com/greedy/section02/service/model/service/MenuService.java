package com.greedy.section02.service.model.service;

import java.sql.Connection;

import com.greedy.section02.service.model.dao.MenuDAO;
import com.greedy.section02.service.model.dto.CategoryDTO;
import com.greedy.section02.service.model.dto.MenuDTO;

import static com.greedy.common.JDBCTemplate.*;
/* 1. Connection 생성
 * 2. DAO의 메소드 호출
 * 3. 트랜잭션의 제어
 * 4. Connection 닫기*/
public class MenuService {

	public void registNewMenu() {
		/* 1. Connection 생성 */
		Connection con = getConnection();
		
		/* 2. DAO 메소드 호출 */
		MenuDAO menuDAO = new MenuDAO();
		/* 2-1. 카테고리 등록 */
		CategoryDTO newCategory = new CategoryDTO();
		newCategory.setName("기타");
		newCategory.setRefCategoryCode(null);
		
		int result1 = menuDAO.insertNewCategory(con, newCategory);
		
		/* 방금 입력한 마지막 카테고리 번호 조회 */
		int newCategoryCode = menuDAO.selectLastCategoryCode(con);
		
		/* 2-2. 메뉴 등록*/
		MenuDTO newMenu = new MenuDTO();
		newMenu.setName("테스트용메뉴");
		newMenu.setPrice(40000);
		newMenu.setCategoryCode(newCategoryCode);
		newMenu.setOrderableStatus("Y");
		
		int result2 = menuDAO.insertNewMenu(con, newMenu);
		
		/* 3. 트랜잭션 제어 */
		if(result1 > 0 && result2 > 0) {
			System.out.println("신규 카테고리와 메뉴를 추가하였습니다.");
			commit(con);
		} else {
			System.out.println("신규 카테고리와 메뉴를 추가하지 못했습니다.");
			rollback(con);
		} 
		/* con 반납*/
		close(con);

	} 

}
