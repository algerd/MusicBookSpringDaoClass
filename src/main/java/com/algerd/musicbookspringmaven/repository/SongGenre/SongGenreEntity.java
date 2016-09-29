
package com.algerd.musicbookspringmaven.repository.SongGenre;

import com.algerd.musicbookspringmaven.repository.Song.SongEntity;
import com.algerd.musicbookspringmaven.repository.Genre.GenreEntity;
import com.algerd.musicbookspringmaven.repository.Entity;
import com.algerd.musicbookspringmaven.repository.annotation.Column;
import com.algerd.musicbookspringmaven.repository.annotation.Table;

@Table("song_genre")
public class SongGenreEntity extends Entity {
    @Column("id")
    private int id = 0;
    @Column("id_genre")
    private int id_genre = 0;
    @Column("id_song")
    private int id_song = 0; 
    
    private GenreEntity genre;
    private SongEntity song;
          
    public SongGenreEntity() {
        super();
    }
    
    @Override
	public boolean equals(Object obj) {        
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof SongGenreEntity) {
            Entity entity = (SongGenreEntity) obj;
            if (entity.getId() == getId()) {
                return true;
            }
        }    
		return false;
	}
    
    @Override
    public String getName() {
        return "";
    }

    @Override
    public int getId() {
        return id;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getId_genre() {
        return id_genre;
    }
    public void setId_genre(int id_genre) {
        this.id_genre = id_genre;
    }

    public int getId_song() {
        return id_song;
    }
    public void setId_song(int id_song) {
        this.id_song = id_song;
    } 

    public GenreEntity getGenre() {
        return genre;
    }
    public void setGenre(GenreEntity genre) {
        this.genre = genre;
    }

    public SongEntity getSong() {
        return song;
    }
    public void setSong(SongEntity song) {
        this.song = song;
    }
      
}
