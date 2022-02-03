package com.greedy.section03.delete;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.greedy.common.JDBCTemplate.*;

public class Application1 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("삭제할 메뉴 번호를 입력하세요 : ");
		int deleteCode = sc.nextInt();
		
		Connection con = getConnection();
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		Properties prop = new Properties();
		try {
			prop.loadFromXML(new FileInputStream("mapper/menu-qurey.xml"));
			String query = prop.getProperty("deleteMenu");
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, deleteCode);
			
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
			System.out.println("메뉴 삭제에 성공하였습니다.");
		} else {
			System.out.println("메뉴 삭제에 실패하였습니다.");
		}

	}

}
