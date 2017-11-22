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
		System.out.println(_parser.hasNext());
		while(_parser.hasNext()){
			System.out.println(Arrays.toString(_parser.next()));
		}
		assertTrue("1 == 2 should equals true", 1 == 2);
	}	
}
