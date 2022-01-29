package com.greedy.section02.preparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.greedy.model.dto.EmployeeDTO;

import static com.greedy.common.JDBCTemplate.*;

public class Application4 {

	public static void main(String[] args) {
		/* Scanner를 이용해서 사번을 입력 받고 해당 사번의 사원 정보를 EmployeeDTO에 담아서 출력하세요.
		 * (PreparedStatement 객체 사용)
		 * */
		
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		/* 사번입력 받을 Scanner */
		Scanner sc = new Scanner(System.in);
		System.out.print("조회하려는 직원의 사번을 입력해주세요 : ");
		String empId = sc.nextLine();
		
		EmployeeDTO emp = new EmployeeDTO();
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM EMPLOYEE WHERE EMP_ID = ?");
			pstmt.setString(1, empId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				emp.setEmpId(rset.getString("EMP_ID"));
				emp.setEmpName(rset.getString("EMP_NAME"));
				emp.setEmpNo(rset.getString("EMP_NO"));
				emp.setEmail(rset.getString("EMAIL"));
				emp.setPhone(rset.getString("PHONE"));
				emp.setDeptCode(rset.getString("DEPT_CODE"));
				emp.setJobCode(rset.getString("JOB_CODE"));
				emp.setSalLevel(rset.getString("SAL_LEVEL"));
				emp.setSalary(rset.getInt("SALARY"));
				emp.setBonus(rset.getDouble("BONUS"));
				emp.setManagerId(rset.getString("MANAGER_ID"));
				emp.setHireDate(rset.getDate("HIRE_DATE"));
				emp.setEntDate(rset.getDate("ENT_DATE"));
				emp.setEntYn(rset.getString("ENT_YN"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
			close(con);
		}
		
		System.out.println(emp);

	}

}
