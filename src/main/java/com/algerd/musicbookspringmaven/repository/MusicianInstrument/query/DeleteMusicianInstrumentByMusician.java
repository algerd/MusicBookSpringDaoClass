
package com.algerd.musicbookspringmaven.repository.MusicianInstrument.query;

import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.stereotype.Component;

@Component("DeleteMusicianInstrumentByMusician")
public class DeleteMusicianInstrumentByMusician extends SqlUpdate {
    
    private static final String QUERY = 
        "delete from musician_instrument where id_musician = ?";
    
    @Autowired
    public DeleteMusicianInstrumentByMusician(DataSource dataSource) {
        super(dataSource, QUERY);
        super.declareParameter(new SqlParameter("id_musician", Types.INTEGER));
        compile();
    }    
}
