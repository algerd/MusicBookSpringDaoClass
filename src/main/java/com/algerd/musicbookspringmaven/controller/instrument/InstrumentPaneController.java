
package com.algerd.musicbookspringmaven.controller.instrument;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import com.algerd.musicbookspringmaven.utils.ImageUtil;
import com.algerd.musicbookspringmaven.controller.BasePaneController;
import com.algerd.musicbookspringmaven.Params;
import com.algerd.musicbookspringmaven.repository.Entity;
import com.algerd.musicbookspringmaven.repository.Instrument.InstrumentEntity;
import com.algerd.musicbookspringmaven.repository.impl.WrapChangedEntity;
import static com.algerd.musicbookspringmaven.service.ContextMenuItemType.ADD_INSTRUMENT;
import static com.algerd.musicbookspringmaven.service.ContextMenuItemType.DELETE_INSTRUMENT;
import static com.algerd.musicbookspringmaven.service.ContextMenuItemType.EDIT_INSTRUMENT;
import org.springframework.stereotype.Controller;

@Controller
public class InstrumentPaneController extends BasePaneController {

    private InstrumentEntity instrument;
    private File file;
    private long imageLastModified;
    
    @FXML
    private TabPane instrumentTabPane; 
    @FXML
    private ImageView instrumentImageView;
    @FXML
    private Label nameLabel;    
    @FXML
    private Text descriptionText;
    
    @FXML
    private AnchorPane musicianTable; 
    @FXML
    private MusicianTableController includedMusicianTableController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }  
    
    @Override
    public void show(Entity entity) {
        instrument = (InstrumentEntity) entity;  
        showDetails(); 
        view.setVisible(true);
        initRepositoryListeners();
        includedMusicianTableController.setPaneController(this);
        instrumentTabPane.getSelectionModel().selectFirst();
    } 

    private void initRepositoryListeners() {
        repositoryService.getInstrumentRepository().clearDeleteListeners(this);
        repositoryService.getInstrumentRepository().clearUpdateListeners(this);
        
        repositoryService.getInstrumentRepository().addDeleteListener(this::deletedInstrument, this);
        repositoryService.getInstrumentRepository().addUpdateListener(this::updatedInstrument, this);
    }
    
    private void deletedInstrument(ObservableValue observable, Object oldVal, Object newVal) {
        InstrumentEntity newEntity = ((WrapChangedEntity<InstrumentEntity>) newVal).getNew();
        if (newEntity.getId() == instrument.getId()) {
            view.setVisible(false);
        }
    }
    
    private void updatedInstrument(ObservableValue observable, Object oldVal, Object newVal) {
        if (file.lastModified() != imageLastModified) {
            instrumentImageView.setImage(ImageUtil.readImage(file));
            imageLastModified = file.lastModified();
        }
    }
    
    private void showDetails() {  
        nameLabel.textProperty().bind(instrument.nameProperty()); 
        descriptionText.textProperty().bind(instrument.descriptionProperty());
        
        // проверить и загрузить изображение
        file = new File(Params.DIR_IMAGE_ENTITY + "instrument/" + instrument.getId() + "." + Params.SAVED_IMAGE_FORMAT);
        instrumentImageView.setImage(ImageUtil.readImage(file));
        imageLastModified = file.lastModified();       
    }
    
    /**
     * При ПКМ по странице инструмента показать контекстное меню.
     */
    @FXML
    private void showContextMenu(MouseEvent mouseEvent) {
        contextMenuService.clear();
		if (mouseEvent.getButton() == MouseButton.SECONDARY) {       
            contextMenuService.add(ADD_INSTRUMENT, new InstrumentEntity());
            contextMenuService.add(EDIT_INSTRUMENT, instrument);
            contextMenuService.add(DELETE_INSTRUMENT, instrument);
            contextMenuService.show(view, mouseEvent);
        }      
    }

    public InstrumentEntity getInstrument() {
        return instrument;
    }
    
}
