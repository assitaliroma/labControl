/**
 * 
 */
package ve.unefa.labControl.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import ve.unefa.labControl.domain.Periodo;
import ve.unefa.labControl.persistence.rowmapper.PeriodoRowMapper;

/**
 * @author desarrollador
 *
 */
public class PeriodoDAOImpl implements PeriodoDAO {
	private JdbcTemplate jdbcTemplate;
	//private DataFieldMaxValueIncrementer bookIncrementer;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
//	public void setBookIncrementer(DataFieldMaxValueIncrementer bookIncrementer) {
//	this.bookIncrementer = bookIncrementer;
//	}

	public List<Periodo> lookupPeriodo() throws Exception {
		String sql = null;
		sql = "SELECT periodo_key, periodo_description FROM periodos ORDER BY periodo_key;";
		
		return jdbcTemplate.query(sql, new PeriodoRowMapper());
	}

	public List<String> lookupPeriodoKeys() throws Exception {
		List<Periodo> list = lookupPeriodo();
		List<String> retList = new ArrayList<String>();
		for(Periodo p : list)
			retList.add(p.getName());
		
		return retList;
	}
	
	public void insertPeriodo(Periodo p) throws Exception {
		jdbcTemplate.execute("INSERT INTO profesores VALUES"+
				"(nextval('profesores_seq'),"+
				"initcap('"+p.getName()+"'),"+
				"lower('"+p.getDescription()+"'));");
	}

}
