
package com.algerd.musicbookspringmaven.service.impl;

import com.algerd.musicbookspringmaven.repository.Album.AlbumEntity;
import com.algerd.musicbookspringmaven.repository.Artist.ArtistEntity;
import com.algerd.musicbookspringmaven.repository.ArtistReference.ArtistReferenceEntity;
import com.algerd.musicbookspringmaven.repository.Genre.GenreEntity;
import com.algerd.musicbookspringmaven.repository.Instrument.InstrumentEntity;
import com.algerd.musicbookspringmaven.repository.Musician.MusicianEntity;
import com.algerd.musicbookspringmaven.repository.MusicianAlbum.MusicianAlbumEntity;
import com.algerd.musicbookspringmaven.repository.MusicianGroup.MusicianGroupEntity;
import com.algerd.musicbookspringmaven.repository.MusicianSong.MusicianSongEntity;
import com.algerd.musicbookspringmaven.repository.Song.SongEntity;
import com.algerd.musicbookspringmaven.service.RepositoryService;
import com.algerd.musicbookspringmaven.service.DeleteAlertService;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.springframework.beans.factory.annotation.Autowired;

public class DeleteAlertServiceImpl implements DeleteAlertService {
    
    @Autowired
    private RepositoryService repositoryService;
    
    public DeleteAlertServiceImpl() {     
    }
    
    public void setRepositoryService(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }
    
    @Override
    public void show(AlbumEntity album) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Do you want to remove the album " + album.getName() + " ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            repositoryService.getAlbumRepository().delete(album);
        }
    }
    
    @Override
    public void show(ArtistEntity artist) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Do you want to remove the artist " + artist.getName() + " ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
           repositoryService.getArtistRepository().delete(artist);
        }
    }
    
    @Override
    public void show(GenreEntity genre) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");       
        alert.setContentText("Do you want to remove the genre " + genre.getName() + " ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            repositoryService.getGenreRepository().delete(genre);
        }
    }
    
    @Override
    public void show(InstrumentEntity instrument) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");       
        alert.setContentText("Do you want to remove the instrument " + instrument.getName() + " ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            repositoryService.getInstrumentRepository().delete(instrument);
        }
    }
    
    @Override
    public void show(MusicianAlbumEntity musicianAlbum) {
        MusicianEntity musician = repositoryService.getMusicianRepository().selectById(musicianAlbum.getId_musician());
        AlbumEntity album = repositoryService.getAlbumRepository().selectById(musicianAlbum.getId_album());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");        
        alert.setContentText("Do you want to remove the musician " + musician.getName() + " from " + album.getName() + " ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            repositoryService.getMusicianAlbumRepository().delete(musicianAlbum);
        }
    }
    
    @Override
    public void show(MusicianEntity musician) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");       
        alert.setContentText("Do you want to remove the musician " + musician.getName() + " ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            repositoryService.getMusicianRepository().delete(musician);       
        }
    }
    
    @Override
    public void show(MusicianSongEntity musicianSong) {
        MusicianEntity musician = repositoryService.getMusicianRepository().selectById(musicianSong.getId_musician());
        SongEntity song = repositoryService.getSongRepository().selectById(musicianSong.getId_song());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");        
        alert.setContentText("Do you want to remove the musician " + musician.getName() + " from " + song.getName() + " ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            repositoryService.getMusicianSongRepository().delete(musicianSong);
        }
    }
    
    @Override
    public void show(SongEntity song) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Do you want to remove the song " + song.getName() + " ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            repositoryService.getSongRepository().delete(song);
        }
    }
    
    @Override
    public void show(MusicianGroupEntity musicianGroup) {
        MusicianEntity musician = repositoryService.getMusicianRepository().selectById(musicianGroup.getId_musician());
        ArtistEntity artist = repositoryService.getArtistRepository().selectById(musicianGroup.getId_artist());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");        
        alert.setContentText("Do you want to remove the musician " + musician.getName() + " from " + artist.getName() + " ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            repositoryService.getMusicianGroupRepository().delete(musicianGroup);
        }
    }
    
    @Override
    public void show(ArtistReferenceEntity artistReference) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Do you want to remove the artist reference: " + artistReference.getName() + " ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            repositoryService.getArtistReferenceRepository().delete(artistReference);
        }
    }
     
}
