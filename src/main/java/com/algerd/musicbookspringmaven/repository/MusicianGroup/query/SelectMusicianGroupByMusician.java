
package com.algerd.musicbookspringmaven.repository.MusicianGroup.query;

import com.algerd.musicbookspringmaven.repository.BaseRepository;
import com.algerd.musicbookspringmaven.repository.Artist.ArtistEntity;
import com.algerd.musicbookspringmaven.repository.MusicianGroup.MusicianGroupEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

@Component("selectMusicianGroupByMusician")
public class SelectMusicianGroupByMusician extends MappingSqlQuery<MusicianGroupEntity> {

    private BaseRepository<MusicianGroupEntity> repository;
    
    private static final String QUERY = 
        "select "
            + "musician_group.id, "
            + "musician_group.id_musician, "
            + "musician_group.id_artist, "
            + "musician_group.start_date, "
            + "musician_group.end_date, "
            + "artist.name, "
            + "artist.rating "
        + "from musician_group "
            + "left join artist on musician_group.id_artist = artist.id "
        + "where musician_group.id_musician = ?"; 
    
    @Autowired        
    public SelectMusicianGroupByMusician(DataSource dataSource) {
        super(dataSource, QUERY);
        super.declareParameter(new SqlParameter("id_musician", Types.INTEGER));
        compile();
    }
     
    @Override
    protected MusicianGroupEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        MusicianGroupEntity musicianGroup = repository.getEntity(resultSet);
        ArtistEntity artist = new ArtistEntity();
        artist.setId(musicianGroup.getId_artist());
        artist.setName(resultSet.getString(6));
        artist.setRating(resultSet.getInt(7));
        musicianGroup.setArtist(artist);
        return musicianGroup;
    }

    public void setRepository(BaseRepository<MusicianGroupEntity> repository) {
        this.repository = repository;
    }  
}
