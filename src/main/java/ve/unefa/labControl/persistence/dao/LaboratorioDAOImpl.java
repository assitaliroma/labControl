/**
 * 
 */
package ve.unefa.labControl.persistence.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import ve.unefa.labControl.domain.Laboratorio;
import ve.unefa.labControl.persistence.rowmapper.LaboratorioRowMapper;

/**
 * @author desarrollador
 *
 */
public class LaboratorioDAOImpl implements LaboratorioDAO {
	private JdbcTemplate jdbcTemplate;
	//private DataFieldMaxValueIncrementer bookIncrementer;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
//	public void setBookIncrementer(DataFieldMaxValueIncrementer bookIncrementer) {
//	this.bookIncrementer = bookIncrementer;
//	}

	public List<Laboratorio> lookupLaboratorio(String filterString) throws Exception {
		String sql = null;
		if(filterString == null || filterString.length() == 0)
			sql = "SELECT id, nombre, capacidad FROM laboratorios ORDER BY nombre ASC;";
		else
			sql = "SELECT id, nombre, capacidad FROM laboratorios WHERE lower(nombre) LIKE lower('%"+filterString+
				"%') ORDER BY nombre ASC;";
		return jdbcTemplate.query(sql, new LaboratorioRowMapper());
	}
	
	public void insertLaboratorio(Laboratorio l) throws Exception {
		jdbcTemplate.execute("INSERT INTO laboratorios VALUES"+
				"(nextval('laboratorio_seq'),"+
				"initcap('"+l.getNombre()+"'),"+
				l.getCapacidad()+");");
	}

	@Override
	public void deleteLaboratorio(String id) throws Exception {
		jdbcTemplate.execute("DELETE FROM laboratorios WHERE id='"+id+"';");
	}

}
