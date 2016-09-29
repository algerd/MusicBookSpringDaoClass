
package com.algerd.musicbookspringmaven.repository.SongGenre.query;

import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.stereotype.Component;

@Component("deleteSongGenreBySong")
public class DeleteSongGenreBySong extends SqlUpdate {
    
    private static final String QUERY = 
        "delete from song_genre where id_song = ?";
    
    @Autowired
    public DeleteSongGenreBySong(DataSource dataSource) {
        super(dataSource, QUERY);
        super.declareParameter(new SqlParameter("id_song", Types.INTEGER));
        compile();
    }  
    
}
