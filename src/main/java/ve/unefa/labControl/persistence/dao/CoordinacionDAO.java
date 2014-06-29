/**
 * 
 */
package ve.unefa.labControl.persistence.dao;

import java.util.List;

import ve.unefa.labControl.domain.Coordinacion;

/**
 * @author desarrollador
 *
 */
public interface CoordinacionDAO {
	 public List<Coordinacion> lookupCoordinacion(String filterString) throws Exception;
	 
	 public List<String> lookupCoordinacionForDisplay() throws Exception;
	 
	 public void insertCoordinacion(Coordinacion c) throws Exception;
	 
	 

//	    public void insertCategory(String name, String description) throws Exception;
//
	    public void deleteCoordinacion(String id) throws Exception;
//
//	    public void updateCategory(Long id, String name, String description) throws Exception;

}
