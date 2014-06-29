/**
 * 
 */
package ve.unefa.labControl.persistence.dao;

import java.util.List;

import ve.unefa.labControl.domain.Laboratorio;

/**
 * @author desarrollador
 *
 */
public interface LaboratorioDAO {
	 public List<Laboratorio> lookupLaboratorio(String filterString) throws Exception;
	 
	 public void insertLaboratorio(Laboratorio l) throws Exception;

//	    public void insertCategory(String name, String description) throws Exception;
//
	    public void deleteLaboratorio(String id) throws Exception;
//
//	    public void updateCategory(Long id, String name, String description) throws Exception;

}
