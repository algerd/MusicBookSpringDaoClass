
package com.algerd.musicbookspringmaven.controller.musician;

import com.algerd.musicbookspringmaven.controller.BaseDialogController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import com.algerd.musicbookspringmaven.repository.Album.AlbumEntity;
import com.algerd.musicbookspringmaven.repository.Artist.ArtistEntity;
import com.algerd.musicbookspringmaven.repository.Entity;
import com.algerd.musicbookspringmaven.repository.Musician.MusicianEntity;
import com.algerd.musicbookspringmaven.repository.MusicianSong.MusicianSongEntity;
import com.algerd.musicbookspringmaven.repository.Song.SongEntity;
import com.algerd.musicbookspringmaven.utils.Helper;
import javafx.scene.layout.AnchorPane;

public class MusicianSongDialogController extends BaseDialogController {
    
    private MusicianSongEntity musicianSong;    
    
    @FXML
    private AnchorPane view;
    @FXML
    private ChoiceBox<MusicianEntity> musicianChoiceBox;
    @FXML
    private ChoiceBox<ArtistEntity> artistChoiceBox;
    @FXML
    private ChoiceBox<AlbumEntity> albumChoiceBox;
    @FXML
    private ChoiceBox<SongEntity> songChoiceBox;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        artistChoiceBox.getSelectionModel().selectedItemProperty().addListener(this::changedArtistChoiceBox);
        albumChoiceBox.getSelectionModel().selectedItemProperty().addListener(this::changedAlbumChoiceBox);
        initMusicianChoiceBox();        
        initArtistChoiceBox();       
    } 
    
    private void initMusicianChoiceBox() {
        Helper.initEntityChoiceBox(musicianChoiceBox);
        musicianChoiceBox.getItems().addAll(repositoryService.getMusicianRepository().selectAll());
        musicianChoiceBox.getSelectionModel().selectFirst();
    }
    
    private void initArtistChoiceBox() {
        Helper.initEntityChoiceBox(artistChoiceBox);
        artistChoiceBox.getItems().addAll(repositoryService.getArtistRepository().selectAll());
        artistChoiceBox.getSelectionModel().selectFirst();
    }
    
    /**
     * При выборе другого артиста - обновить список альбомов в albumChoiceBox.
     */
    private void changedArtistChoiceBox(ObservableValue<? extends Object> observable, ArtistEntity oldValue, ArtistEntity newValue) {          
        albumChoiceBox.getItems().clear();
        ArtistEntity artist = artistChoiceBox.getSelectionModel().getSelectedItem();
        if (artist != null) {
            albumChoiceBox.getItems().addAll(repositoryService.getAlbumRepository().selectAlbumByArtist(artist));
            albumChoiceBox.getSelectionModel().selectFirst();
        }    
    }
    
    /**
     * При выборе другого альбома - обновить список песен songChoiceBox.
     */
    private void changedAlbumChoiceBox(ObservableValue<? extends Object> observable, AlbumEntity oldValue, AlbumEntity newValue) {          
        songChoiceBox.getItems().clear();
        AlbumEntity album = albumChoiceBox.getSelectionModel().getSelectedItem();
        if (album != null) {
            songChoiceBox.getItems().addAll(repositoryService.getSongRepository().selectSongByAlbum(album));
            songChoiceBox.getSelectionModel().selectFirst();
        }
    }    
    
    
    @FXML
    private void handleOkButton() {
        if (isInputValid()) {
            MusicianEntity musician = musicianChoiceBox.getValue();
            SongEntity song = songChoiceBox.getValue();
            if (!repositoryService.getMusicianSongRepository().containsMusicianSongByMusicianAndSong(musician, song)) {
                musicianSong.setId_musician(musician.getId());
                musicianSong.setId_song(song.getId());
                repositoryService.getMusicianSongRepository().save(musicianSong);               
            }
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancelButton() {
        dialogStage.close();
    }
    
    @Override
    protected void edit() {
        add();
    }
    
    @Override
    protected void add() { 
        musicianChoiceBox.getSelectionModel().select(repositoryService.getMusicianRepository().selectById(musicianSong.getId_musician()));
    
        SongEntity song = repositoryService.getSongRepository().selectById(musicianSong.getId_song());
        AlbumEntity album = repositoryService.getAlbumRepository().selectById(song.getId_album()); 
        ArtistEntity artist = repositoryService.getArtistRepository().selectById(album.getId_artist()); 
        artistChoiceBox.getSelectionModel().select(artist);
        albumChoiceBox.getSelectionModel().select(album);
        songChoiceBox.getSelectionModel().select(song);
    }
    
    @Override
    protected boolean isInputValid() {
        String errorMessage = "";
        
        if (albumChoiceBox.getValue() == null) {
            errorMessage += "Выберите альбом из списка \n";           
        }
        else if (songChoiceBox.getValue() == null) {
            errorMessage += "Выберите песню из списка \n";           
        }     
        if (errorMessage.equals("")) {
            return true;
        } 
        else {
            errorMessage(errorMessage);          
            return false;
        }           
    }
       
    @Override
    public void setEntity(Entity entity) {
        musicianSong = (MusicianSongEntity) entity;
        super.setEntity(entity);
    }
    
}
