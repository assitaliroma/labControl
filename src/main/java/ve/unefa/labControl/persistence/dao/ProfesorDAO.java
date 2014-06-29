/**
 * 
 */
package ve.unefa.labControl.persistence.dao;

import java.util.List;

import ve.unefa.labControl.domain.Profesor;

/**
 * @author desarrollador
 *
 */
public interface ProfesorDAO {
	 public List<Profesor> lookupProfesor(String filterString) throws Exception;
	 
	 public void insertProfesor(Profesor p) throws Exception;

	public void deleteProfesor(String id);

//	    public void insertCategory(String name, String description) throws Exception;
//
//	    public void deleteCategory(Long id) throws Exception;
//
//	    public void updateCategory(Long id, String name, String description) throws Exception;

}
