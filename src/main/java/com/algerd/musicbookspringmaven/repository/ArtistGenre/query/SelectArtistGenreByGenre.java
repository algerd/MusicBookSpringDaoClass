
package com.algerd.musicbookspringmaven.repository.ArtistGenre.query;

import com.algerd.musicbookspringmaven.repository.BaseRepository;
import com.algerd.musicbookspringmaven.repository.Artist.ArtistEntity;
import com.algerd.musicbookspringmaven.repository.ArtistGenre.ArtistGenreEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

@Component("selectArtistGenreByGenre")
public class SelectArtistGenreByGenre extends MappingSqlQuery<ArtistGenreEntity>  {
    
    private BaseRepository<ArtistGenreEntity> repository;
    
    private static final String QUERY = 
        "select "
                + "artist_genre.id, "
                + "artist_genre.id_genre, "
                + "artist_genre.id_artist, "             
                + "artist.name, "
                + "artist.rating "
            + "from artist_genre "
                + "left join artist on artist_genre.id_artist = artist.id "
            + "where artist_genre.id_genre = ?";    
            
    @Autowired 
    public SelectArtistGenreByGenre(DataSource dataSource) {
        super(dataSource, QUERY);
        super.declareParameter(new SqlParameter("id_genre", Types.INTEGER));
        compile();
    }
    
    @Override
    protected ArtistGenreEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        ArtistGenreEntity artistGenre = repository.getEntity(resultSet);
        ArtistEntity artist = new ArtistEntity();
        artist.setId(artistGenre.getId_artist());
        artist.setName(resultSet.getString("name"));
        artist.setRating(resultSet.getInt("rating"));
        artistGenre.setArtist(artist);
        return artistGenre;   
    }
    
    public void setRepository(BaseRepository<ArtistGenreEntity> repository) {
        this.repository = repository;
    }
      
}
