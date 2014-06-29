package ve.unefa.labControl.persistence.rowmapper;

import ve.unefa.labControl.domain.Coordinacion;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;


public class CoordinacionRowMapper implements RowMapper<Coordinacion> {

    public Coordinacion mapRow(ResultSet resultSet, int i) throws SQLException {

    	Coordinacion content = new Coordinacion();
    	content.setCarrera((String)resultSet.getString("carrera"));
        content.setId((String)resultSet.getString("id"));
        content.setTelefono((String)resultSet.getString("telefono"));
        content.setCoordinador((String)resultSet.getString("coordinador"));
        content.setTurno((String)resultSet.getString("turno"));
        
        return content;
    }
}