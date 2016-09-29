
package com.algerd.musicbookspringmaven.repository.ArtistGenre;

import com.algerd.musicbookspringmaven.repository.Artist.ArtistEntity;
import com.algerd.musicbookspringmaven.repository.Entity;
import com.algerd.musicbookspringmaven.repository.annotation.Column;
import com.algerd.musicbookspringmaven.repository.annotation.Table;
import com.algerd.musicbookspringmaven.repository.Genre.GenreEntity;

@Table("artist_genre")
public class ArtistGenreEntity extends Entity {
    @Column("id")
    private int id = 0;
    @Column("id_genre")
    private int id_genre = 0; 
    @Column("id_artist")
    private int id_artist = 0;
    
    private GenreEntity genre;
    private ArtistEntity artist;
    
    public ArtistGenreEntity() {
        super();
    }
    
    @Override
	public boolean equals(Object obj) {        
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof ArtistGenreEntity) {
            Entity entity = (ArtistGenreEntity) obj;
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

    public int getId_artist() {
        return id_artist;
    }
    public void setId_artist(int id_artist) {
        this.id_artist = id_artist;
    } 

    public GenreEntity getGenre() {
        return genre;
    }
    public void setGenre(GenreEntity genre) {
        this.genre = genre;
    }

    public ArtistEntity getArtist() {
        return artist;
    }
    public void setArtist(ArtistEntity artist) {
        this.artist = artist;
    }
       
}
