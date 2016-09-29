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
import com.algerd.musicbookspringmaven.repository.MusicianAlbum.MusicianAlbumEntity;
import com.algerd.musicbookspringmaven.utils.Helper;
import javafx.scene.layout.AnchorPane;

public class MusicianAlbumDialogController extends BaseDialogController {
   
    private MusicianAlbumEntity musicianAlbum;    
    
    @FXML
    private AnchorPane view;
    @FXML
    private ChoiceBox<MusicianEntity> musicianChoiceBox;
    @FXML
    private ChoiceBox<ArtistEntity> artistChoiceBox;
    @FXML
    private ChoiceBox<AlbumEntity> albumChoiceBox;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {       
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
        artistChoiceBox.getSelectionModel().selectedItemProperty().addListener(this::changedArtistChoiceBox);
        artistChoiceBox.getSelectionModel().selectFirst();
    }
    
    private void changedArtistChoiceBox(ObservableValue<? extends Object> observable, ArtistEntity oldValue, ArtistEntity newValue) {          
        albumChoiceBox.getItems().clear();
        ArtistEntity artist = artistChoiceBox.getSelectionModel().getSelectedItem();
        if (artist != null) {
            albumChoiceBox.getItems().addAll(repositoryService.getAlbumRepository().selectAlbumByArtist(artist));
            albumChoiceBox.getSelectionModel().selectFirst();
        }    
    }

    @FXML
    private void handleOkButton() {
        if (isInputValid()) {
            MusicianEntity musician = musicianChoiceBox.getValue();
            AlbumEntity album = albumChoiceBox.getValue();
            if (!repositoryService.getMusicianAlbumRepository().containsMusicianAlbumByMusicianAndAlbum(musician, album)) {
                musicianAlbum.setId_musician(musician.getId());
                musicianAlbum.setId_album(album.getId());
                repositoryService.getMusicianAlbumRepository().save(musicianAlbum);               
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
        musicianChoiceBox.getSelectionModel().select(repositoryService.getMusicianRepository().selectById(musicianAlbum.getId_musician()));
   
        AlbumEntity album = repositoryService.getAlbumRepository().selectById(musicianAlbum.getId_album());
        ArtistEntity artist = repositoryService.getArtistRepository().selectById(album.getId_artist());          
        artistChoiceBox.getSelectionModel().select(artist);
        albumChoiceBox.getSelectionModel().select(album);      
    }
       
    @Override
    public void setEntity(Entity entity) {
        musicianAlbum = (MusicianAlbumEntity) entity;
        super.setEntity(entity);
    }
    
    @Override
    protected boolean isInputValid() {
        String errorMessage = "";
        
        if (albumChoiceBox.getValue() == null) {
            errorMessage += "Выберите альбом из списка артиста\n";
        }              
        if (errorMessage.equals("")) {
            return true;
        } 
        else {
            errorMessage(errorMessage);         
            return false;
        }   
    }  
       
}
