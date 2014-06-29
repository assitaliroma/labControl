package ve.unefa.labControl.persistence.rowmapper;

import ve.unefa.labControl.domain.Profesor;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;


public class ProfesorRowMapper implements RowMapper<Profesor> {

    public Profesor mapRow(ResultSet resultSet, int i) throws SQLException {

    	Profesor content = new Profesor();
    	content.setNombre((String)resultSet.getString("nombre"));
        content.setId(Long.parseLong(resultSet.getString("id")));
        content.setTelefono((String)resultSet.getString("telefono"));
        content.setPeriodo((String)resultSet.getString("periodo"));
        content.setTurno((String)resultSet.getString("turno"));
        content.setCoordinacion((String)resultSet.getString("coordinacion_id"));
        
        return content;
    }
}