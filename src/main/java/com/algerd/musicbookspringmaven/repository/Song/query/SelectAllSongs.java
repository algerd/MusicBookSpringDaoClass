
package com.algerd.musicbookspringmaven.repository.Song.query;

import com.algerd.musicbookspringmaven.repository.Album.AlbumEntity;
import com.algerd.musicbookspringmaven.repository.Artist.ArtistEntity;
import com.algerd.musicbookspringmaven.repository.Song.SongEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

@Component("selectAllSongs")
public class SelectAllSongs extends MappingSqlQuery<SongEntity> {
        
    private static final String QUERY = 
        "select "
            + "song.id as id, "
            + "song.id_album as id_album, "
            + "song.name as name, "
            + "song.rating as rating, "
            + "album.name as album_name, "
            + "album.year as year, "
            + "album.id_artist as id_artist, "
            + "artist.name as artist_name "
        + "from song "
            + "left join album on song.id_album = album.id "
            + "left join artist on album.id_artist = artist.id ";
    
    
    @Autowired        
    public SelectAllSongs(DataSource dataSource) {
        super(dataSource, QUERY);
        compile();
    }
     
    @Override
    protected SongEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        SongEntity song = new SongEntity();
        song.setId(resultSet.getInt("id"));
        song.setId_album(resultSet.getInt("id_album"));
        song.setName(resultSet.getString("name"));
        song.setRating(resultSet.getInt("rating"));

        AlbumEntity album = new AlbumEntity();
        album.setId(song.getId_album());
        album.setName(resultSet.getString("album_name"));
        album.setYear(resultSet.getInt("year"));
        album.setId_artist(resultSet.getInt("id_artist"));

        ArtistEntity artist = new ArtistEntity();
        artist.setId(album.getId_artist());
        artist.setName(resultSet.getString("artist_name"));

        album.setArtist(artist);
        song.setAlbum(album);
        return song;
    }

}
