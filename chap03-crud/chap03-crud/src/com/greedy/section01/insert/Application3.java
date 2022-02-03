package com.greedy.section01.insert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import com.greedy.model.dto.MenuDTO;

import static com.greedy.common.JDBCTemplate.*;

public class Application3 {

	public static void main(String[] args) {
		
		/*�ٸ� Ŭ������ �����Ѵ� */
		Scanner sc = new Scanner(System.in);
		System.out.print("�޴��� �̸��� �Է��ϼ��� : ");
		String menuName = sc.nextLine();
		System.out.print("�޴��� ������ �Է��ϼ��� : ");
		int menuPrice = sc.nextInt();
		System.out.print("ī�װ� �ڵ带 �Է��ϼ��� : ");
		int categoryCode = sc.nextInt();
		System.out.print("�Ǹ� ���θ� �������ּ���(Y/N) : ");
		sc.nextLine();
		String orderableStatus = sc.nextLine().toUpperCase();
		
		MenuDTO newMenu = new MenuDTO();
		newMenu.setName(menuName);
		newMenu.setPrice(menuPrice);
		newMenu.setCategoryCode(categoryCode);
		newMenu.setOrderableStatus(orderableStatus);
		
		/* ------------------------------------------- */
		/* ���� ���ļ� ������ ���� DTO�� ��� ���� */
		/* ------------------------------------------- */
		
		Connection con = getConnection();
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		Properties prop = new Properties();
		
		try {
			prop.loadFromXML(new FileInputStream("mapper/menu-qurey.xml"));
			String query = prop.getProperty("insertMenu");
			System.out.println(query);
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, newMenu.getName());
			pstmt.setInt(2, newMenu.getPrice());
			pstmt.setInt(3, newMenu.getCategoryCode());
			pstmt.setString(4, newMenu.getOrderableStatus());
			
			result = pstmt.executeUpdate();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);
		}
		/* ������Ʈ �������� */
		if(result > 0) {
			System.out.println("����");
		} else {
			System.out.println("����");
		}

	}

}
