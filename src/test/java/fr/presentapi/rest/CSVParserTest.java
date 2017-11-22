/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 20/11/17
 */

package test.java.fr.presentapi.rest;

import fr.presentapi.csv.CSVParser;
import java.util.Arrays;
import org.junit.Assert;
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
			"[00000001, smith, john, smith.john@ensicaen.fr, info, image, 2019, B, 3]",
			"[00000002, debroise, quentin, debroise.quentin@ensicaen.fr, info, image, 2019, B, 3]",
			"[00000003, easterly, julian, easterly@ensicaen.fr, monetique, 2019, B, 3]",
			"[00000004, smagghe, coline, smagghe@ensicaen.fr, image, 2019, B, 3]",
			"[00000005, nicol, pierre, nicol@ensicaen.fr, monetique, 2019, A, 2]",
			"[00000006, Leymarie, jeAnNe, leymarie@ensicaen.fr, image, 2019, B, 3]",
		};

		for(int i = 0; _parser.hasNext(); i++){
			assertTrue(outputs[i].equals(Arrays.toString(_parser.next())));
		}

		assertTrue(_parser.hasNext());
		_parser.close();
	}	
}
