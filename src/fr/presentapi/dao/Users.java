/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 04/12/17
 */

package fr.presentapi.dao;


public class Users {
    public static long DEFAULT_ID = -1;
    private int _numEtu;
    private String _salt;
    private long _statusId;

    public Users(int numEtu, String salt, long statusId) {
        _numEtu = numEtu;
        _salt = salt;
        _statusId = statusId;
    }

    public Users(int numEtu, String salt) {
        _numEtu = numEtu;
        _salt = salt;
        _statusId = Users.DEFAULT_ID;
    }
    
    

    public int getNumEtu() {
        return _numEtu;
    }

    public void setNumEtu(int numEtu) {
        _numEtu = numEtu;
    }

    public String getSalt() {
        return _salt;
    }

    public void setSalt(String salt) {
        _salt = salt;
    }

    public long getStatusId() {
        return _statusId;
    }

    public void setStatusId(long statusId) {
        _statusId = statusId;
    }
}