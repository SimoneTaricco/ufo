package it.polito.tdp.ufo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;
import java.sql.*;

public class TestDB {

	public static void main(String[] args) {


		String jdbcURL = "jdbc:mysql://localhost/ufo_sightings?user=root&password=rootpsw";
		
		try {
		Connection conn = DriverManager.getConnection(jdbcURL);
		
		String sql = "SELECT DISTINCT shape "
				+ "FROM sighting "
				+ "WHERE shape<>'' "
				+ "ORDER BY shape ASC";
		
		PreparedStatement st = conn.prepareStatement(sql);
		
		ResultSet res = st.executeQuery(sql);
		
		List<String> formeUFO = new ArrayList<>();
		while(res.next()) {
			String forma = res.getString("shape");
			formeUFO.add(forma);
		}
		
		
		String sql2 = "SELECT COUNT(*) AS cnt FROM sighting WHERE shape= ? ";
		String shapeScelta = "circle";
		
		PreparedStatement st2 = conn.prepareStatement(sql2);
		st2.setString(1, shapeScelta);
		ResultSet res2 = st2.executeQuery();
		res2.first();
		int count = res2.getInt("cnt");
		st2.close();
				
		System.out.println("UFO di forma " + shapeScelta + " sono: "+count);
		
		conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
