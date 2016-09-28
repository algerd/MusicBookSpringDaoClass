
package com.algerd.musicbookspringmaven.repository.albumgenre.query;

import com.algerd.musicbookspringmaven.dbDriver.BaseRepository;
import com.algerd.musicbookspringmaven.entity.Album;
import com.algerd.musicbookspringmaven.entity.AlbumGenre;
import com.algerd.musicbookspringmaven.entity.Artist;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

@Component("selectAlbumGenreByGenre")
public class SelectAlbumGenreByGenre extends MappingSqlQuery<AlbumGenre> {
    
    private BaseRepository<AlbumGenre> repository;
    
    private static final String SELECT_ALBUMGENRE_BY_GENRE = 
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
        super(dataSource, SELECT_ALBUMGENRE_BY_GENRE);
        declareParameter(new SqlParameter("id_genre", Types.INTEGER));
        compile();
    }
    
    protected AlbumGenre mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            AlbumGenre albumGenre = repository.getEntity(resultSet);
            
            Album album = new Album();
            album.setId(albumGenre.getId_album());
            album.setName(resultSet.getString("name"));
            album.setRating(resultSet.getInt("rating"));
            album.setId_artist(resultSet.getInt("id_artist"));

            Artist artist = new Artist();
            artist.setId(album.getId_artist());
            artist.setName(resultSet.getString("artist_name"));

            album.setArtist(artist);
            albumGenre.setAlbum(album);
            return albumGenre;
    }
    
    public void setRepository(BaseRepository<AlbumGenre> repository) {
        this.repository = repository;
    }
    
}
