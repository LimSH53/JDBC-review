package com.greedy.section01.transaction;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static com.greedy.common.JDBCTemplate.*;

public class Application {

	public static void main(String[] args) {
		
		Connection con = getConnection();
		
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		
		int result1 = 0;
		int result2 = 0;
		
		Properties prop = new Properties();
		try {
			prop.loadFromXML(new FileInputStream("mapper/menu-query.xml"));
			
			String query1 = prop.getProperty("insertCategory");
			String query2 = prop.getProperty("insertMenu");
			
			pstmt1 = con.prepareStatement(query1);
			pstmt1.setString(1, "기타_테스트");
			pstmt1.setInt(2, 1);
			
			result1 = pstmt1.executeUpdate();
			
			pstmt2 = con.prepareStatement(query2);
			pstmt2.setString(1, "정어리비빔밥_테스트");
			pstmt2.setInt(2, 40000);
			pstmt2.setInt(3, 4);
			pstmt2.setString(4, "Y");
			
			result2 = pstmt2.executeUpdate();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt1);
			close(pstmt2);
			
			if(result1 > 0 && result2 > 0) {
				System.out.println("신규 카테고리와 메뉴 등록 성공");
				commit(con);
			} else {
				System.out.println("신규 카테고리와 메뉴 등록 실패");
				rollback(con);
			}
			
			close(con);
			
		}
		
		
		
		
		

	}

}
