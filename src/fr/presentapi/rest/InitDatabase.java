/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 * 
 * @version 0.0.1 - Last modified: 09/11/17
 */
package fr.presentapi.rest;

import fr.presentapi.csv.StudentLoader;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/initdb")
public class InitDatabase{
	@GET
	public void init(){
		StudentLoader loader = new StudentLoader("resources/students.csv");
		loader.load();
	}
		
}
