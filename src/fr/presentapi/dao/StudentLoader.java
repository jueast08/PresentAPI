/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 20/11/17
 */

package fr.presentapi.dao;

public class StudentLoader extends CSVLoader{
	private StudentModel _studentsModel;

	public StudentLoader(String filepath){
		super(filepath);
		_studentsModel = new StudentModel();
	}

	@Override
	public void load(){
	}
}
