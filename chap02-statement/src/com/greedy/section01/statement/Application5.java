package com.greedy.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.greedy.model.dto.EmployeeDTO;

import static com.greedy.common.JDBCTemplate.*;

public class Application5 {

	public static void main(String[] args) {

		Connection con = getConnection();
		
		Statement stmt = null;
		ResultSet rset = null;
		
		/* 한 행 정보를 담을 DTO */
		EmployeeDTO row = null;
		/* 여러 DTO를 하나의 인스턴스로 묶기위한 List*/
		List<EmployeeDTO> empList = null;
		
		String query = "SELECT * FROM EMPLOYEE";
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			empList = new ArrayList<EmployeeDTO>();
			
			while(rset.next()) {
				
				row = new EmployeeDTO();
				
				row.setEmpId(rset.getString("EMP_ID"));
				row.setEmpName(rset.getString("EMP_NAME"));
				row.setEmpNo(rset.getString("EMP_NO"));
				row.setEmail(rset.getString("EMAIL"));
				row.setPhone(rset.getString("PHONE"));
				row.setDeptCode(rset.getString("DEPT_CODE"));
				row.setJobCode(rset.getString("JOB_CODE"));
				row.setSalLevel(rset.getString("SAL_LEVEL"));
				row.setSalary(rset.getInt("SALARY"));
				row.setBonus(rset.getDouble("BONUS"));
				row.setManagerId(rset.getString("MANAGER_ID"));
				row.setHireDate(rset.getDate("HIRE_DATE"));
				row.setEntDate(rset.getDate("ENT_DATE"));
				row.setEntYn(rset.getString("ENT_YN"));
				
				empList.add(row);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
			close(con);
		}
		
		for(EmployeeDTO emp : empList) {
			System.out.println(emp);
		}
		

	}

}
