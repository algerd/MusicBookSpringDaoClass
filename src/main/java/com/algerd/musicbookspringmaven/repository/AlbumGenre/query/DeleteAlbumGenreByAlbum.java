
package com.algerd.musicbookspringmaven.repository.AlbumGenre.query;

import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.stereotype.Component;

@Component("deleteAlbumGenreByAlbum")
public class DeleteAlbumGenreByAlbum extends SqlUpdate {

    private static final String QUERY = 
        "delete from album_genre where id_album = ?";
    
    @Autowired
    public DeleteAlbumGenreByAlbum(DataSource dataSource) {
        super(dataSource, QUERY);
        super.declareParameter(new SqlParameter("id_album", Types.INTEGER));
        compile();
    }     
}
