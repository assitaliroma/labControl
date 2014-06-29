/**
 * 
 */
package ve.unefa.labControl.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import ve.unefa.labControl.domain.Turno;
import ve.unefa.labControl.persistence.rowmapper.TurnoRowMapper;

/**
 * @author desarrollador
 *
 */
public class TurnoDAOImpl implements TurnoDAO {
	private JdbcTemplate jdbcTemplate;
	//private DataFieldMaxValueIncrementer bookIncrementer;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
//	public void setBookIncrementer(DataFieldMaxValueIncrementer bookIncrementer) {
//	this.bookIncrementer = bookIncrementer;
//	}

	public List<Turno> lookupTurno() throws Exception {
		String sql = null;
		sql = "SELECT turno_key, turno_description FROM turnos ORDER BY turno_key;";
		
		return jdbcTemplate.query(sql, new TurnoRowMapper());
	}

	public List<String> lookupTurnoKeys() throws Exception {
		List<Turno> list = lookupTurno();
		List<String> retList = new ArrayList<String>();
		for(Turno t : list)
			retList.add(t.getName());
		
		return retList;
	}
	
	public void insertTurno(Turno t) throws Exception {
		jdbcTemplate.execute("INSERT INTO turnos VALUES"+
				"(initcap('"+t.getName()+"'),"+
				"lower('"+t.getDescription()+"'));");
	}

}
