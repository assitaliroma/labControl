/**
 * 
 */
package ve.unefa.labControl.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import ve.unefa.labControl.domain.Coordinacion;
import ve.unefa.labControl.gui.common.Constants;
import ve.unefa.labControl.persistence.rowmapper.CoordinacionRowMapper;

/**
 * @author desarrollador
 *
 */
public class CoordinacionDAOImpl implements CoordinacionDAO {
	private JdbcTemplate jdbcTemplate;
	//private DataFieldMaxValueIncrementer bookIncrementer;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
//	public void setBookIncrementer(DataFieldMaxValueIncrementer bookIncrementer) {
//	this.bookIncrementer = bookIncrementer;
//	}

	public List<Coordinacion> lookupCoordinacion(String filterString) throws Exception {
		String sql = null;
		if(filterString == null || filterString.length() == 0)
			sql = "SELECT id, carrera, telefono, coordinador, turno FROM coordinaciones ORDER BY carrera ASC;";
		else
			sql = "SELECT id, carrera, telefono, coordinador, turno FROM coordinaciones WHERE id LIKE upper('%"+filterString+
				"%') OR lower(carrera) LIKE lower('%"+filterString+"%') ORDER BY carrera ASC;";
		return jdbcTemplate.query(sql, new CoordinacionRowMapper());
	}

	public List<String> lookupCoordinacionForDisplay() throws Exception {
		List<Coordinacion> list = lookupCoordinacion(null);
		List<String> retList = new ArrayList<String>();
		for(Coordinacion c : list)
			retList.add(c.getCarrera()+Constants.TOKEN_SEPARATOR+c.getId());
		
		return retList;
	}
	
	public void insertCoordinacion(Coordinacion c) throws Exception {
		jdbcTemplate.execute("INSERT INTO coordinaciones VALUES"+
				"(upper('"+c.getId()+"'),"+
				"initcap('"+c.getCarrera()+"'),'"+
				c.getTelefono()+"',"+
				"initcap('"+c.getCoordinador()+"'),"+
				"initcap('"+c.getTurno()+"'));");
	}
	
	public void deleteCoordinacion(String id) throws Exception{
		jdbcTemplate.execute("DELETE FROM coordinaciones WHERE id='"+id+"';");
	}

}
