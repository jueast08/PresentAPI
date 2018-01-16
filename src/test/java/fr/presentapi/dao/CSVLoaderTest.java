/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 21/12/17
 */
package test.java.fr.presentapi.dao;

import fr.presentapi.csv.CSVParser;
import static org.junit.Assert.assertEquals;
import fr.presentapi.csv.StudentLoader;
import fr.presentapi.dao.UsersDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CSVLoaderTest{
	@Before
	public void setup(){
		/* Cleans database before running tests */
		UsersDAO usermodel = new UsersDAO();
		CSVParser parser = new CSVParser("resources/students.csv");
		while(parser.hasNext()){
			usermodel.deleteUsers(Long.parseLong(parser.next()[0]));
		}
	}
	
	@Test
	public void loadTest(){
		StudentLoader loader = new StudentLoader("resources/students.csv");
		assertEquals(0, loader.load());
	}
}
