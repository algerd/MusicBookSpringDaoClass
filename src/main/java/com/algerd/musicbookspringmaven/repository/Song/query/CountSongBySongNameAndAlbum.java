
package com.algerd.musicbookspringmaven.repository.Song.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

@Component("countSongBySongNameAndAlbum")
public class CountSongBySongNameAndAlbum extends MappingSqlQuery<Integer> {
    
    private static final String QUERY =
         "select count(id) from song where id_album = ? and name = ?";   
    
    @Autowired        
    public CountSongBySongNameAndAlbum(DataSource dataSource) {
        super(dataSource, QUERY);
        super.declareParameter(new SqlParameter("id_album", Types.INTEGER));
        super.declareParameter(new SqlParameter("name", Types.VARCHAR));
        compile();
    }

    @Override
    protected Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getInt(1);
    }  
}
