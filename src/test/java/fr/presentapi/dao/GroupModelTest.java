/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 20/11/17
 */

package test.java.fr.presentapi.dao;

import fr.presentapi.dao.Group;
import fr.presentapi.dao.GroupModel;
import java.sql.SQLException;
import static org.junit.Assert.assertEquals;
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
	private GroupModel _model;
	private Group _group;

	@Before
	public void setup() throws SQLException{
		_group = new Group(1, "Projet present");
		when(_model.insert(any(Group.class))).thenReturn(true);
		when(_model.find(any(String.class))).thenReturn(_group);
	}

	@Test
	public void insertFindGroup(){
		_model.insert(_group);
		Group g = _model.find("Projet present");
		assertEquals(_group, g);
	}	
}
