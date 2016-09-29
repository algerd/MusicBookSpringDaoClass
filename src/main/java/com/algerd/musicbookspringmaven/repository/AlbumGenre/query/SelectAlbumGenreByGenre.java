
package com.algerd.musicbookspringmaven.repository.AlbumGenre.query;

import com.algerd.musicbookspringmaven.repository.BaseRepository;
import com.algerd.musicbookspringmaven.repository.Album.AlbumEntity;
import com.algerd.musicbookspringmaven.repository.AlbumGenre.AlbumGenreEntity;
import com.algerd.musicbookspringmaven.repository.Artist.ArtistEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

@Component("selectAlbumGenreByGenre")
public class SelectAlbumGenreByGenre extends MappingSqlQuery<AlbumGenreEntity> {
    
    private BaseRepository<AlbumGenreEntity> repository;
    
    private static final String QUERY = 
        "select "
                + "album_genre.id as id, "
                + "album_genre.id_genre as id_genre, "
                + "album_genre.id_album as id_album, "             
                + "album.name as name, "
                + "album.year as year, "               
                + "album.rating as rating, "
                + "album.id_artist as id_artist, "
                + "artist.name as artist_name "               
            + "from album_genre "
                + "left join album on album_genre.id_album = album.id "
                + "left join artist on album.id_artist = artist.id "                
            + "where album_genre.id_genre = ?";
    
    @Autowired 
    public SelectAlbumGenreByGenre(DataSource dataSource) {
        super(dataSource, QUERY);
        super.declareParameter(new SqlParameter("id_genre", Types.INTEGER));
        compile();
    }
    
    @Override
    protected AlbumGenreEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            AlbumGenreEntity albumGenre = repository.getEntity(resultSet);
            
            AlbumEntity album = new AlbumEntity();
            album.setId(albumGenre.getId_album());
            album.setName(resultSet.getString("name"));
            album.setRating(resultSet.getInt("rating"));
            album.setId_artist(resultSet.getInt("id_artist"));

            ArtistEntity artist = new ArtistEntity();
            artist.setId(album.getId_artist());
            artist.setName(resultSet.getString("artist_name"));

            album.setArtist(artist);
            albumGenre.setAlbum(album);
            return albumGenre;
    }
    
    public void setRepository(BaseRepository<AlbumGenreEntity> repository) {
        this.repository = repository;
    }
    
}
