package com.greedy.section02.preparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.greedy.model.dto.EmployeeDTO;

import static com.greedy.common.JDBCTemplate.*;

public class Application5 {

	public static void main(String[] args) {
		/* EMPLOYEE ���̺��� ��ȸ�� ����� �̸��� ���� �Է� �޾� �ش� ������ ���� ��� �������� ��� ����ϼ���.
		 * (PreparedStatement ��ü ���, List<EmployeeDTO> ��ü�� ��Ƽ� ���)
		 * */
		
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		EmployeeDTO emp = null;
		List<EmployeeDTO> empList = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("��ȸ�� ����� �̸��� ���� �Է����ּ��� : ");
		String name = sc.nextLine();
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM EMPLOYEE WHERE EMP_NAME LIKE ? || '%'");
			pstmt.setString(1, name);
			
			rset = pstmt.executeQuery();
			
			empList = new ArrayList<EmployeeDTO>();
			
			while(rset.next()) {
				
				emp = new EmployeeDTO();
				
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
				
				empList.add(emp);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
			close(con);
		}
		
		for(EmployeeDTO e : empList) {
			System.out.println(e);
		}

	}

}
