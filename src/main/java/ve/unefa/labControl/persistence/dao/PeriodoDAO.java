/**
 * 
 */
package ve.unefa.labControl.persistence.dao;

import java.util.List;

import ve.unefa.labControl.domain.Periodo;

/**
 * @author desarrollador
 *
 */
public interface PeriodoDAO {
	 public List<Periodo> lookupPeriodo() throws Exception;
	 
	 public List<String> lookupPeriodoKeys() throws Exception;
	 
	 public void insertPeriodo(Periodo p) throws Exception;

//	    public void insertCategory(String name, String description) throws Exception;
//
//	    public void deleteCategory(Long id) throws Exception;
//
//	    public void updateCategory(Long id, String name, String description) throws Exception;

}
