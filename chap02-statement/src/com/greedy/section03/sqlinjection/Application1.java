package com.greedy.section03.sqlinjection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.greedy.common.JDBCTemplate.*;

public class Application1 {
	
	/* 입력할 계정 정보 */
	private static String empId = "200";
	private static String empName = "' OR 1=1 AND EMP_ID = '200";

	public static void main(String[] args) {
		
		Connection con = getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String qurey = "SELECT * FROM EMPLOYEE WHERE EMP_ID = ? AND EMP_NAME =?";
		/*입력된 쿼리 확인*/
		System.out.println(qurey);
		
		try {
			pstmt = con.prepareStatement(qurey);
			pstmt.setString(1, empId);
			pstmt.setString(2, empName);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				System.out.println(rset.getString("EMP_NAME") + "님 환영합니다.");
			} else {
			    System.out.println("회원 정보가 없습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
			close(con);
		}
	

	}

}
