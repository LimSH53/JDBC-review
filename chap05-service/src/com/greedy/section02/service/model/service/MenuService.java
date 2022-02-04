package com.greedy.section02.service.model.service;

import java.sql.Connection;

import com.greedy.section02.service.model.dao.MenuDAO;
import com.greedy.section02.service.model.dto.CategoryDTO;
import com.greedy.section02.service.model.dto.MenuDTO;

import static com.greedy.common.JDBCTemplate.*;
/* 1. Connection ����
 * 2. DAO�� �޼ҵ� ȣ��
 * 3. Ʈ������� ����
 * 4. Connection �ݱ�*/
public class MenuService {

	public void registNewMenu() {
		/* 1. Connection ���� */
		Connection con = getConnection();
		
		/* 2. DAO �޼ҵ� ȣ�� */
		MenuDAO menuDAO = new MenuDAO();
		/* 2-1. ī�װ� ��� */
		CategoryDTO newCategory = new CategoryDTO();
		newCategory.setName("��Ÿ");
		newCategory.setRefCategoryCode(null);
		
		int result1 = menuDAO.insertNewCategory(con, newCategory);
		
		/* ��� �Է��� ������ ī�װ� ��ȣ ��ȸ */
		int newCategoryCode = menuDAO.selectLastCategoryCode(con);
		
		/* 2-2. �޴� ���*/
		MenuDTO newMenu = new MenuDTO();
		newMenu.setName("�׽�Ʈ��޴�");
		newMenu.setPrice(40000);
		newMenu.setCategoryCode(newCategoryCode);
		newMenu.setOrderableStatus("Y");
		
		int result2 = menuDAO.insertNewMenu(con, newMenu);
		
		/* 3. Ʈ����� ���� */
		if(result1 > 0 && result2 > 0) {
			System.out.println("�ű� ī�װ��� �޴��� �߰��Ͽ����ϴ�.");
			commit(con);
		} else {
			System.out.println("�ű� ī�װ��� �޴��� �߰����� ���߽��ϴ�.");
			rollback(con);
		} 
		/* con �ݳ�*/
		close(con);

	} 

}
