/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 04/12/17
 */
package fr.presentapi.dao;

public class Status {
    public static long DEFAULT_ID = -1;
    private long _statusId;
    private String _label;

    public Status(long statusId, String label) {
        _statusId = statusId;
        _label = label;
    }

    public Status(String label) {
        _label = label;
        _statusId = Status.DEFAULT_ID;
    }
    
    public long getStatusId() {
        return _statusId;
    }

    public void setStatusId(int statusId) {
        _statusId = statusId;
    }

    public String getLabel() {
        return _label;
    }

    public void setLabel(String label) {
        _label = label;
    }   
}