
package com.algerd.musicbookspringmaven.repository.albumgenre.query;

import com.algerd.musicbookspringmaven.dbDriver.BaseRepository;
import com.algerd.musicbookspringmaven.entity.AlbumGenre;
import com.algerd.musicbookspringmaven.entity.Genre;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

@Component("selectAlbumGenreByAlbum")
public class SelectAlbumGenreByAlbum extends MappingSqlQuery<AlbumGenre> {
    
    private BaseRepository<AlbumGenre> repository;
    
    private static final String SELECT_ALBUMGENRE_BY_ALBUM = 
        "select "
                + "album_genre.id, "
                + "album_genre.id_genre, "
                + "album_genre.id_album, "              
                + "genre.name "
            + "from album_genre "
                + "left join genre on album_genre.id_genre = genre.id "
            + "where album_genre.id_album = ?";  
    

    @Autowired        
    public SelectAlbumGenreByAlbum(DataSource dataSource) {
        super(dataSource, SELECT_ALBUMGENRE_BY_ALBUM);
        declareParameter(new SqlParameter("id_album", Types.INTEGER));
        compile();
    }
     
    protected AlbumGenre mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        AlbumGenre albumGenre = repository.getEntity(resultSet);
        Genre genre = new Genre();
        genre.setId(albumGenre.getId_genre());
        genre.setName(resultSet.getString("name"));
        albumGenre.setGenre(genre);
        return albumGenre;
    }

    public void setRepository(BaseRepository<AlbumGenre> repository) {
        this.repository = repository;
    }
    
}
