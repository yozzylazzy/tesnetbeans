/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXML_DisplaySiswaController implements Initializable {
    
    @FXML
    private TableView<SiswaModel> tbvsiswa;
    @FXML
    private Button btnawal;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btnakhir;
    @FXML
    private Button btntambah;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btnubah;
    @FXML
    private Button btnkeluar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
    }    
    
    private void showdata(){
        ObservableList<SiswaModel> data=FXMLDocumentController.dtsiswa.Load();
        if(data!=null){            
            tbvsiswa.getColumns().clear();
            tbvsiswa.getItems().clear();
            TableColumn col=new TableColumn("NPM");
            col.setCellValueFactory(new PropertyValueFactory<SiswaModel, String>("NPM"));
            tbvsiswa.getColumns().addAll(col);
            col=new TableColumn("Nama");
            col.setCellValueFactory(new PropertyValueFactory<SiswaModel, String>("Nama"));
            tbvsiswa.getColumns().addAll(col);
            col=new TableColumn("Alamat");
            col.setCellValueFactory(new PropertyValueFactory<SiswaModel, String>("Alamat"));
            tbvsiswa.getColumns().addAll(col);
            tbvsiswa.setItems(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvsiswa.getScene().getWindow().hide();;
        }                
    }

    @FXML
    private void awalklik(ActionEvent event) {
        tbvsiswa.getSelectionModel().selectFirst();
        tbvsiswa.requestFocus();
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
        tbvsiswa.getSelectionModel().selectAboveCell();
        tbvsiswa.requestFocus();
    }

    @FXML
    private void sesudahklik(ActionEvent event) {
        tbvsiswa.getSelectionModel().selectBelowCell();
        tbvsiswa.requestFocus();
    }

    @FXML
    private void akhirklik(ActionEvent event) {
        tbvsiswa.getSelectionModel().selectLast();
        tbvsiswa.requestFocus();
    }

    @FXML
    private void tambahklik(ActionEvent event) {
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_InputSiswa.fxml"));    
        Parent root = (Parent)loader.load();

        Scene scene = new Scene(root);
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(scene);
        stg.showAndWait();
        } catch (IOException e){   e.printStackTrace();   }
        showdata();
        awalklik(event);
}

    @FXML
    private void hapusklik(ActionEvent event) {
       SiswaModel s= new SiswaModel();
       s=tbvsiswa.getSelectionModel().getSelectedItem();
       Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",
               ButtonType.YES,ButtonType.NO);
       a.showAndWait();
       if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.dtsiswa.delete(s.getNPM())){
               Alert b=new Alert(Alert.AlertType.INFORMATION,
                   "Data berhasil dihapus", ButtonType.OK);
               b.showAndWait();
           } else {
               Alert b=new Alert(Alert.AlertType.ERROR,
                   "Data gagal dihapus", ButtonType.OK);
               b.showAndWait();               
           }    
           showdata();
           awalklik(event);
       }
    }

    @FXML
    private void ubahklik(ActionEvent event) {
        SiswaModel s= new SiswaModel();
        s=tbvsiswa.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_InputSiswa.fxml"));    
        Parent root = (Parent)loader.load();
        FXML_InputSiswaController isidt=(FXML_InputSiswaController)loader.getController();
        isidt.execute(s);                
        Scene scene = new Scene(root);
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(scene);
        stg.showAndWait();
        } catch (IOException e){   e.printStackTrace();   }
        showdata();  awalklik(event);
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }

    
}
