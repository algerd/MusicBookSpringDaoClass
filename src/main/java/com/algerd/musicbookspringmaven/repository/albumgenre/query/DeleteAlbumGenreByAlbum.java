
package com.algerd.musicbookspringmaven.repository.albumgenre.query;

import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.stereotype.Component;

@Component("deleteAlbumGenreByAlbum")
public class DeleteAlbumGenreByAlbum extends SqlUpdate {

    private static final String DELETE_ALBUMGENRE_BY_ALBUM = 
        "delete from album_genre where id_album = ?";
    
    @Autowired
    public DeleteAlbumGenreByAlbum(DataSource dataSource) {
        super(dataSource, DELETE_ALBUMGENRE_BY_ALBUM);
        declareParameter(new SqlParameter("id_album", Types.INTEGER));
        compile();
    }
       
}
