
package com.algerd.musicbookspringmaven.repository.ArtistReference;

import com.algerd.musicbookspringmaven.repository.Entity;
import com.algerd.musicbookspringmaven.repository.annotation.Column;
import com.algerd.musicbookspringmaven.repository.annotation.Table;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Table("artist_reference")
public class ArtistReferenceEntity extends Entity {
    @Column("id")
    private int id = 0;
    @Column("id_artist")
    private int id_artist = 1;
    @Column("name")
    private final StringProperty name = new SimpleStringProperty("");
    @Column("reference")
    private final StringProperty reference = new SimpleStringProperty("");
   
    public ArtistReferenceEntity() {
        super();
    }
    
    @Override
	public boolean equals(Object obj) {        
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof ArtistReferenceEntity) {
            Entity entity = (ArtistReferenceEntity) obj;
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

    public int getId_artist() {
        return id_artist;
    }
    public void setId_artist(int id_artist) {
        this.id_artist = id_artist;
    }
            
    @Override
    public String getName() {
        return name.get();
    }
    public void setName(String value) {
        name.set(value);
    }
    public StringProperty nameProperty() {
        return name;
    }
    
    public String getReference() {
        return reference.get();
    }
    public void setReference(String value) {
        reference.set(value);
    }
    public StringProperty referenceProperty() {
        return reference;
    }
    
    
}
