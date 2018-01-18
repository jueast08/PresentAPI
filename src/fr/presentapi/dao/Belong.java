/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 18/01/18
 */
package fr.presentapi.dao;

public class Belong {
    private long _uid;
    private long _groupId;

    public Belong(long uid, long groupId) {
        _uid = uid;
        _groupId = groupId;
    }

    public long getUid() {
        return _uid;
    }

    public long getGroupId() {
        return _groupId;
    }

     
}
