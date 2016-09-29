
package com.algerd.musicbookspringmaven.repository.SongGenre.query;

import com.algerd.musicbookspringmaven.repository.BaseRepository;
import com.algerd.musicbookspringmaven.repository.Album.AlbumEntity;
import com.algerd.musicbookspringmaven.repository.Artist.ArtistEntity;
import com.algerd.musicbookspringmaven.repository.Song.SongEntity;
import com.algerd.musicbookspringmaven.repository.SongGenre.SongGenreEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

@Component("selectSongGenreByGenre")
public class SelectSongGenreByGenre extends MappingSqlQuery<SongGenreEntity> {
    
    private BaseRepository<SongGenreEntity> repository;
    
    private static final String QUERY = 
        "select "
                + "song_genre.id as id, "
                + "song_genre.id_genre as id_genre, "
                + "song_genre.id_song as id_song, "             
                + "song.name as name, "
                + "song.rating as rating, "
                + "song.id_album as id_album, "
                + "album.name as album_name, "               
                + "album.year as year, "               
                + "album.id_artist as id_artist, "
                + "artist.name as artist_name "               
            + "from song_genre "
                + "left join song on song_genre.id_song = song.id "
                + "left join album on song.id_album = album.id "
                + "left join artist on album.id_artist = artist.id "                
            + "where song_genre.id_genre = ?";
    
    @Autowired        
    public SelectSongGenreByGenre(DataSource dataSource) {
        super(dataSource, QUERY);
        super.declareParameter(new SqlParameter("id_genre", Types.INTEGER));
        compile();
    }
     
    @Override
    protected SongGenreEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        SongGenreEntity songGenre = repository.getEntity(resultSet);
            
        SongEntity song = new SongEntity();
        song.setId(songGenre.getId_song());
        song.setName(resultSet.getString("name"));
        song.setRating(resultSet.getInt("rating"));
        song.setId_album(resultSet.getInt("id_album"));

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
        songGenre.setSong(song);
        return songGenre;
    }

    public void setRepository(BaseRepository<SongGenreEntity> repository) {
        this.repository = repository;
    }
}
