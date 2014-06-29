/**
 * 
 */
package ve.unefa.labControl.persistence.dao;

import java.util.List;

import ve.unefa.labControl.domain.Turno;

/**
 * @author desarrollador
 *
 */
public interface TurnoDAO {
	 public List<Turno> lookupTurno() throws Exception;
	 
	 public List<String> lookupTurnoKeys() throws Exception;
	 
	 public void insertTurno(Turno p) throws Exception;

//	    public void insertCategory(String name, String description) throws Exception;
//
//	    public void deleteCategory(Long id) throws Exception;
//
//	    public void updateCategory(Long id, String name, String description) throws Exception;

}
