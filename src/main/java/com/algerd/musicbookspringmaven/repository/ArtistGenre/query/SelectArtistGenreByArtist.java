
package com.algerd.musicbookspringmaven.repository.ArtistGenre.query;

import com.algerd.musicbookspringmaven.dbDriver.BaseRepository;
import com.algerd.musicbookspringmaven.entity.Genre;
import com.algerd.musicbookspringmaven.repository.ArtistGenre.ArtistGenreEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

@Component("selectArtistGenreByArtist")
public class SelectArtistGenreByArtist extends MappingSqlQuery<ArtistGenreEntity> {

    private BaseRepository<ArtistGenreEntity> repository;
    
    private static final String QUERY = 
        "select "
                + "artist_genre.id, "
                + "artist_genre.id_genre, "
                + "artist_genre.id_artist, "              
                + "genre.name "
            + "from artist_genre "
                + "left join genre on artist_genre.id_genre = genre.id "
            + "where artist_genre.id_artist = ?";     
            
    @Autowired        
    public SelectArtistGenreByArtist(DataSource dataSource) {
        super(dataSource, QUERY);
        super.declareParameter(new SqlParameter("id_artist", Types.INTEGER));
        compile();
    }        
            
    @Override
    protected ArtistGenreEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        ArtistGenreEntity artistGenre = repository.getEntity(resultSet);
        Genre genre = new Genre();
        genre.setId(artistGenre.getId_genre());
        genre.setName(resultSet.getString("name"));
        artistGenre.setGenre(genre);
        return artistGenre;
    }

    public void setRepository(BaseRepository<ArtistGenreEntity> repository) {
        this.repository = repository;
    }        
}
