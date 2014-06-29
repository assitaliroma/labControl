package ve.unefa.labControl.persistence.rowmapper;

import ve.unefa.labControl.domain.Turno;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;


public class TurnoRowMapper implements RowMapper<Turno> {

    public Turno mapRow(ResultSet resultSet, int i) throws SQLException {

    	Turno content = new Turno();
    	content.setName(resultSet.getString("turno_key"));
        content.setDescription(resultSet.getString("turno_description"));
        
        return content;
    }
}