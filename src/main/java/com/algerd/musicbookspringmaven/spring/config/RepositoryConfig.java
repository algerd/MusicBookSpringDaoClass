
package com.algerd.musicbookspringmaven.spring.config;

import com.algerd.musicbookspringmaven.repository.AlbumGenre.AlbumGenreRepository;
import com.algerd.musicbookspringmaven.repository.Album.AlbumRepository;
import com.algerd.musicbookspringmaven.repository.ArtistGenre.ArtistGenreRepository;
import com.algerd.musicbookspringmaven.repository.ArtistReference.ArtistReferenceRepository;
import com.algerd.musicbookspringmaven.repository.Artist.ArtistRepository;
import com.algerd.musicbookspringmaven.repository.Genre.GenreRepository;
import com.algerd.musicbookspringmaven.repository.Instrument.InstrumentRepository;
import com.algerd.musicbookspringmaven.repository.MusicianAlbum.MusicianAlbumRepository;
import com.algerd.musicbookspringmaven.repository.MusicianGenre.MusicianGenreRepository;
import com.algerd.musicbookspringmaven.repository.MusicianGroup.MusicianGroupRepository;
import com.algerd.musicbookspringmaven.repository.MusicianInstrument.MusicianInstrumentRepository;
import com.algerd.musicbookspringmaven.repository.Musician.MusicianRepository;
import com.algerd.musicbookspringmaven.repository.MusicianSong.MusicianSongRepository;
import com.algerd.musicbookspringmaven.repository.SongGenre.SongGenreRepository;
import com.algerd.musicbookspringmaven.repository.Song.SongRepository;
import com.algerd.musicbookspringmaven.repository.Album.AlbumRepositoryImpl;
import com.algerd.musicbookspringmaven.repository.AlbumGenre.AlbumGenreRepositoryImpl;
import com.algerd.musicbookspringmaven.repository.ArtistGenre.ArtistGenreRepositoryImpl;
import com.algerd.musicbookspringmaven.repository.ArtistReference.ArtistReferenceRepositoryImpl;
import com.algerd.musicbookspringmaven.repository.Artist.ArtistRepositoryImpl;
import com.algerd.musicbookspringmaven.repository.Genre.GenreRepositoryImpl;
import com.algerd.musicbookspringmaven.repository.Instrument.InstrumentRepositoryImpl;
import com.algerd.musicbookspringmaven.repository.MusicianAlbum.MusicianAlbumRepositoryImpl;
import com.algerd.musicbookspringmaven.repository.MusicianGenre.MusicianGenreRepositoryImpl;
import com.algerd.musicbookspringmaven.repository.MusicianGroup.MusicianGroupRepositoryImpl;
import com.algerd.musicbookspringmaven.repository.MusicianInstrument.MusicianInstrumentRepositoryImpl;
import com.algerd.musicbookspringmaven.repository.Musician.MusicianRepositoryImpl;
import com.algerd.musicbookspringmaven.repository.MusicianSong.MusicianSongRepositoryImpl;
import com.algerd.musicbookspringmaven.repository.SongGenre.SongGenreRepositoryImpl;
import com.algerd.musicbookspringmaven.repository.Song.SongRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource({"classpath:properties/dbconnect.properties"})
public class RepositoryConfig {
    
    @Autowired
    Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("driver-class-name"));
        dataSource.setUrl(env.getProperty("url"));
        dataSource.setUsername(env.getProperty("user"));
        dataSource.setPassword(env.getProperty("password"));
        try {
            String propertiesUrl = "properties/dbconfig.properties";
            InputStream input = this.getClass().getClassLoader().getResourceAsStream(propertiesUrl);
            if (input == null) {
                throw new IOException("Sorry, unable to find " + propertiesUrl);
            }        
            Properties properties = new Properties();
            properties.load(input);
            dataSource.setConnectionProperties(properties);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return dataSource;
    }
    
    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }
   
    @Bean
    public AlbumRepository albumRepository() {
        return new AlbumRepositoryImpl();
    }
    
    @Bean
    public AlbumGenreRepository albumGenreRepository() {
        return new AlbumGenreRepositoryImpl();
    }
    
    @Bean
    public ArtistGenreRepository artistGenreRepository() {
        return new ArtistGenreRepositoryImpl();
    }
    
    @Bean
    public ArtistReferenceRepository artistReferenceRepository() {
        return new ArtistReferenceRepositoryImpl();
    }
    
    @Bean
    public ArtistRepository artistRepository() {
        return new ArtistRepositoryImpl();
    }
        
    @Bean
    public GenreRepository genreRepository() {
        return new GenreRepositoryImpl();
    }
    
    @Bean
    public InstrumentRepository instrumentRepository() {
        return new InstrumentRepositoryImpl();
    }
    
    @Bean
    public MusicianAlbumRepository musicianAlbumRepository() {
        return new MusicianAlbumRepositoryImpl();
    } 
    
    @Bean
    public MusicianGroupRepository musicianGroupRepository() {
        return new MusicianGroupRepositoryImpl();
    }
    
    @Bean
    public MusicianGenreRepository musicianGenreRepository() {
        return new MusicianGenreRepositoryImpl();
    }
    
    @Bean
    public MusicianInstrumentRepository musicianInstrumentRepository() {
        return new MusicianInstrumentRepositoryImpl();
    }
    
    @Bean
    public MusicianSongRepository musicianSongRepository() {
        return new MusicianSongRepositoryImpl();
    }
    
    @Bean
    public MusicianRepository musicianRepository() {
        return new MusicianRepositoryImpl();
    }
 
    @Bean
    public SongGenreRepository songGenreRepository() {
        return new SongGenreRepositoryImpl();
    }

    @Bean
    public SongRepository songRepository() {
        return new SongRepositoryImpl();
    }

}
