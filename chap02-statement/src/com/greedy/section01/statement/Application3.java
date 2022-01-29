package com.greedy.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.greedy.common.JDBCTemplate.*;

public class Application3 {

	public static void main(String[] args) {
		
		Connection con = getConnection();
		
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			stmt = con.createStatement();
			
			Scanner sc = new Scanner(System.in);
			System.out.print("조회하려는 사번을 입력해주세요 : ");
			String empId = sc.nextLine();
			
			String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID ='" + empId + "'";
			System.out.println(query);
			
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				System.out.println(rset.getString("EMP_ID") + ", " + rset.getString("EMP_NAME"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
			close(con);
		}
		
		
		
	}

}
