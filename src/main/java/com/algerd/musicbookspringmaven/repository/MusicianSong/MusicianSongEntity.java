
package com.algerd.musicbookspringmaven.repository.MusicianSong;

import com.algerd.musicbookspringmaven.repository.Musician.MusicianEntity;
import com.algerd.musicbookspringmaven.repository.Entity;
import com.algerd.musicbookspringmaven.repository.annotation.Column;
import com.algerd.musicbookspringmaven.repository.annotation.Table;
import com.algerd.musicbookspringmaven.repository.Song.SongEntity;

@Table("musician_song")
public class MusicianSongEntity extends Entity {
    @Column("id")
    private int id = 0;
    @Column("id_musician")
    private int id_musician = 1; 
    @Column("id_song")
    private int id_song = 1;
    
    private MusicianEntity musician;      
    private SongEntity song;
    
    public MusicianSongEntity() {
        super();
    }
    
    @Override
	public boolean equals(Object obj) {        
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof MusicianSongEntity) {
            Entity entity = (MusicianSongEntity) obj;
            if (entity.getId() == getId()) {
                return true;
            }
        }    
		return false;
	}

    @Override
    public int getId() {
        return id;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String getName() {
        return "";
    }

    public int getId_musician() {
        return id_musician;
    }
    public void setId_musician(int id_musician) {
        this.id_musician = id_musician;
    }

    public int getId_song() {
        return id_song;
    }
    public void setId_song(int id_song) {
        this.id_song = id_song;
    }

    public MusicianEntity getMusician() {
        return musician;
    }
    public void setMusician(MusicianEntity musician) {
        this.musician = musician;
    }

    public SongEntity getSong() {
        return song;
    }
    public void setSong(SongEntity song) {
        this.song = song;
    }
       
}

