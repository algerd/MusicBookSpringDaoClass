
package com.algerd.musicbookspringmaven.repository.ArtistGenre.query;

import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.stereotype.Component;

@Component("deleteArtistGenreByArtist")
public class DeleteArtistGenreByArtist extends SqlUpdate {
    
    private static final String QUERY = 
        "delete from artist_genre where id_artist = ?";
    
    @Autowired
    public DeleteArtistGenreByArtist(DataSource dataSource) {
        super(dataSource, QUERY);
        super.declareParameter(new SqlParameter("id_artist", Types.INTEGER));
        compile();
    }
}
