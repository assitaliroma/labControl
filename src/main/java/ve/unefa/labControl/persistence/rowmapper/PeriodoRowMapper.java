package ve.unefa.labControl.persistence.rowmapper;

import ve.unefa.labControl.domain.Periodo;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;


public class PeriodoRowMapper implements RowMapper<Periodo> {

    public Periodo mapRow(ResultSet resultSet, int i) throws SQLException {

    	Periodo content = new Periodo();
    	content.setName(resultSet.getString("periodo_key"));
        content.setDescription(resultSet.getString("periodo_description"));
        
        return content;
    }
}