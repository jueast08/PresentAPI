/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 20/11/17
 */

package test.java.fr.presentapi.dao;

import fr.presentapi.dao.DbConnection;
import java.sql.Connection;

public class DbConnectionMock{
	public Connection getConnection(){
		return DbConnection.getConnection();
	}	
}
