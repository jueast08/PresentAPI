/**
 * @author Quentin Debroise <debroise@ecole.ensicaen.fr>
 * @author Coline Smagghe <smagghe@ecole.ensicaen.fr>
 *
 * @version 0.0.1 - Last modified: 07/12/17
 */

package test.java.fr.presentapi.dao;

import fr.presentapi.dao.BelongModel;
import fr.presentapi.dao.Group;
import fr.presentapi.dao.MockUser;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BelongModelTest{
	@Mock
	private BelongModel _model;	
	private BelongModel _belong;
	private Group _g1;
	private MockUser _u1;

	@Before
	public void setup(){
		_g1 = new Group(1, "Projet Present");
		_u1 = new MockUser();
		List<Group> groups = new ArrayList<>();
		List<MockUser> users = new ArrayList<>();
		groups.add(_g1);
		users.add(_u1);

		/*
		when(_model.insert(any(MockUser.class), any(MockUser.class))).thenReturn(true);
		when(_model.findGroupForUser(1)).thenReturn(groups);
		when(_model.findGroupForUser(0)).thenReturn(new ArrayList<Group>());
		when(_model.findUserInGroup(0)).thenReturn(new ArrayList<MockUser>());
		when(_model.findUserInGroup(0)).thenReturn(users);
		*/
	}

	@Test
	public void findGroups(){
	}

	@Test
	public void findUsers(){
	}
}
