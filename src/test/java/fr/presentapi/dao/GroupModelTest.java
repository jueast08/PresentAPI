/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 20/11/17
 */

package test.java.fr.presentapi.dao;

import fr.presentapi.dao.DbConnection;
import fr.presentapi.dao.Group;
import fr.presentapi.dao.GroupModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GroupModelTest{
	@Mock
	private DbConnectionMock _dbconn;
	@Mock
	private Connection _conn;
	@Mock
	private PreparedStatement _stmt;
	@Mock
	private ResultSet _rs;

	private Group _group;

	@Before
	public void setup() throws SQLException{
		assertNotNull(_dbconn);
		when(_conn.prepareStatement(any(String.class))).thenReturn(_stmt);
		when(_dbconn.getConnection()).thenReturn(_conn);


		_group = new Group(1, "Projet present");
		when(_rs.next()).thenReturn(true);
		// when(_rs.getLong(1)).thenReturn(_group.getId());
		when(_rs.getString("label")).thenReturn(_group.getLabel());
		when(_stmt.executeQuery()).thenReturn(_rs);
	}

	@Test
	public void insertGroup(){
		GroupModel model = new GroupModel();
		model.insert(_group);
		Group g = model.find(1L);
		assertEquals(_group, g);
	}	
}
