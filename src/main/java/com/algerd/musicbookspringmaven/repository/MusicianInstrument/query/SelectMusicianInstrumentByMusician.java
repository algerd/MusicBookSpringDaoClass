
package com.algerd.musicbookspringmaven.repository.MusicianInstrument.query;

import com.algerd.musicbookspringmaven.repository.BaseRepository;
import com.algerd.musicbookspringmaven.repository.Instrument.InstrumentEntity;
import com.algerd.musicbookspringmaven.repository.MusicianInstrument.MusicianInstrumentEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

@Component("SelectMusicianInstrumentByMusician")
public class SelectMusicianInstrumentByMusician extends MappingSqlQuery<MusicianInstrumentEntity> {
    
    private BaseRepository<MusicianInstrumentEntity> repository;
    
    private static final String QUERY = 
        "select "
            + "musician_instrument.id, "
            + "musician_instrument.id_instrument, "
            + "musician_instrument.id_musician, "              
            + "instrument.name "
        + "from musician_instrument "
            + "left join instrument on musician_instrument.id_instrument = instrument.id "
        + "where musician_instrument.id_musician = ?";  
    
    @Autowired        
    public SelectMusicianInstrumentByMusician(DataSource dataSource) {
        super(dataSource, QUERY);
        super.declareParameter(new SqlParameter("id_musician", Types.INTEGER));
        compile();
    }
     
    @Override
    protected MusicianInstrumentEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        MusicianInstrumentEntity musicianInstrument = repository.getEntity(resultSet);
        InstrumentEntity instrument = new InstrumentEntity();
        instrument.setId(musicianInstrument.getId_instrument());
        instrument.setName(resultSet.getString(4));
        musicianInstrument.setInstrument(instrument);
        return musicianInstrument;
    }

    public void setRepository(BaseRepository<MusicianInstrumentEntity> repository) {
        this.repository = repository;
    }
    
}
