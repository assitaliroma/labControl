package ve.unefa.labControl.persistence.rowmapper;

import ve.unefa.labControl.domain.Materia;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;


public class MateriaRowMapper implements RowMapper<Materia> {

    public Materia mapRow(ResultSet resultSet, int i) throws SQLException {

    	Materia content = new Materia();
    	content.setId(resultSet.getString("id"));
        content.setNombre((String)resultSet.getString("nombre"));
        content.setCoordinacion((String)resultSet.getString("coordinacion_id"));
        
        return content;
    }
}