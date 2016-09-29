
package com.algerd.musicbookspringmaven.repository.MusicianInstrument;

import com.algerd.musicbookspringmaven.repository.Musician.MusicianEntity;
import com.algerd.musicbookspringmaven.repository.Instrument.InstrumentEntity;
import com.algerd.musicbookspringmaven.repository.Entity;
import com.algerd.musicbookspringmaven.repository.annotation.Column;
import com.algerd.musicbookspringmaven.repository.annotation.Table;

@Table("musician_instrument")
public class MusicianInstrumentEntity extends Entity {
    @Column("id")
    private int id = 0;
    @Column("id_instrument")
    private int id_instrument = 0; 
    @Column("id_musician")
    private int id_musician = 0;
    
    private InstrumentEntity instrument;
    private MusicianEntity musician;
    
    public MusicianInstrumentEntity() {
        super();
    }
    
    @Override
	public boolean equals(Object obj) {        
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof MusicianInstrumentEntity) {
            Entity entity = (MusicianInstrumentEntity) obj;
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

    public int getId_instrument() {
        return id_instrument;
    }
    public void setId_instrument(int id_instrument) {
        this.id_instrument = id_instrument;
    }

    public int getId_musician() {
        return id_musician;
    }
    public void setId_musician(int id_musician) {
        this.id_musician = id_musician;
    }

    public InstrumentEntity getInstrument() {
        return instrument;
    }
    public void setInstrument(InstrumentEntity instrument) {
        this.instrument = instrument;
    }

    public MusicianEntity getMusician() {
        return musician;
    }
    public void setMusician(MusicianEntity musician) {
        this.musician = musician;
    }
    
}
