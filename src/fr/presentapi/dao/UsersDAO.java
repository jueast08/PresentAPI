/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 23/11/17
 */
package fr.presentapi.dao;

public class UsersDAO {
    private  DbConnection connexion;
    
    boolean insertUsers(Users users) {
        
        String cmd = "INSERT INTO Users (numEtu,salt,statusId) VALUES"
                    + "(" + users.getNumEtu()+ ","
                    + users.getSalt() + "," +
                    + users.getStatusId() +")" ;
         
        return true;
    }
    
    boolean updateUsers(Users users) {
        return true;
    }
    
    boolean deleteUsers(Users users) {
        return true;
    }
    
    
}
