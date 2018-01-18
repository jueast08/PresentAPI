/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 04/12/17
 */

package fr.presentapi.dao;


public class User {
    private long _numEtu;
	private String _fname;
	private String _lname;
	private String _mail;
    private String _salt;
    private long _statusId;

    public User(long numEtu, String fname, String lname, String mail, String salt, long statusId) {
        _numEtu = numEtu;
		_fname = fname.toLowerCase();
		_lname = lname.toLowerCase();
		_mail = mail.toLowerCase();
        _salt = salt;
        _statusId = statusId;
    }

    public long getNumEtu() {
        return _numEtu;
    }

    public String getSalt() {
        return _salt;
    }

    public long getStatusId() {
        return _statusId;
    }

	public String getFName(){
		return _fname;
	}

	public String getLName(){
		return _lname;
	}

	public String getMail(){
		return _mail;
	}
}