package services;

import java.util.List;

import data.TableManipulation;

public interface MetaService extends TableManipulation{
	
	Integer addEntity_TS(Object obj);
	void updateEntity_TS(Integer Id, Object obj_new);
	
}
