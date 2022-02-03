package com.greedy.section02.update;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import com.greedy.model.dto.MenuDTO;
import static com.greedy.common.JDBCTemplate.*;

public class Application1 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("������ �޴� ��ȣ�� �Է��ϼ��� : ");
		int menuCode = sc.nextInt();
		System.out.print("������ �޴��� �̸��� �Է��ϼ��� : ");
		sc.nextLine();
		String menuName = sc.nextLine();
		System.out.print("������ �޴��� ������ �Է��ϼ��� : ");
		int menuPrice = sc.nextInt();
		
		MenuDTO changedMenu = new MenuDTO();
		changedMenu.setCode(menuCode);
		changedMenu.setName(menuName);
		changedMenu.setPrice(menuPrice);
		
		Connection con = getConnection();
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		Properties prop = new Properties();
		try {
			prop.loadFromXML(new FileInputStream("mapper/menu-qurey.xml"));
			String query = prop.getProperty("updateMenu");
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, changedMenu.getName());
			pstmt.setInt(2, changedMenu.getPrice());
			pstmt.setInt(3, changedMenu.getCode());
			
			result = pstmt.executeUpdate();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);
		}
		if(result > 0) {
			System.out.println("�޴� ���濡 ����!");
		} else {
			 System.out.println("�޴� ���濡 ���� �Ф�");
		}

	}

}
