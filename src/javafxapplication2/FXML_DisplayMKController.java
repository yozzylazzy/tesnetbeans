/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXML_DisplayMKController implements Initializable {

    @FXML
    private TableView<MKModel> tbvmk;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<MKModel> data=FXMLDocumentController.dtmk.Load();
        if(data!=null){            
            tbvmk.getColumns().clear();
            tbvmk.getItems().clear();
            TableColumn col=new TableColumn("KodeMK");
            col.setCellValueFactory(new PropertyValueFactory<MKModel, String>("kodemk"));
            tbvmk.getColumns().addAll(col);
            col=new TableColumn("NamaMK");
            col.setCellValueFactory(new PropertyValueFactory<MKModel, String>("namamk"));
            tbvmk.getColumns().addAll(col);
            col=new TableColumn("SkS");
            col.setCellValueFactory(new PropertyValueFactory<MKModel, String>("sks"));
            tbvmk.getColumns().addAll(col);
            col=new TableColumn("Praktek");
            col.setCellValueFactory(new PropertyValueFactory<MKModel, String>("praktek"));
            tbvmk.getColumns().addAll(col);
            tbvmk.setItems(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvmk.getScene().getWindow().hide();;
        }        

    }    
    
}
