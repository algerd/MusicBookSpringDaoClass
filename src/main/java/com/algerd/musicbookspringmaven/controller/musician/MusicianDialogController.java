package com.algerd.musicbookspringmaven.controller.musician;

import com.algerd.musicbookspringmaven.controller.BaseDialogController;
import java.net.URL;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ListView;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import com.algerd.musicbookspringmaven.repository.Entity;
import com.algerd.musicbookspringmaven.controller.helper.inputImageBox.DialogImageBoxController;
import com.algerd.musicbookspringmaven.utils.Helper;
import com.algerd.musicbookspringmaven.repository.Musician.MusicianEntity;
import com.algerd.musicbookspringmaven.Params;
import com.algerd.musicbookspringmaven.repository.Genre.GenreEntity;
import com.algerd.musicbookspringmaven.repository.Instrument.InstrumentEntity;
import com.algerd.musicbookspringmaven.repository.MusicianGenre.MusicianGenreEntity;
import com.algerd.musicbookspringmaven.repository.MusicianInstrument.MusicianInstrumentEntity;
import com.algerd.musicbookspringmaven.controller.helper.choiceCheckBox.ChoiceCheckBoxController;

public class MusicianDialogController extends BaseDialogController {
    
    private MusicianEntity musician;
    private final IntegerProperty rating = new SimpleIntegerProperty();
     
    @FXML
    private AnchorPane view;
    @FXML
    private TextField nameTextField;   
    @FXML
    private DatePicker dobDatePicker;
    @FXML
    private DatePicker dodDatePicker;
    @FXML
    private TextField countryTextField;
    @FXML
    private Spinner<Integer> ratingSpinner;
    @FXML
    private ListView<GenreEntity> genreListView;
    @FXML
    private TextArea commentTextArea;
    
    @FXML
    private AnchorPane includedDialogImageBox;
    @FXML
    private DialogImageBoxController includedDialogImageBoxController;
    @FXML   
    private AnchorPane includedGenreChoiceCheckBox;
    @FXML
    private ChoiceCheckBoxController<GenreEntity> includedGenreChoiceCheckBoxController; 
    @FXML   
    private AnchorPane includedInstrumentChoiceCheckBox;
    @FXML
    private ChoiceCheckBoxController<InstrumentEntity> includedInstrumentChoiceCheckBoxController; 

    @Override
    public void initialize(URL url, ResourceBundle rb) {       
        Helper.initIntegerSpinner(ratingSpinner, Params.MIN_RATING, Params.MAX_RATING, Params.MIN_RATING, true, rating);
        Helper.initDatePicker(dobDatePicker, true);
        Helper.initDatePicker(dodDatePicker, true);  
        // ограничить поле ввода указав вторым параметром предельное количество символов
        Helper.limitTextInput(nameTextField, 255);
        Helper.limitTextInput(countryTextField, 255);
        Helper.limitTextInput(commentTextArea, 1000);
        includedDialogImageBoxController.setStage(dialogStage);
               
        includedGenreChoiceCheckBoxController.setMainPane(view);
        includedGenreChoiceCheckBoxController.getChoiceCheckBox().setMaxWidth(250.0);
        includedGenreChoiceCheckBoxController.getChoiceCheckBox().setPrefWidth(250.0);
        
        includedInstrumentChoiceCheckBoxController.setMainPane(view);
        includedInstrumentChoiceCheckBoxController.getChoiceCheckBox().setMaxWidth(250.0);
        includedInstrumentChoiceCheckBoxController.getChoiceCheckBox().setPrefWidth(250.0);
    }
    
    private void initGenreChoiceCheckBox() {
        List<GenreEntity> musicianGenres = new ArrayList<>();
        if (edit) {        
            repositoryService.getMusicianGenreRepository().selectMusicianGenreByMusician(musician).stream().forEach(musicianGenre -> {
                musicianGenres.add(musicianGenre.getGenre());
            });
        }
        Map<GenreEntity, ObservableValue<Boolean>> map = new HashMap<>();
        repositoryService.getGenreRepository().selectAll().stream().forEach(genre -> {                     
            map.put(genre, new SimpleBooleanProperty(musicianGenres.contains(genre)));
        });
        includedGenreChoiceCheckBoxController.addItems(map);       
    } 
    
    private void initInstrumentChoiceCheckBox() {
        List<InstrumentEntity> musicianInstruments = new ArrayList<>();
        if (edit) {        
            repositoryService.getMusicianInstrumentRepository().selectMusicianInstrumentByMusician(musician).stream().forEach(musicianInstrument -> {
                musicianInstruments.add(musicianInstrument.getInstrument());
            });
        }
        Map<InstrumentEntity, ObservableValue<Boolean>> map = new HashMap<>();
        repositoryService.getInstrumentRepository().selectAll().stream().forEach(instrument -> {                     
            map.put(instrument, new SimpleBooleanProperty(musicianInstruments.contains(instrument)));
        });
        includedInstrumentChoiceCheckBoxController.addItems(map);       
    } 
     
    @FXML
    private void handleOkButton() {      
        if (isInputValid()) {
            musician.setName(nameTextField.getText().trim());
            musician.setDate_of_birth(dobDatePicker.getEditor().getText());
            musician.setDate_of_death(dodDatePicker.getEditor().getText());
            musician.setCountry(countryTextField.getText().trim());
            musician.setRating(getRating());
            musician.setDescription(commentTextArea.getText());
            /*
            Если музыкант создаётся снуля, то сначала надо его сохранить,
            получить его id (в объекте musician) и потом сохранить связку musicianGenre
            */
            if (!edit) {
                repositoryService.getMusicianRepository().save(musician);   
            } else {           
                // Cначала удалить все жанры из бд для музыканта, а потом добавить
                repositoryService.getMusicianGenreRepository().deleteMusicianGenreByMusician(musician);
                // Cначала удалить все инструменты из бд для музыканта, а потом добавить
                repositoryService.getMusicianInstrumentRepository().deleteMusicianInstrumentByMusician(musician);
            } 
            // Извлечь жанры из списка и сохранить их в связке связанные с музыкантом
            for (GenreEntity genre : includedGenreChoiceCheckBoxController.getItemMap().keySet()) {
                ObservableValue<Boolean> value = includedGenreChoiceCheckBoxController.getItemMap().get(genre);
                if (value.getValue()) {
                    MusicianGenreEntity musicianGenre = new MusicianGenreEntity();
                    musicianGenre.setId_musician(musician.getId());
                    musicianGenre.setId_genre(genre.getId());
                    repositoryService.getMusicianGenreRepository().save(musicianGenre);
                }
            }  
            // Извлечь инструменты из списка и сохранить их в связке связанные с музыкантом
            for (InstrumentEntity instrument : includedInstrumentChoiceCheckBoxController.getItemMap().keySet()) {
                ObservableValue<Boolean> value = includedInstrumentChoiceCheckBoxController.getItemMap().get(instrument);
                if (value.getValue()) {
                    MusicianInstrumentEntity musicianInstrument = new MusicianInstrumentEntity();
                    musicianInstrument.setId_musician(musician.getId());
                    musicianInstrument.setId_instrument(instrument.getId());
                    repositoryService.getMusicianInstrumentRepository().save(musicianInstrument);
                }
            }
            
            if (includedDialogImageBoxController.isChangedImage()) {
                includedDialogImageBoxController.saveImage();
                includedDialogImageBoxController.setChangedImage(false);                              
            }
            if (edit) {
                repositoryService.getMusicianRepository().save(musician);
            } else {
                requestPageService.musicianPane(musician);
            }            
            dialogStage.close();
            edit = false;
        }
    }
    
    @FXML
    private void handleCancelButton() {
        dialogStage.close();
    }
    
    @Override
    protected void add() {
        includedDialogImageBoxController.setEntity(musician);
        initGenreChoiceCheckBox();
        initInstrumentChoiceCheckBox();
    }
       
    @Override
    protected void edit() {
        edit = true;
        nameTextField.setText(musician.getName());            
        dobDatePicker.setValue(dobDatePicker.getConverter().fromString(musician.getDate_of_birth()));
        dodDatePicker.setValue(dodDatePicker.getConverter().fromString(musician.getDate_of_death()));
        countryTextField.setText(musician.getCountry());
        ratingSpinner.getValueFactory().setValue(musician.getRating());
        commentTextArea.setText(musician.getDescription());              
        add();
        includedDialogImageBoxController.setImage(includedDialogImageBoxController.getImageFile());      
    }
    
    @Override
    protected boolean isInputValid() {
        String errorMessage = "";       
        if (nameTextField.getText() == null || nameTextField.getText().trim().equals("")) {
            errorMessage += "Введите название музыканта!\n"; 
        }
        if (!edit && !repositoryService.getMusicianRepository().isUniqueColumnValue("name", nameTextField.getText())) {
            errorMessage += "Такой музыкант уже есть!\n";
        }
        try { 
            dobDatePicker.getConverter().fromString(dobDatePicker.getEditor().getText());
        } catch (DateTimeParseException e) {
            errorMessage += "Неправильно введён формат Date Of Birth " + dobDatePicker.getEditor().getText() +". Надо mm.dd.yyyy \n";
        }
        
        try { 
            dodDatePicker.getConverter().fromString(dodDatePicker.getEditor().getText());
        } catch (DateTimeParseException e) {
            errorMessage += "Неправильно введён формат Date Of Death " + dodDatePicker.getEditor().getText() +". Надо mm.dd.yyyy \n";
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
        musician = (MusicianEntity) entity;
        super.setEntity(entity);
    }
    
    public int getRating() {
        return rating.get();
    }
    public void setRating(int value) {
        rating.set(value);
    }
    public IntegerProperty ratingProperty() {
        return rating;
    }

    public AnchorPane getIncludedDialogImageBox() {
        return includedDialogImageBox;
    }
    public DialogImageBoxController getIncludedDialogImageBoxController() {
        return includedDialogImageBoxController;
    } 
     
}
