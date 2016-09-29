
package com.algerd.musicbookspringmaven.repository.MusicianInstrument.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

@Component("countMusicianInstrumentByInstrument")
public class CountMusicianInstrumentByInstrument extends MappingSqlQuery<Integer> {

    private static final String QUERY =
        "select count(id) from musician_instrument where id_instrument = ?"; 
    
    @Autowired        
    public CountMusicianInstrumentByInstrument(DataSource dataSource) {
        super(dataSource, QUERY);
        super.declareParameter(new SqlParameter("id_instrument", Types.INTEGER));
        compile();
    }

    @Override
    protected Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getInt(1);
    }
}
