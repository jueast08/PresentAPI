/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 21/12/17
 */
package test.java.fr.presentapi.dao;

import static org.junit.Assert.assertEquals;
import fr.presentapi.csv.StudentLoader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CSVLoaderTest{
	@Test
	public void loadTest(){
		StudentLoader loader = new StudentLoader("resources/students.csv");
		assertEquals(0, loader.load());
	}		
}
