
package com.algerd.musicbookspringmaven.repository.MusicianSong.query;

import com.algerd.musicbookspringmaven.repository.BaseRepository;
import com.algerd.musicbookspringmaven.repository.Musician.MusicianEntity;
import com.algerd.musicbookspringmaven.repository.MusicianSong.MusicianSongEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

@Component("selectMusicianSongBySong")
public class SelectMusicianSongBySong extends MappingSqlQuery<MusicianSongEntity> {

    private BaseRepository<MusicianSongEntity> repository;
    
    private static final String QUERY = 
        "select "
                + "musician_song.id, "
                + "musician_song.id_musician, "
                + "musician_song.id_song, "
                + "musician.name, "
                + "musician.rating "
            + "from musician_song "
                + "left join musician on musician_song.id_musician = musician.id "
            + "where musician_song.id_song = ?";  
            
    @Autowired        
    public SelectMusicianSongBySong(DataSource dataSource) {
        super(dataSource, QUERY);
        super.declareParameter(new SqlParameter("id_song", Types.INTEGER));
        compile();
    }
     
    @Override
    protected MusicianSongEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        MusicianSongEntity musicianSong = repository.getEntity(resultSet);
        MusicianEntity musician = new MusicianEntity();
        musician.setId(musicianSong.getId_musician());
        musician.setName(resultSet.getString(4));
        musician.setRating(resultSet.getInt(5));
        musicianSong.setMusician(musician);
        return musicianSong;
    }

    public void setRepository(BaseRepository<MusicianSongEntity> repository) {
        this.repository = repository;
    }                     
}
