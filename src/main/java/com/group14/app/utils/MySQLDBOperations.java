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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.group14.app.models.SQLInput;

@Component
public class MySQLDBOperations implements CRUDRepository<SQLInput> {

	private final static String DB_HOST;
	private final static String DB_USER;
	private final static String DB_PASSWORD;

	private static final Logger LOG = LoggerFactory.getLogger(MySQLDBOperations.class);

	static {
		final String ENV = System.getenv("APP_PROFILE");
		LOG.info("APP_PROFILE: {}", ENV);
		if (ENV != null && ENV.equalsIgnoreCase("test")) {
			DB_HOST = "jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_14_TEST?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			DB_USER = "CSCI5308_14_TEST_USER";
			DB_PASSWORD = "CSCI5308_14_TEST_14577";
		} else if (ENV != null && ENV.equalsIgnoreCase("prod")) {
			DB_HOST = "jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_14_PRODUCTION?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			DB_USER = "CSCI5308_14_PRODUCTION_USER";
			DB_PASSWORD = "CSCI5308_14_PRODUCTION_14739";
		} else {
			DB_HOST = "jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_14_DEVINT?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			DB_USER = "CSCI5308_14_DEVINT_USER";
			DB_PASSWORD = "CSCI5308_14_DEVINT_14103";
		}
		LOG.info("Connected Database: {}", DB_HOST);

	}

	@Override
	public <S extends SQLInput> boolean existsById(S entity) throws SQLException {

		try (Connection connection = this.getConnection();
				PreparedStatement stmt = connection.prepareStatement(entity.getSql())) {

			for (int i = 0; i < entity.getParameters().size(); i++)
				stmt.setObject(i + 1, entity.getParameters().get(i));
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				return true;
		} catch (SQLException e) {
			LOG.error("Could not execute SQL :: " + entity.getSql());
			e.printStackTrace();
			throw e;

		}
		return false;
	}

	@Override
	public List<HashMap<String, Object>> readData(SQLInput entity) throws SQLException {
		try (Connection connection = getConnection();
				PreparedStatement stmt = connection.prepareStatement(entity.getSql())) {

			for (int i = 0; i < entity.getParameters().size(); i++)
				stmt.setObject(i + 1, entity.getParameters().get(i));
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
			while (rs.next()) {
				HashMap<String, Object> row = new HashMap<String, Object>();
				for (int i = 1; i <= columns; i++)
					row.put(rsmd.getColumnName(i), rs.getObject(rsmd.getColumnName(i)));
				data.add(row);
			}
			return data;

		} catch (SQLException e) {
			LOG.error("Could not execute SQL :: " + entity.getSql());
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public <S extends SQLInput> int save(S entity) throws SQLException {
		try (Connection connection = getConnection();
				PreparedStatement stmt = connection.prepareStatement(entity.getSql())) {
			for (int i = 0; i < entity.getParameters().size(); i++) {
				stmt.setObject(i + 1, entity.getParameters().get(i));
			}

			int rowsUpdated = stmt.executeUpdate();

			return rowsUpdated;

		} catch (SQLException e) {
			LOG.error("Could not execute SQL :: " + entity.getSql());
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public <S extends SQLInput> int[] saveTransaction(List<S> entities) throws SQLException {
		Connection connection = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);

			int[] rowsUptadedForEachTransaction = new int[entities.size()];

			for (int i = 0; i < entities.size(); i++) {
				S entity = entities.get(i);

				try (PreparedStatement stmt = connection.prepareStatement(entity.getSql())) {
					for (int j = 0; j < entity.getParameters().size(); j++)
						stmt.setObject(j + 1, entity.getParameters().get(j));
					rowsUptadedForEachTransaction[i] = stmt.executeUpdate();
				}
			}
			connection.commit();
			return rowsUptadedForEachTransaction;
		} catch (SQLException e) {
			try {
				if (connection != null) {
					connection.rollback();
				}
				throw e;
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw e1;
			}
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}

	}

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			Connection con = DriverManager.getConnection(DB_HOST, DB_USER, DB_PASSWORD);
			return con;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
