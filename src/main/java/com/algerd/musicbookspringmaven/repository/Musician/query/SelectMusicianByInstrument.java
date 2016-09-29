
package com.algerd.musicbookspringmaven.repository.Musician.query;

import com.algerd.musicbookspringmaven.repository.BaseRepository;
import com.algerd.musicbookspringmaven.repository.Musician.MusicianEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

@Component("selectMusicianByInstrument")
public class SelectMusicianByInstrument extends MappingSqlQuery<MusicianEntity> {

    private BaseRepository<MusicianEntity> repository;

    private static final String QUERY = 
        "select "
                + "musician.id, "             
                + "musician.name, "
                + "musician.rating "
            + "from musician_instrument "
                + "left join musician on musician_instrument.id_musician = musician.id "
            + "where musician_instrument.id_instrument = ?";
    
    @Autowired 
    public SelectMusicianByInstrument(DataSource dataSource) {
        super(dataSource, QUERY);
        super.declareParameter(new SqlParameter("id_instrument", Types.INTEGER));
        compile();
    }

    @Override
    protected MusicianEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        MusicianEntity musician = new MusicianEntity();
        musician.setId(resultSet.getInt("id"));
        musician.setName(resultSet.getString("name"));
        musician.setRating(resultSet.getInt("rating"));
        return musician;
    }
    
    public void setRepository(BaseRepository<MusicianEntity> repository) {
        this.repository = repository;
    }  
}
