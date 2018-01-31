/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 23/11/17
 */
package test.java.test.java.fr.presentapi.querybuilder;


import static org.junit.Assert.*;

import fr.presentapi.querybuilder.QueryBuilder;
import fr.presentapi.dao.UserModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;

public class QueryBuilderTest{
	private static final String TEST_TABLE = "TestTable";
	private QueryBuilder _builder;
	
	@Before
	public void setup(){
		_builder = new QueryBuilder(new UserModel());
	}
	
	private void assertTrueMsg(String expected, String got){
		assertTrue("Expected : " + expected + "\ngot : " + got, expected.equals(got));
	}
	
	@Test
	public void selectAllTest(){
		String result = _builder.selectAll(TEST_TABLE).build();
		String expected = "SELECT * FROM " + TEST_TABLE + " ";
		assertTrueMsg(expected, result);
	}
	
	@Test
	public void selectAttributeTest(){
		String[] attributes = {"att1", "att2"};
		String result = _builder.select(TEST_TABLE, attributes).build();
		String expected = "SELECT att1,att2 FROM " + TEST_TABLE + " ";
		assertTrueMsg(expected, result);
	}
	
	@Test
	public void selectAttributeEmptyTest(){
		String[] attributes = {};
		String result = _builder.select(TEST_TABLE, attributes).build();
		String expected = "SELECT * FROM " + TEST_TABLE + " ";
		assertTrueMsg(expected, result);
	}
	
	@Test
	public void whereTest(){
		String expected1 = "SELECT * FROM " + TEST_TABLE + " WHERE att1=? ";
		String expected2 = "SELECT * FROM " + TEST_TABLE + " WHERE att1>? ";
		String expected3 = "SELECT * FROM " + TEST_TABLE + " WHERE att1=? AND att2=? ";
		String expected4 = "SELECT * FROM " + TEST_TABLE + " WHERE att1>? OR att2<? ";
		String expected5 = "SELECT * FROM " + TEST_TABLE + " WHERE att1 LIKE ? ";
		String expected6 = "SELECT * FROM " + TEST_TABLE + " WHERE att1>? AND att2 LIKE ? OR att3<=? ";
		
		String result = _builder.selectAll(TEST_TABLE).where("att1", "placeholder").build();
		assertTrueMsg(expected1, result);
		_builder.reset();
		
		result = _builder.selectAll(TEST_TABLE)
			.where("att1", QueryBuilder.WhereOp.WHERE_GT, "placeholder")
			.build();
		assertTrueMsg(expected2, result);
		_builder.reset();
		
		result = _builder.selectAll(TEST_TABLE)
			.where("att1", "placeholder")
			.where("att2", "placeholder")
			.build();
		assertTrueMsg(expected3, result);
		_builder.reset();
	
		result = _builder.selectAll(TEST_TABLE)
			.where("att1", QueryBuilder.WhereOp.WHERE_GT, "placeholder")
			.orWhere("att2", QueryBuilder.WhereOp.WHERE_LT, "placeholder")
			.build();
		assertTrueMsg(expected4, result);
		_builder.reset();
		
		result = _builder.selectAll(TEST_TABLE)
			.where("att1", QueryBuilder.WhereOp.WHERE_LIKE, "placeholder")
			.build();
		assertTrueMsg(expected5, result);
		_builder.reset();
		
		result = _builder.selectAll(TEST_TABLE)
			.where("att1", QueryBuilder.WhereOp.WHERE_GT, "placeholder")
			.where("att2", QueryBuilder.WhereOp.WHERE_LIKE, "placeholder")
			.orWhere("att3", QueryBuilder.WhereOp.WHERE_LE, "placeholder")
			.build();
		assertTrueMsg(expected6, result);
		_builder.reset();
	}
	
	@Test
	public void modelQueryTest(){
		String attributes[] = {};
		String expected = "SELECT * FROM Users WHERE att1=? ";
		String result = new UserModel().select(attributes).where("att1", "placeholder").build();
		assertTrueMsg(expected, result);
	}
	
	@Test
	public void databaseTest() throws SQLException{
		String attributes[] = {};
		Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/presentdb", "testuser", "password");
		UserModel model = new UserModel(conn);
		model.select(attributes);
		conn.close();
	}
}
