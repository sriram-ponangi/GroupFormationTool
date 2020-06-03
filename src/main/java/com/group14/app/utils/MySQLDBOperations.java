package com.group14.app.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.group14.app.models.SQLInput;

@Component
public class MySQLDBOperations implements CRUDRepository<SQLInput>{
	@Autowired
    private Environment env;

	@Override
	public <S extends SQLInput> boolean existsById(S entity) {	
		
		try(Connection connection = this.getConnection();
			PreparedStatement stmt= connection.prepareStatement(entity.getSql())) {
			
			for(int i=0; i< entity.getParameters().size(); i++) 
				stmt.setString(i+1, entity.getParameters().get(i));
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				return true;				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public List<HashMap<String,Object>> readData(SQLInput entity) {
		try(Connection connection = getConnection();
				PreparedStatement stmt= connection.prepareStatement(entity.getSql())) {
				
				for(int i=0; i< entity.getParameters().size(); i++) 
					stmt.setString(i+1, entity.getParameters().get(i));
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				int columns = rsmd.getColumnCount();
				List<HashMap<String,Object>> data = new ArrayList<HashMap<String,Object>>();
				while(rs.next()) {
					HashMap<String,Object> row = new HashMap<String,Object>();
					for(int i=1;i<=columns;i++)
						row.put(rsmd.getColumnName(i), rs.getObject(rsmd.getColumnName(i)));
					data.add(row);
				}				
				return data;
							
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public <S extends SQLInput> int save(S entity) {	
		try(Connection connection = getConnection();
				PreparedStatement stmt= connection.prepareStatement(entity.getSql())) {
				
				for(int i=0; i< entity.getParameters().size(); i++) 
					stmt.setString(i+1, entity.getParameters().get(i));
				
				int rowsUpdated = stmt.executeUpdate();
				return rowsUpdated;	
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return 0;	
	}
	
	@Override
	public <S extends SQLInput> int[] saveTransaction(List<S> entities) {
		Connection connection = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			
			int[] rowsUptadedForEachTransaction = new int[entities.size()];
			
			for (int i=0; i<entities.size();i++) {
				S entity = entities.get(i);
				try(PreparedStatement stmt= connection.prepareStatement(entity.getSql())){
					for(int j=0; j< entity.getParameters().size(); j++) 						
						stmt.setString(j+1, entity.getParameters().get(j));
					
					rowsUptadedForEachTransaction[i] = stmt.executeUpdate();
				}				
			}			
			connection.commit();
			return rowsUptadedForEachTransaction;	
		} catch (SQLException e) {			 
				try {
					if(connection!=null) 
						connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	public Connection getConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}  
		try {

			Connection con = DriverManager.getConnection(env.getProperty("spring.datasource.url"),
					env.getProperty("spring.datasource.username"),
					env.getProperty("spring.datasource.password"));
			return con;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

