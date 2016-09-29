
package com.algerd.musicbookspringmaven.repository.MusicianSong.query;

import com.algerd.musicbookspringmaven.repository.BaseRepository;
import com.algerd.musicbookspringmaven.repository.Song.SongEntity;
import com.algerd.musicbookspringmaven.repository.Album.AlbumEntity;
import com.algerd.musicbookspringmaven.repository.Artist.ArtistEntity;
import com.algerd.musicbookspringmaven.repository.MusicianSong.MusicianSongEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

@Component("selectMusicianSongByMusician")
public class SelectMusicianSongByMusician extends MappingSqlQuery<MusicianSongEntity> {
    
    private BaseRepository<MusicianSongEntity> repository;
    
    private static final String QUERY = 
        "select "
                + "musician_song.id, "
                + "musician_song.id_musician, "
                + "musician_song.id_song, "
                + "song.name, "
                + "song.rating, "
                + "album.id, "
                + "album.name, "
                + "album.year, "
                + "artist.id, "
                + "artist.name "                
            + "from musician_song "
                + "left join song on musician_song.id_song = song.id "
                + "left join album on song.id_album = album.id "
                + "left join artist on album.id_artist = artist.id "
            + "where musician_song.id_musician = ?";
    
    @Autowired        
    public SelectMusicianSongByMusician(DataSource dataSource) {
        super(dataSource, QUERY);
        super.declareParameter(new SqlParameter("id_instrument", Types.INTEGER));
        compile();
    }
     
    @Override
    protected MusicianSongEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        MusicianSongEntity musicianSong = repository.getEntity(resultSet);
        SongEntity song = new SongEntity();
        song.setId(musicianSong.getId_song());
        song.setName(resultSet.getString(4));
        song.setRating(resultSet.getInt(5));

        AlbumEntity album = new AlbumEntity();
        album.setId(resultSet.getInt(6));
        album.setName(resultSet.getString(7));
        album.setYear(resultSet.getInt(8));
        song.setAlbum(album);

        ArtistEntity artist = new ArtistEntity();
        artist.setId(resultSet.getInt(9));
        artist.setName(resultSet.getString(10));
        album.setArtist(artist);

        musicianSong.setSong(song);
        return musicianSong;
    }

    public void setRepository(BaseRepository<MusicianSongEntity> repository) {
        this.repository = repository;
    } 

}
