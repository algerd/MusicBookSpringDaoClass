
package com.algerd.musicbookspringmaven.repository.MusicianSong.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

@Component("countMusicianSongByMusicianAndSong")
public class CountMusicianSongByMusicianAndSong extends MappingSqlQuery<Integer> {

    private static final String QUERY =
        "select count(id) from musician_song where id_musician = ? and id_song = ?";
    
    @Autowired        
    public CountMusicianSongByMusicianAndSong(DataSource dataSource) {
        super(dataSource, QUERY);
        super.declareParameter(new SqlParameter("id_musician", Types.INTEGER));
        super.declareParameter(new SqlParameter("id_song", Types.INTEGER));
        compile();
    }

    @Override
    protected Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getInt(1);
    }
}
