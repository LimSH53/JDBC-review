package com.greedy.section01.insert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.greedy.common.JDBCTemplate.*;

public class Application2 {

	public static void main(String[] args) {
		
		Connection con = getConnection();
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		Properties prop = new Properties();
		
		try {
			prop.loadFromXML(new FileInputStream("mapper/menu-qurey.xml"));
			String query = prop.getProperty("insertMenu");
			System.out.println(query);
			
			Scanner sc = new Scanner(System.in);
			System.out.print("�޴��� �̸��� �Է��ϼ��� : ");
			String menuName = sc.nextLine();
			System.out.print("�޴��� ������ �Է��ϼ��� : ");
			int menuPrice = sc.nextInt();
			System.out.print("ī�װ� �ڵ带 �Է��ϼ��� : ");
			int category = sc.nextInt();
			System.out.print("�Ǹ� ���θ� �������ּ���(Y/N) : ");
			sc.nextLine();
			String orderableStatus = sc.nextLine().toUpperCase();
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, menuName);
			pstmt.setInt(2, menuPrice);
			pstmt.setInt(3, category);
			pstmt.setString(4, orderableStatus);
			
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
