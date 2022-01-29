package com.greedy.section02.preparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.greedy.common.JDBCTemplate.*;

public class Application3 {
	
	public static void main(String[] args) {
		
		Connection con = getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("조회하려는 사번을 입력해주세요 : ");
		String empId = sc.nextLine();
		
		String qurey = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID =?";
		
		try {
			pstmt = con.prepareStatement(qurey);
			pstmt.setString(1, empId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				System.out.println(rset.getString("EMP_ID") + ", " + rset.getString("EMP_NAME"));
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
