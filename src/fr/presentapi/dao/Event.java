/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 04/12/17
 */
package fr.presentapi.dao;

public class Event {

    public static long DEFAULT_ID = -1;
    private long _eventId;
    private long _uid;
    private String _label;

    public Event(long eventId, long uid, String label) {
        _eventId = eventId;
        _uid = uid;
        _label = label;
    }

    public Event(long uid, String label) {
        _uid = uid;
        _label = label;
        _eventId = Event.DEFAULT_ID;
    }

    public long getEventId() {
        return _eventId;
    }

    public void setEventId(long eventId) {
        _eventId = eventId;
    }

    public long getUid() {
        return _uid;
    }

    public void setUid(long uid) {
        _uid = uid;
    }

    public String getLabel() {
        return _label;
    }

    public void setLabel(String label) {
        _label = label;
    }
}