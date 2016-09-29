
package com.algerd.musicbookspringmaven.repository.MusicianGroup.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

@Component("countMusicianGroupByMusicianAndArtist")
public class CountMusicianGroupByMusicianAndArtist extends MappingSqlQuery<Integer>  {
    
    private static final String QUERY =
        "select count(id) from musician_group where id_musician = ? and id_artist = ?";
    
    @Autowired        
    public CountMusicianGroupByMusicianAndArtist(DataSource dataSource) {
        super(dataSource, QUERY);
        super.declareParameter(new SqlParameter("id_musician", Types.INTEGER));
        super.declareParameter(new SqlParameter("id_artist", Types.INTEGER));
        compile();
    }

    @Override
    protected Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getInt(1);
    } 
}
