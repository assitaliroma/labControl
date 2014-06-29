package ve.unefa.labControl.persistence.rowmapper;

import ve.unefa.labControl.domain.Laboratorio;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;


public class LaboratorioRowMapper implements RowMapper<Laboratorio> {

    public Laboratorio mapRow(ResultSet resultSet, int i) throws SQLException {

    	Laboratorio content = new Laboratorio();
    	content.setId(Long.parseLong(resultSet.getString("id")));
        content.setNombre((String)resultSet.getString("nombre"));
        content.setCapacidad(Integer.parseInt(resultSet.getString("capacidad")));
        
        return content;
    }
}