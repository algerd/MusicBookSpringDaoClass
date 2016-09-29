
package com.algerd.musicbookspringmaven.repository.MusicianGroup;

import com.algerd.musicbookspringmaven.repository.Musician.MusicianEntity;
import com.algerd.musicbookspringmaven.repository.Artist.ArtistEntity;
import com.algerd.musicbookspringmaven.repository.Entity;
import com.algerd.musicbookspringmaven.repository.annotation.Column;
import com.algerd.musicbookspringmaven.repository.annotation.Table;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Table("musician_group")
public class MusicianGroupEntity extends Entity {
    @Column("id")
    private int id = 0;
    @Column("id_musician")
    private int id_musician = 1;     
    @Column("id_artist")
    private int id_artist = 1;
    @Column("start_date")
    private final StringProperty start_date = new SimpleStringProperty();
    @Column("end_date")
    private final StringProperty end_date = new SimpleStringProperty();
    
    private MusicianEntity musician;      
    private ArtistEntity artist;
                 
    public MusicianGroupEntity() {
        super();
    }
    
    @Override
	public boolean equals(Object obj) {        
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof MusicianGroupEntity) {
            Entity entity = (MusicianGroupEntity) obj;
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
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId_musician() {
        return id_musician;
    }
    public void setId_musician(int id_musician) {
        this.id_musician = id_musician;
    }

    public int getId_artist() {
        return id_artist;
    }
    public void setId_artist(int id_artist) {
        this.id_artist = id_artist;
    }   
    
    public String getStart_date() {
        return start_date.get();
    }
    public void setStart_date(String value) {
        start_date.set(value);
    }
    public StringProperty start_dateProperty() {
        return start_date;
    }
    
    public String getEnd_date() {
        return end_date.get();
    }
    public void setEnd_date(String value) {
        end_date.set(value);
    }
    public StringProperty end_dateProperty() {
        return end_date;
    }

    public MusicianEntity getMusician() {
        return musician;
    }
    public void setMusician(MusicianEntity musician) {
        this.musician = musician;
    }

    public ArtistEntity getArtist() {
        return artist;
    }
    public void setArtist(ArtistEntity artist) {
        this.artist = artist;
    }
       
}
