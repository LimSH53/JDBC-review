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
		
		/* insert 구문은 변경된 행의 갯수를 리턴해주기 때문에 int로 받는다 */
		int result = 0;
		
		Properties prop = new Properties();
		
		try {
			prop.loadFromXML(new FileInputStream("mapper/menu-qurey.xml"));
			String query = prop.getProperty("insertMenu");
			/* query확인용 */
			System.out.println(query);
			
			pstmt = con.prepareStatement(query);
			/* 넣을 값 입력 */
			pstmt.setString(1, "봉골레청국장");
			pstmt.setInt(2, 50000);
			pstmt.setInt(3, 4);
			pstmt.setString(4, "Y");
			
			/* insert/update/delete시 executeUpdate() */
			
			result = pstmt.executeUpdate();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			/* ResultSet은 사용하지 않았기 때문에 안닫아줌*/
			close(pstmt);
			close(con);
		}
		
		if(result > 0) {
			System.out.println("성공적으로 변경되었습니다.");
		} else {
			System.out.println("변경에 실패하였습니다.");
		}

	}

}
