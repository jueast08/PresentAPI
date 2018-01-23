/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 04/12/17
 */
package fr.presentapi.dao;


public class Code {
    public static int DEFAULT_DURATION = -1;
    private String _code;
    private String _creation; //timestamp
    private int _duration;

    public Code(String code, String creation, int duration) {
        _code = code;
        _creation = creation;
        _duration = duration;
    }

    public Code(String code, String creation) {
        _code = code;
        _creation = creation;
        _duration = DEFAULT_DURATION;
    }

    public int getDuration() {
        return _duration;
    }

    public String getCode() {
        return _code;
    }

    public String getCreation() {
        return _creation;
    }   
}
