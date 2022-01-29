package com.greedy.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.greedy.common.JDBCTemplate.*;


public class Application1 {

	public static void main(String[] args) {
		
		Connection con = getConnection();
		
		/* �������� �����ϰ� �����ϴ� ����� �ϴ� �뵵�� �������̽� */
		Statement stmt = null;
		/* select ��� ������ �޾ƿ� �뵵�� �������̽� */
		ResultSet rset = null;
		
		/* Connertion �ν��Ͻ��� ���� Statement �ν��Ͻ� ����*/
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
