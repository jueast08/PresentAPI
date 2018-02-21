/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 21/02/18
 */
package fr.presentapi.dao;

public class User {

    private long _uid;
    private String _fname;
    private String _lname;
    private String _mail;
    private String _imei;
    private String _salt;
    private long _statusId;

    public User(long uid, String fname, String lname, String mail, String salt, long statusId,String imei) {
        _uid = uid;
        _fname = fname.toLowerCase();
        _lname = lname.toLowerCase();
        _mail = mail.toLowerCase();
        _imei = imei;
        _salt = salt;
        _statusId = statusId;
    }

    public User(String fname, String lname, String mail, String salt, long statusId, String imei) {
        _fname = fname.toLowerCase();
        _lname = lname.toLowerCase();
        _mail = mail.toLowerCase();
        _imei = imei;
        _salt = salt;
        _statusId = statusId;
    }

    public String getImei() {
        return _imei;
    }

    public long getUid() {
        return _uid;
    }

    public String getSalt() {
        return _salt;
    }

    public long getStatusId() {
        return _statusId;
    }

    public String getFName() {
        return _fname;
    }

    public String getLName() {
        return _lname;
    }

    public String getMail() {
        return _mail;
    }
}
