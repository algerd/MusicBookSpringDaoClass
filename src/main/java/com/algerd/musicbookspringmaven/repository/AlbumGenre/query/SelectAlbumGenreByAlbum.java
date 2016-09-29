
package com.algerd.musicbookspringmaven.repository.AlbumGenre.query;

import com.algerd.musicbookspringmaven.repository.BaseRepository;
import com.algerd.musicbookspringmaven.repository.AlbumGenre.AlbumGenreEntity;
import com.algerd.musicbookspringmaven.repository.Genre.GenreEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

@Component("selectAlbumGenreByAlbum")
public class SelectAlbumGenreByAlbum extends MappingSqlQuery<AlbumGenreEntity> {
    
    private BaseRepository<AlbumGenreEntity> repository;
    
    private static final String QUERY = 
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
        super(dataSource, QUERY);
        super.declareParameter(new SqlParameter("id_album", Types.INTEGER));
        compile();
    }
     
    @Override
    protected AlbumGenreEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        AlbumGenreEntity albumGenre = repository.getEntity(resultSet);
        GenreEntity genre = new GenreEntity();
        genre.setId(albumGenre.getId_genre());
        genre.setName(resultSet.getString("name"));
        albumGenre.setGenre(genre);
        return albumGenre;
    }

    public void setRepository(BaseRepository<AlbumGenreEntity> repository) {
        this.repository = repository;
    }
    
}
