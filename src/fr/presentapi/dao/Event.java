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
    private int _numEtu;
    private String _label;

    public Event(long eventId, int numEtu, String label) {
        _eventId = eventId;
        _numEtu = numEtu;
        _label = label;
    }

    public Event(int numEtu, String label) {
        _numEtu = numEtu;
        _label = label;
        _eventId = Event.DEFAULT_ID;
    }
    

    public long getEventId() {
        return _eventId;
    }

    public void setEventId(long eventId) {
        _eventId = eventId;
    }

    public int getNumEtu() {
        return _numEtu;
    }

    public void setNumEtu(int numEtu) {
        _numEtu = numEtu;
    }

    public String getLabel() {
        return _label;
    }

    public void setLabel(String label) {
        _label = label;
    }
    
}