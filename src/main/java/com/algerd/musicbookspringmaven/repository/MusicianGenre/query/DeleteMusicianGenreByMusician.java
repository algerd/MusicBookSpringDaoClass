
package com.algerd.musicbookspringmaven.repository.MusicianGenre.query;

import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.stereotype.Component;

@Component("deleteMusicianGenreByMusician")
public class DeleteMusicianGenreByMusician extends SqlUpdate {
    
    private static final String QUERY = 
        "delete from musician_genre where id_musician = ?";
    
    @Autowired
    public DeleteMusicianGenreByMusician(DataSource dataSource) {
        super(dataSource, QUERY);
        super.declareParameter(new SqlParameter("id_musician", Types.INTEGER));
        compile();
    }  

}
