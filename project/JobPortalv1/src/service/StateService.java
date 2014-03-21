package service;

import java.util.ArrayList;
import java.util.List;

import data.dao.StateDAO;
import data.entity.State;

public class StateService {
	static final StateDAO stateDao = new StateDAO();
	
	public static List<String> all() {
		List<State> states = stateDao.listEntities();
		List<String> statesString = new ArrayList<String>();
		
		for(State state : states) {
			statesString.add(state.getState_n());
		}
		
		return statesString;
	}

}

