
package com.algerd.musicbookspringmaven.repository.MusicianInstrument.query;

import com.algerd.musicbookspringmaven.repository.BaseRepository;
import com.algerd.musicbookspringmaven.repository.Musician.MusicianEntity;
import com.algerd.musicbookspringmaven.repository.MusicianInstrument.MusicianInstrumentEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

@Component("selectMusicianInstrumentByInstrument")
public class SelectMusicianInstrumentByInstrument extends MappingSqlQuery<MusicianInstrumentEntity> {

    private BaseRepository<MusicianInstrumentEntity> repository;
    
    private static final String QUERY = 
        "select "
               + "musician_instrument.id, "
               + "musician_instrument.id_instrument, "
               + "musician_instrument.id_musician, "             
               + "musician.name, "
               + "musician.rating "
            + "from musician_instrument "
                + "left join musician on musician_instrument.id_musician = musician.id "
            + "where musician_instrument.id_instrument = ?";   
            
    @Autowired        
    public SelectMusicianInstrumentByInstrument(DataSource dataSource) {
        super(dataSource, QUERY);
        super.declareParameter(new SqlParameter("id_instrument", Types.INTEGER));
        compile();
    }
     
    @Override
    protected MusicianInstrumentEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        MusicianInstrumentEntity musicianInstrument = repository.getEntity(resultSet);
        MusicianEntity musician = new MusicianEntity();
        musician.setId(musicianInstrument.getId_musician());
        musician.setName(resultSet.getString(4));
        musician.setRating(resultSet.getInt(5));
        musicianInstrument.setMusician(musician);
        return musicianInstrument;
    }

    public void setRepository(BaseRepository<MusicianInstrumentEntity> repository) {
        this.repository = repository;
    }                   
}
