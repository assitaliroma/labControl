/**
 * 
 */
package ve.unefa.labControl.persistence.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import ve.unefa.labControl.domain.Profesor;
import ve.unefa.labControl.persistence.rowmapper.ProfesorRowMapper;

/**
 * @author desarrollador
 *
 */
public class ProfesorDAOImpl implements ProfesorDAO {
	private JdbcTemplate jdbcTemplate;
	//private DataFieldMaxValueIncrementer bookIncrementer;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
//	public void setBookIncrementer(DataFieldMaxValueIncrementer bookIncrementer) {
//	this.bookIncrementer = bookIncrementer;
//	}

	public List<Profesor> lookupProfesor(String filterString) throws Exception {
		String sql = null;
		if(filterString == null || filterString.length() == 0)
			sql = "SELECT id, nombre, periodo, telefono, turno, coordinacion_id FROM profesores ORDER BY nombre ASC;";
		else
			sql = "SELECT id, nombre, periodo, telefono, turno, coordinacion_id FROM profesores WHERE lower(nombre) LIKE lower('%"+filterString+
				"%') ORDER BY nombre ASC;";
		return jdbcTemplate.query(sql, new ProfesorRowMapper());
	}
	
	public void insertProfesor(Profesor p) throws Exception {
		jdbcTemplate.execute("INSERT INTO profesores VALUES"+
				"(nextval('profesores_seq'),"+
				"initcap('"+p.getNombre()+"'),"+
				"initcap('"+p.getPeriodo()+"'),'"+
				p.getTelefono()+"',"+
				"initcap('"+p.getTurno()+"'),"+
				"upper('"+p.getCoordinacion()+"'));");
	}

	@Override
	public void deleteProfesor(String id) {
		jdbcTemplate.execute("DELETE FROM profesores WHERE id='"+id+"';");
		
	}

}
