/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 20/11/17
 */

package fr.presentapi.csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class CSVParser implements Iterator{
	private final static String DEFAULT_SEPARATOR = ";";
	private final String _separator;
	private final String _filepath;
	private BufferedReader _buffer;

	public CSVParser(String filepath, String separator){
		_filepath = filepath;
		_separator = separator;
		_buffer = null;
	}

	public CSVParser(String filepath){
		this(filepath, DEFAULT_SEPARATOR);
	}

	public void close(){
		if(_buffer != null){
			try{
				_buffer.close();
			} catch(IOException ex){
				System.err.println("Error closing file");
			}
		}
	}

	@Override
	public boolean hasNext(){
		try{
			if(_buffer == null){
				_buffer = new BufferedReader(new FileReader(_filepath));
			}
			if(_buffer.ready()){
				return true;
			}
			else{
				_buffer.close();
				_buffer = null;
			}
		}
		catch(FileNotFoundException e){
			System.err.println("File not found " + _filepath);
		} catch(IOException ex){
			System.err.println("Error reading file " + _filepath);
		}

		return false;
	}

	@Override
	public String[] next(){
		String line;

		try{
			if(_buffer == null){
				_buffer = new BufferedReader(new FileReader(_filepath));
			}

			if((line = _buffer.readLine()) == null){
				_buffer.close();
				_buffer = null;
				throw new NoSuchElementException();
			}

			return line.split(_separator);
		}
		catch(FileNotFoundException e){
			System.err.println("Can't load " + _filepath);
		}
		catch(IOException e){
			System.err.println("Error reading file " + _filepath);
		}

		throw new NoSuchElementException();
	}
}
