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
	
	@Test
	public void selectAllTest(){
		String result = _builder.selectAll(TEST_TABLE).build();
		String expected = "SELECT * FROM " + TEST_TABLE + " ";
		assertTrue("Expected : " + expected + "\ngot : " + result, expected.equals(result));
	}
	
	@Test
	public void selectAttributeTest(){
		String[] attributes = {"att1", "att2"};
		String result = _builder.select(TEST_TABLE, attributes).build();
		String expected = "SELECT att1,att2 FROM " + TEST_TABLE + " ";
		assertTrue("Expected : " + expected + "\ngot : " + result, expected.equals(result));
	}
	
	@Test
	public void selectAttributeEmptyTest(){
		String[] attributes = {};
		String result = _builder.select(TEST_TABLE, attributes).build();
		String expected = "SELECT * FROM " + TEST_TABLE + " ";
		assertTrue("Expected : " + expected + "\ngot : " + result, expected.equals(result));
	}
}
