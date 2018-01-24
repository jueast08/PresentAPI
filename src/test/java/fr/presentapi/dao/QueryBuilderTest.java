/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 23/11/17
 */
package test.java.fr.presentapi.dao;


import static org.junit.Assert.*;

import fr.presentapi.dao.QueryBuilder;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;

public class QueryBuilderTest{
	private static final String TEST_TABLE = "TestTable";
	private QueryBuilder _builder;
	
	@Before
	public void setup(){
		_builder = new QueryBuilder();
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
		
		String result = _builder.selectAll(TEST_TABLE).where("att1").build();
		assertTrueMsg(expected1, result);
		
		result = _builder.selectAll(TEST_TABLE)
			.where("att1", QueryBuilder.WhereOp.WHERE_GT)
			.build();
		assertTrueMsg(expected2, result);
		
		result = _builder.selectAll(TEST_TABLE)
			.where("att1")
			.where("att2")
			.build();
		assertTrueMsg(expected3, result);
	
		result = _builder.selectAll(TEST_TABLE)
			.where("att1", QueryBuilder.WhereOp.WHERE_GT)
			.orWhere("att2", QueryBuilder.WhereOp.WHERE_LT)
			.build();
		assertTrueMsg(expected4, result);
		
		result = _builder.selectAll(TEST_TABLE)
			.where("att1", QueryBuilder.WhereOp.WHERE_LIKE)
			.build();
		assertTrueMsg(expected5, result);
		
		result = _builder.selectAll(TEST_TABLE)
			.where("att1", QueryBuilder.WhereOp.WHERE_GT)
			.where("att2", QueryBuilder.WhereOp.WHERE_LIKE)
			.orWhere("att3", QueryBuilder.WhereOp.WHERE_LE)
			.build();
		assertTrueMsg(expected6, result);
	}
}
