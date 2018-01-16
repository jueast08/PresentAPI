/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 20/11/17
 */

package test.java.fr.presentapi.rest;

import fr.presentapi.csv.CSVParser;
import java.util.Arrays;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class CSVParserTest{
	private CSVParser _parser;

	@Before
	public void setup(){
		_parser = new CSVParser("resources/students.csv");
	}

	@Test
	public void parseRow(){
		final String[] outputs = {
			"[1,smith,john,smith.john@ensicaen.fr,info,image,2019,B,3]",
			"[2,debroise,quentin,debroise.quentin@ensicaen.fr,info,image,2019,B,3]",
			"[3,easterly,julian,easterly@ensicaen.fr,monetique,2019,B,3]",
			"[4,smagghe,coline,smagghe@ensicaen.fr,image,2019,B,3]",
			"[5,nicol,pierre,nicol@ensicaen.fr,monetique,2019,A,1]",
			"[6,Leymarie,jeAnNe,leymarie@ensicaen.fr,image,2019,B,3]",
		};

		for(int i = 0; _parser.hasNext(); i++){
			String a = Arrays.toString(_parser.next());
			System.out.println("got : " + a);
			//assertTrue(outputs[i].equals(Arrays.toString(_parser.next())));
			System.out.println("expected : " + outputs[i]);
			assertTrue(outputs[i].equals(a));
		}

		assertTrue(_parser.hasNext());
		_parser.close();
	}	
}
