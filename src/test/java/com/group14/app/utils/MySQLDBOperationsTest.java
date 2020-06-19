package com.group14.app.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.group14.app.models.SQLInput;

public class MySQLDBOperationsTest {

	@Spy
	private MySQLDBOperations mySQLDBOperations;

	@Mock
	private Connection c;

	@Mock
	private PreparedStatement stmt;

	@Mock
	private ResultSet rs;

	@Mock
	private ResultSetMetaData rsmd;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void existsByIdTest() {
		boolean response = false;

		List<Object> parameters = new ArrayList<>();
		parameters.add("userId");
		SQLInput sqlInput = new SQLInput("SELECT id FROM sometable where id = ?", parameters);

		try {
			Mockito.doReturn(c).when(mySQLDBOperations).getConnection();

			when(c.prepareStatement(any(String.class))).thenReturn(stmt);
			when(stmt.executeQuery()).thenReturn(rs);
			when(rs.next()).thenReturn(true);

			response = mySQLDBOperations.existsById(sqlInput);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		assertEquals(true, response);
	}

	@Test
	public void readDataTest() {

		List<Object> parameters = new ArrayList<>();
		parameters.add("userId");
		SQLInput sqlInput = new SQLInput("SELECT * FROM sometable where id = ?", parameters);

		try {
			Mockito.doReturn(c).when(mySQLDBOperations).getConnection();

			when(c.prepareStatement(any(String.class))).thenReturn(stmt);
			when(stmt.executeQuery()).thenReturn(rs);
			when(rs.getMetaData()).thenReturn(rsmd);
			when(rsmd.getColumnCount()).thenReturn(6);
			when(rsmd.getColumnName(1)).thenReturn("user_id");
			when(rsmd.getColumnName(2)).thenReturn("password");
			when(rsmd.getColumnName(3)).thenReturn("email");
			when(rsmd.getColumnName(4)).thenReturn("first_name");
			when(rsmd.getColumnName(5)).thenReturn("last_name");
			when(rsmd.getColumnName(6)).thenReturn("enabled");
			when(rs.next()).thenReturn(true).thenReturn(false);
			when(rs.getObject("user_id")).thenReturn("userId");
			when(rs.getObject("password")).thenReturn("password");
			when(rs.getObject("email")).thenReturn("email");
			when(rs.getObject("first_name")).thenReturn("firstName");
			when(rs.getObject("last_name")).thenReturn("lastName");
			when(rs.getObject("enabled")).thenReturn(1);

			List<HashMap<String, Object>> response = mySQLDBOperations.readData(sqlInput);
			HashMap<String, Object> row = response.get(0);

			assertEquals("userId", (String) row.get("user_id"));
			assertEquals("password", (String) row.get("password"));
			assertEquals("email", (String) row.get("email"));
			assertEquals("firstName", (String) row.get("first_name"));
			assertEquals("lastName", (String) row.get("last_name"));
			assertEquals(1, (Integer) row.get("enabled"));

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void saveTest() {
		int response = 0;

		List<Object> parameters = new ArrayList<>();
		parameters.add("param_1");
		parameters.add("param_2");
		SQLInput sqlInput = new SQLInput("INSERT INTO SomeTable (  param_1, param_2 ) VALUES (?,?)", parameters);

		try {
			Mockito.doReturn(c).when(mySQLDBOperations).getConnection();

			when(c.prepareStatement(any(String.class))).thenReturn(stmt);
			when(stmt.executeUpdate()).thenReturn(1);
			response = mySQLDBOperations.save(sqlInput);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		assertEquals(1, response);
	}

	@Test
	public void saveTransactionTest() {
		int[] response = new int[3];

		List<Object> parameters = new ArrayList<>();
		parameters.add("param_1");
		parameters.add("param_2");
		List<SQLInput> sqlInputs = new ArrayList<>();
		sqlInputs.add(new SQLInput("INSERT INTO SomeTable (  param_1, param_2 ) VALUES (?,?)", parameters));
		sqlInputs.add(new SQLInput("INSERT INTO SomeTable (  param_1, param_2 ) VALUES (?,?)", parameters));
		sqlInputs.add(new SQLInput("INSERT INTO SomeTable (  param_1, param_2 ) VALUES (?,?)", parameters));

		try {
			Mockito.doReturn(c).when(mySQLDBOperations).getConnection();

			when(c.prepareStatement(any(String.class))).thenReturn(stmt);
			when(stmt.executeUpdate()).thenReturn(1).thenReturn(2).thenReturn(3);
			response = mySQLDBOperations.saveTransaction(sqlInputs);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		assertEquals(1, response[0]);
		assertEquals(2, response[1]);
		assertEquals(3, response[2]);
	}

}
