/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 04/12/17
 */
package fr.presentapi.dao;

import java.sql.Connection;

public class EventDAO {
    private /*final*/ Connection _connexion;
    
    public EventDAO(){
		_connexion = DbConnection.getConnection();
    }
}
