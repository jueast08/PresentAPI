/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 20/11/17
 */

package fr.presentapi.dao;


public abstract class CSVLoader{
	private CSVParser _parser;

	public CSVLoader(String filepath){
		_parser = new CSVParser(filepath);
	}

	public CSVParser getParser(){
		return _parser;
	}

	public abstract void load();
}
