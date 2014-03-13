package service;

import java.util.List;

import util.TableManipulation;

public interface MetaService extends TableManipulation{
	
	Integer addEntity_TS(Object obj);
	void updateEntity_TS(Integer Id, Object obj_new);
	
}
