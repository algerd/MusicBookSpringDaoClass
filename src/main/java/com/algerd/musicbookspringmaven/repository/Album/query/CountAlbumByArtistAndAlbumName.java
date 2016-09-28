
package com.algerd.musicbookspringmaven.repository.Album.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

@Component("countAlbumByArtistAndAlbumName")
public class CountAlbumByArtistAndAlbumName extends MappingSqlQuery<Integer> {

    private static final String QUERY = 
        "select count(id) from album where id_artist = ? and name = ?";;
    
    @Autowired 
    public CountAlbumByArtistAndAlbumName(DataSource dataSource) {
        super(dataSource, QUERY);
        super.declareParameter(new SqlParameter("id_artist", Types.INTEGER));
        super.declareParameter(new SqlParameter("name", Types.VARCHAR));
        compile();
    }
    
    @Override
    protected Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getInt(1);
    }
    
}
