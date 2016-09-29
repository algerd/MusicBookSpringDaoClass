package com.algerd.musicbookspringmaven.controller.genre;

import com.algerd.musicbookspringmaven.controller.BaseIncludeController;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import com.algerd.musicbookspringmaven.utils.Helper;
import com.algerd.musicbookspringmaven.repository.Musician.MusicianEntity;
import com.algerd.musicbookspringmaven.repository.MusicianGenre.MusicianGenreEntity;
import com.algerd.musicbookspringmaven.repository.MusicianInstrument.MusicianInstrumentEntity;
import static com.algerd.musicbookspringmaven.service.impl.ContextMenuItemType.ADD_MUSICIAN;
import static com.algerd.musicbookspringmaven.service.impl.ContextMenuItemType.DELETE_MUSICIAN;
import static com.algerd.musicbookspringmaven.service.impl.ContextMenuItemType.EDIT_MUSICIAN;

public class MusicianGenreTableController extends BaseIncludeController<GenrePaneController> {
       
    private MusicianGenreEntity selectedItem;
    
    @FXML
    private AnchorPane musicianGenreTable;
    @FXML
    private Label titleLabel;        
    @FXML
    private TableView<MusicianGenreEntity> musicianTableView;
    @FXML
    private TableColumn<MusicianGenreEntity, Integer> rankColumn;
    @FXML
    private TableColumn<MusicianGenreEntity, String> musicianColumn;
    @FXML
    private TableColumn<MusicianGenreEntity, MusicianGenreEntity> instrumentColumn;
    @FXML
    private TableColumn<MusicianGenreEntity, Integer> ratingColumn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rankColumn.setCellValueFactory(
            cellData -> new SimpleIntegerProperty(musicianTableView.getItems().indexOf(cellData.getValue()) + 1).asObject()
        );
        musicianColumn.setCellValueFactory(cellData ->cellData.getValue().getMusician().nameProperty());
        ratingColumn.setCellValueFactory(cellData ->cellData.getValue().getMusician().ratingProperty().asObject());
        
        instrumentColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        instrumentColumn.setCellFactory(col -> {
            TableCell<MusicianGenreEntity, MusicianGenreEntity> cell = new TableCell<MusicianGenreEntity, MusicianGenreEntity>() {
                @Override
                public void updateItem(MusicianGenreEntity item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    if (!empty) {
                        List<MusicianInstrumentEntity> musicianInstruments = 
                            repositoryService.getMusicianInstrumentRepository().selectMusicianInstrumentByMusician(item.getMusician());
                        String str = "";
                        for (MusicianInstrumentEntity musicianInstrument :  musicianInstruments){
                            str += (!str.equals("")) ? ", " : "";
                            str += musicianInstrument.getInstrument().getName();
                        }
                        this.setText(str);                                         
                    }
                }
            };
			return cell;
        });
    } 
    
    @Override
    public void bootstrap() {     
        setTableValue();
        initListeners();
        initRepositoryListeners(); 
        titleLabel.setText("Музыканты жанра " + paneController.getGenre().getName());
    }
    
    private void initRepositoryListeners() {       
        repositoryService.getMusicianRepository().clearDeleteListeners(this);           
        repositoryService.getMusicianRepository().clearUpdateListeners(this);          
        repositoryService.getGenreRepository().clearDeleteListeners(this);           
        repositoryService.getGenreRepository().clearUpdateListeners(this);      
        repositoryService.getMusicianGenreRepository().clearChangeListeners(this);
        
        repositoryService.getMusicianRepository().addDeleteListener(this::changed, this);           
        repositoryService.getMusicianRepository().addUpdateListener(this::changed, this);          
        repositoryService.getGenreRepository().addDeleteListener(this::changed, this);           
        repositoryService.getGenreRepository().addUpdateListener(this::changed, this);      
        repositoryService.getMusicianGenreRepository().addChangeListener(this::changed, this);      
    }
    
    private void setTableValue() {
        clearSelectionTable();
        musicianTableView.getItems().clear();
        List<MusicianGenreEntity> musicianGenres = repositoryService.getMusicianGenreRepository().selectMusicianGenreByGenre(paneController.getGenre());
        musicianTableView.setItems(FXCollections.observableArrayList(musicianGenres));      
        sort();       
        Helper.setHeightTable(musicianTableView, 10);  
    }
    
    private void initListeners() {
        musicianTableView.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> selectedItem = musicianTableView.getSelectionModel().getSelectedItem()
        );             
    }
    
    public void clearSelectionTable() {
        musicianTableView.getSelectionModel().clearSelection();
        selectedItem = null;
    }
    
    private void sort() {
        clearSelectionTable();
        musicianTableView.getItems().sort(Comparator.comparingInt((MusicianGenreEntity musicianGenre) -> musicianGenre.getMusician().getRating()).reversed());
    }
    
    private void changed(ObservableValue observable, Object oldVal, Object newVal) {
        setTableValue();
    }
    
    /**
     * ЛКМ - зызов окна выбранного элемента;
     * ПКМ - вызов контекстного меню для add, edit, delete.
     */
    @FXML
    private void onMouseClickTable(MouseEvent mouseEvent) {
        boolean isShowingContextMenu = contextMenuService.getContextMenu().isShowing();
        contextMenuService.clear();  
        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            // если контекстное меню выбрано, то лкм сбрасывает контекстное меню и выбор в таблице
            if (isShowingContextMenu) {
                clearSelectionTable();
            }
            // если лкм выбрана запись - показать её
            if (selectedItem != null) { 
                MusicianEntity musician = repositoryService.getMusicianRepository().selectById(selectedItem.getMusician().getId());
                requestPageService.musicianPane(musician);
            }
        }
        else if (mouseEvent.getButton() == MouseButton.SECONDARY) { 
            contextMenuService.add(ADD_MUSICIAN, new MusicianEntity());                    
            if (selectedItem != null) {
                MusicianEntity musician = repositoryService.getMusicianRepository().selectById(selectedItem.getMusician().getId());
                contextMenuService.add(EDIT_MUSICIAN, musician);
                contextMenuService.add(DELETE_MUSICIAN, musician);     
            }
            contextMenuService.show(paneController.getView(), mouseEvent);    
        }
    }
    
}
