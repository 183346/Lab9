package it.polito.tdp.porto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




import it.polito.tdp.porto.bean.Creator;

public class PortoDAO {

	public List<Creator> getAllCreators() {
		final String sql = "SELECT id_creator, family_name, given_name FROM creator ORDER BY family_name ASC" ;
		
		List<Creator> autori = new ArrayList<Creator>() ;
		
		Connection conn = DBConnect.getConnection() ;
		try {
			
			
			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet rs = st.executeQuery() ;
			
			while( rs.next() ) {
				Creator autore = new Creator(
						rs.getInt("id_creator"),
						rs.getString("family_name"),
						rs.getString("given_name") ) ;
				autori.add(autore) ;
			}
						
			st.close() ;
			conn.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e) ;
		}
		
		return autori ;
	}

	public List<Creator> getCoautori(int id_creator1,List<Creator> autori) {
		
final String sql = "select  distinct id_creator from authorship where eprintid in (select aaa.eprintid from authorship aaa where aaa.id_creator=?) and id_creator<>?" ;
		
		List<Creator> coautori = new ArrayList<Creator>() ;
		
		Connection conn = DBConnect.getConnection() ;
		try {
			
			
			PreparedStatement st = conn.prepareStatement(sql) ;
	st.setInt(1, id_creator1);
	st.setInt(2, id_creator1);
			
			ResultSet rs = st.executeQuery() ;
		
			
			while( rs.next() ) {
				
				Creator coautore =  autori.get(autori.indexOf(new Creator(rs.getInt("id_creator"),null,null)));
						
				coautori.add(coautore) ;
			}
						
			st.close() ;
			conn.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e) ;
		}
		
		return coautori ;
	}
	
}
