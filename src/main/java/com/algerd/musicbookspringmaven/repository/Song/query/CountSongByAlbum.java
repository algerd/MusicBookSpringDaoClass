
package com.algerd.musicbookspringmaven.repository.Song.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

@Component("countSongByAlbum")
public class CountSongByAlbum extends MappingSqlQuery<Integer> {

    private static final String QUERY =
        "select count(id) from song where id_album = ?";
    
    @Autowired        
    public CountSongByAlbum(DataSource dataSource) {
        super(dataSource, QUERY);
        super.declareParameter(new SqlParameter("id_album", Types.INTEGER));
        compile();
    }

    @Override
    protected Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getInt(1);
    }   
}
