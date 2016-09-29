
package com.algerd.musicbookspringmaven.repository.MusicianGroup.query;

import com.algerd.musicbookspringmaven.repository.BaseRepository;
import com.algerd.musicbookspringmaven.repository.Artist.ArtistEntity;
import com.algerd.musicbookspringmaven.repository.Musician.MusicianEntity;
import com.algerd.musicbookspringmaven.repository.MusicianGroup.MusicianGroupEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

@Component("selectMusicianGroupByArtist")
public class SelectMusicianGroupByArtist extends MappingSqlQuery<MusicianGroupEntity> {
    
    private BaseRepository<MusicianGroupEntity> repository;
    
    private static final String QUERY = 
        "select "
            + "musician_group.id, "
            + "musician_group.id_musician, "
            + "musician_group.id_artist, "
            + "musician_group.start_date, "
            + "musician_group.end_date, "
            + "musician.name, "
            + "musician.rating "
        + "from musician_group "
            + "left join musician on musician_group.id_musician = musician.id "
        + "where musician_group.id_artist = ?";
    
    @Autowired        
    public SelectMusicianGroupByArtist(DataSource dataSource) {
        super(dataSource, QUERY);
        super.declareParameter(new SqlParameter("id_artist", Types.INTEGER));
        compile();
    }
     
    @Override
    protected MusicianGroupEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        MusicianGroupEntity musicianGroup = repository.getEntity(resultSet);
        MusicianEntity musician = new MusicianEntity();
        musician.setId(musicianGroup.getId_musician());
        musician.setName(resultSet.getString(6));
        musician.setRating(resultSet.getInt(7));
        musicianGroup.setMusician(musician);
        return musicianGroup;
    }

    public void setRepository(BaseRepository<MusicianGroupEntity> repository) {
        this.repository = repository;
    }  
}
