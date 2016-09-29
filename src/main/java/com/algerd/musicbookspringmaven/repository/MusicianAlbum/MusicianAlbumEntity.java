
package com.algerd.musicbookspringmaven.repository.MusicianAlbum;

import com.algerd.musicbookspringmaven.repository.Musician.MusicianEntity;
import com.algerd.musicbookspringmaven.repository.Album.AlbumEntity;
import com.algerd.musicbookspringmaven.repository.Entity;
import com.algerd.musicbookspringmaven.repository.annotation.Column;
import com.algerd.musicbookspringmaven.repository.annotation.Table;

@Table("musician_album")
public class MusicianAlbumEntity extends Entity {
    @Column("id")
    private int id = 0;
    @Column("id_musician")
    private int id_musician = 1;
    @Column("id_album")
    private int id_album = 1;
    
    private MusicianEntity musician;      
    private AlbumEntity album;
    
    public MusicianAlbumEntity() {
        super();
    }
    
    @Override
	public boolean equals(Object obj) {        
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof MusicianAlbumEntity) {
            Entity entity = (MusicianAlbumEntity) obj;
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

    public int getId_album() {
        return id_album;
    }
    public void setId_album(int id_album) {
        this.id_album = id_album;
    }

    public MusicianEntity getMusician() {
        return musician;
    }
    public void setMusician(MusicianEntity musician) {
        this.musician = musician;
    }

    public AlbumEntity getAlbum() {
        return album;
    }
    public void setAlbum(AlbumEntity album) {
        this.album = album;
    }   
    
}
