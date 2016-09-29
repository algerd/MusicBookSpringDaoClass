
package com.algerd.musicbookspringmaven.repository.MusicianAlbum.query;

import com.algerd.musicbookspringmaven.repository.BaseRepository;
import com.algerd.musicbookspringmaven.repository.Album.AlbumEntity;
import com.algerd.musicbookspringmaven.repository.Artist.ArtistEntity;
import com.algerd.musicbookspringmaven.repository.MusicianAlbum.MusicianAlbumEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

@Component("selectMusicianAlbumByMusician")
public class SelectMusicianAlbumByMusician extends MappingSqlQuery<MusicianAlbumEntity> {
    
    private BaseRepository<MusicianAlbumEntity> repository;
    
    private static final String QUERY = 
        "select "
                + "musician_album.id as id, "
                + "musician_album.id_musician as id_musician, "
                + "musician_album.id_album as id_album, "
                + "album.name as album_name, "
                + "album.rating as rating, "
                + "artist.id as id_artist, "               
                + "artist.name as artist_name "
            + "from musician_album "
                + "left join album on musician_album.id_album = album.id "
                + "left join artist on album.id_artist = artist.id "
            + "where musician_album.id_musician = ?"; 

    @Autowired 
    public SelectMusicianAlbumByMusician(DataSource dataSource) {
        super(dataSource, QUERY);
        super.declareParameter(new SqlParameter("id_musician", Types.INTEGER));
        compile();
    }
    
    @Override
    protected MusicianAlbumEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        MusicianAlbumEntity musicianAlbum = repository.getEntity(resultSet);
            
        AlbumEntity album = new AlbumEntity();
        album.setId(musicianAlbum.getId_album());
        album.setName(resultSet.getString("album_name"));
        album.setRating(resultSet.getInt("rating"));

        ArtistEntity artist = new ArtistEntity();
        artist.setId(resultSet.getInt("id_artist"));
        artist.setName(resultSet.getString("artist_name"));

        album.setArtist(artist);
        musicianAlbum.setAlbum(album);
        return musicianAlbum;            
    }

    public void setRepository(BaseRepository<MusicianAlbumEntity> repository) {
        this.repository = repository;
    }
    
}
