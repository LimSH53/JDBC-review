package com.greedy.section01.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import static com.greedy.common.JDBCTemplate.*;

public class MenuDAO {
	
	private Properties prop = new Properties();
	
	public MenuDAO() {
		try {
			prop.loadFromXML(new FileInputStream("mapper/menu-query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int selectLastMenuCode(Connection con) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int maxMenuCode = 0;
		
		String query = prop.getProperty("selectLastMenuCode");
		try {
			pstmt = con.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				maxMenuCode = rset.getInt("MAX(A.MENU_CODE)");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return maxMenuCode;
	}

	public List<Map<Integer, String>> selectAllCategory(Connection con) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		List<Map<Integer, String>> categoryList = null;
		
		String query = prop.getProperty("selectAllCategory");
		
		try {
			pstmt = con.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			categoryList = new ArrayList<>();
			
			while(rset.next()) {
				Map<Integer, String> category = new HashMap<Integer, String>();
				category.put(rset.getInt("CATEGORY_CODE"), rset.getString("CATEGORY_NAME"));
				
				categoryList.add(category);
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return categoryList;
	}

}
