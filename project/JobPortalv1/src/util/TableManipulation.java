package util;

import java.util.List;

public interface TableManipulation {
	
	Integer addEntity(Object obj);
	void updateEntity(Integer Id, Object obj_new);
	void deleteEntity(Integer Id);
	List listEntities();
}
