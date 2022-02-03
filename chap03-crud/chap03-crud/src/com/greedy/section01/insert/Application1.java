package com.greedy.section01.insert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static com.greedy.common.JDBCTemplate.*;

public class Application1 {

	public static void main(String[] args) {
		
		Connection con = getConnection();
		
		PreparedStatement pstmt = null;
		
		/* insert ������ ����� ���� ������ �������ֱ� ������ int�� �޴´� */
		int result = 0;
		
		Properties prop = new Properties();
		
		try {
			prop.loadFromXML(new FileInputStream("mapper/menu-qurey.xml"));
			String query = prop.getProperty("insertMenu");
			/* queryȮ�ο� */
			System.out.println(query);
			
			pstmt = con.prepareStatement(query);
			/* ���� �� �Է� */
			pstmt.setString(1, "����û����");
			pstmt.setInt(2, 50000);
			pstmt.setInt(3, 4);
			pstmt.setString(4, "Y");
			
			/* insert/update/delete�� executeUpdate() */
			
			result = pstmt.executeUpdate();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			/* ResultSet�� ������� �ʾұ� ������ �ȴݾ���*/
			close(pstmt);
			close(con);
		}
		
		if(result > 0) {
			System.out.println("���������� ����Ǿ����ϴ�.");
		} else {
			System.out.println("���濡 �����Ͽ����ϴ�.");
		}

	}

}
