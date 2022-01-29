package com.greedy.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.greedy.common.JDBCTemplate.*;


public class Application1 {

	public static void main(String[] args) {
		
		Connection con = getConnection();
		
		/* 쿼리문을 저장하고 실행하는 기능을 하는 용도의 인터페이스 */
		Statement stmt = null;
		/* select 결과 집합을 받아올 용도의 인터페이스 */
		ResultSet rset = null;
		
		/* Connertion 인스턴스를 통해 Statement 인스턴스 생성*/
		try {
			stmt = con.createStatement();
			
			rset = stmt.executeQuery("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE");
			
			while(rset.next()) {
				System.out.println(rset.getString("EMP_ID") + ", " + rset.getNString("EMP_NAME"));
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
