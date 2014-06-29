/**
 * 
 */
package ve.unefa.labControl.persistence.dao;

import java.util.List;

import ve.unefa.labControl.domain.Materia;

/**
 * @author desarrollador
 *
 */
public interface MateriaDAO {
	 public List<Materia> lookupMateria(String filterString) throws Exception;
	 
	 public void insertMateria(Materia m) throws Exception;

	public void deleteLaboratorio(String id);

//	    public void insertCategory(String name, String description) throws Exception;
//
//	    public void deleteCategory(Long id) throws Exception;
//
//	    public void updateCategory(Long id, String name, String description) throws Exception;

}
