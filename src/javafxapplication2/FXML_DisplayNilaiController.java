/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXML_DisplayNilaiController implements Initializable {

    @FXML
    private TableView<NilaiModel> tbvnilai;
    @FXML
    private Button btnkeluar;
    @FXML
    private Button btnubah;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btntambah;
    @FXML
    private Button btnakhir;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btnawal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         showdata();
    }    

    public void showdata(){
        ObservableList<NilaiModel> data=FXMLDocumentController.dtnilai.Load();
        if(data!=null){            
            tbvnilai.getColumns().clear();
            tbvnilai.getItems().clear();
            TableColumn col=new TableColumn("NPM");
            col.setCellValueFactory(new PropertyValueFactory<NilaiModel, String>("npm"));
            col.setMinWidth(200);
            col.setMaxWidth(200);
            tbvnilai.getColumns().addAll(col);
            col=new TableColumn("KodeMK");
            col.setCellValueFactory(new PropertyValueFactory<NilaiModel, String>("kodemk"));
            tbvnilai.getColumns().addAll(col);
            col=new TableColumn("Tanggal");
/*            col.setCellFactory(column -> {
    TableCell<NilaiModel, String> cell = new TableCell<NilaiModel, String>() {
//        private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if(empty) {
                setText(null);
            }
            else {
                setText(item.toString());
//                setText(format.format(item));
            }
        }
    };

    return cell;
});*/
//            col.setCellValueFactory(new PropertyValueFactory<NilaiModel, String>("tanggal"));
//            col.setCellValueFactory(new PropertyValueFactory<NilaiModel, Date>("tanggal"));
//            col.setCellValueFactory(new FormattedDateValueFactory<NilaiModel>("date", "dd/MM/yyyy"));
//        n.setTanggal(Date.valueOf(dtptanggal.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));        

//            dateColumn.setCellValueFactory(new FormattedDateValueFactory<InvoiceTableEntry>("date", "dd/MM/yyyy"));}
/*            columnDate.setCellFactory(column -> {
    TableCell<PatientEntity, Date> cell = new TableCell<PatientEntity, Date>() {
        private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        @Override
        protected void updateItem(Date item, boolean empty) {
            super.updateItem(item, empty);
            if(empty) {
                setText(null);
            }
            else {
                setText(format.format(item));
            }
        }
    };

    return cell;
});*/
            
            
            tbvnilai.getColumns().addAll(col);
            col=new TableColumn("Nilai");
            col.setCellValueFactory(new PropertyValueFactory<NilaiModel, String>("nilai"));
/*            col.setCellFactory(column -> {
    TableCell<NilaiModel, Double> cell = new TableCell<NilaiModel, Double>() {
//        private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        @Override
        protected void updateItem(Double item, boolean empty) {
            super.updateItem(item, empty);
            if(empty) {
                setText(null);
            }
            else {
                setText(String.valueOf(item.doubleValue()));
//                setText((new DecimalFormat("Rp #,##0")).format(item.doubleValue()));
//                setText(item.toString());
//                setText(format.format(item));
            }
        }
    };

    return cell;
});*/
            
            
            
            col.setMinWidth(100);
            col.setMaxWidth(100);
            tbvnilai.getColumns().addAll(col);
            col=new TableColumn("Hadir");
            col.setCellValueFactory(new PropertyValueFactory<NilaiModel, String>("hadir"));
            col.setMinWidth(100);
            col.setMaxWidth(100);
            tbvnilai.getColumns().addAll(col);
            tbvnilai.setItems(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvnilai.getScene().getWindow().hide();;
        }                
    }
   

    @FXML
    private void awalklik(ActionEvent event) {
        tbvnilai.getSelectionModel().selectFirst();
        tbvnilai.requestFocus();
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
        tbvnilai.getSelectionModel().selectAboveCell();
        tbvnilai.requestFocus();
    }

    @FXML
    private void sesudahklik(ActionEvent event) {
        tbvnilai.getSelectionModel().selectBelowCell();
        tbvnilai.requestFocus();
    }

    @FXML
    private void akhirklik(ActionEvent event) {
        tbvnilai.getSelectionModel().selectLast();
        tbvnilai.requestFocus();
    }

    @FXML
    private void tambahklik(ActionEvent event) {
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_InputNilai.fxml"));    
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
       NilaiModel s= new NilaiModel();
       s=tbvnilai.getSelectionModel().getSelectedItem();
       Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",
               ButtonType.YES,ButtonType.NO);
       a.showAndWait();
       if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.dtnilai.delete(s.getNpm(),s.getKodemk())){
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
        NilaiModel s= new NilaiModel();
        s=tbvnilai.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_InputNilai.fxml"));    
        Parent root = (Parent)loader.load();
        FXML_InputNilaiController isidt=(FXML_InputNilaiController)loader.getController();
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
