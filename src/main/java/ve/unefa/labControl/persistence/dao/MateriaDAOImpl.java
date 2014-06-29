/**
 * 
 */
package ve.unefa.labControl.persistence.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import ve.unefa.labControl.domain.Materia;
import ve.unefa.labControl.persistence.rowmapper.MateriaRowMapper;

/**
 * @author desarrollador
 *
 */
public class MateriaDAOImpl implements MateriaDAO {
	private JdbcTemplate jdbcTemplate;
	//private DataFieldMaxValueIncrementer bookIncrementer;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
//	public void setBookIncrementer(DataFieldMaxValueIncrementer bookIncrementer) {
//	this.bookIncrementer = bookIncrementer;
//	}

	public List<Materia> lookupMateria(String filterString) throws Exception {
		String sql = null;
		if(filterString == null || filterString.length() == 0)
			sql = "SELECT id, nombre, coordinacion_id FROM materias ORDER BY nombre ASC;";
		else
			sql = "SELECT id, nombre, coordinacion_id FROM materias WHERE id LIKE upper('%"+filterString+
				"%') OR lower(nombre) LIKE lower('%"+filterString+"%') ORDER BY nombre ASC;";
		return jdbcTemplate.query(sql, new MateriaRowMapper());
	}
	
	public void insertMateria(Materia m) throws Exception {
		jdbcTemplate.execute("INSERT INTO materias VALUES"+
				"(upper('"+m.getId()+"'),"+
				"initcap('"+m.getNombre()+"'),"+
				"upper('"+m.getCoordinacion()+"'));");
	}

	@Override
	public void deleteLaboratorio(String id) {
		jdbcTemplate.execute("DELETE FROM materias WHERE id='"+id+"';");
		
	}

}
