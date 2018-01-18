/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 21/12/17
 */
package test.java.fr.presentapi.dao;

import static org.junit.Assert.assertEquals;
import fr.presentapi.csv.StudentLoader;
import fr.presentapi.dao.DbConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CSVLoaderTest{
	@Before
	public void setup() throws SQLException{
		/* Cleans database before running tests */
		Connection conn = DbConnection.getConnection();
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("DELETE FROM Groups");
		conn.commit();
	}
	
	@Test
	public void loadTest(){
		StudentLoader loader = new StudentLoader("resources/students.csv");
		assertEquals(0, loader.load());
	}
}
