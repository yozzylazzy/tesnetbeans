/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import java.net.URL;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXML_InputNilaiController implements Initializable {

    boolean editdata=false;
    
    @FXML
    private TextField txtnpm;
    @FXML
    private TextField txtkodemk;
    @FXML
    private DatePicker dtptanggal;
    @FXML
    private TextField txtnilai;
    @FXML
    private Button btnsimpan;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnkeluar;
    @FXML
    private Label lblhadir;
    @FXML
    private Slider sldhadir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sldhadir.valueProperty().addListener(new ChangeListener<Number>(){
            public void changed(ObservableValue<? extends Number> changed, 
                    Number oldVal, Number newVal){
                lblhadir.setText(String.valueOf(newVal.intValue()));        
                              }   });
    }
    

    public void execute(NilaiModel d){
        if(!d.getNpm().isEmpty()&&!d.getKodemk().isEmpty()){
          editdata=true;
          txtnpm.setText(d.getNpm());
          txtkodemk.setText(d.getKodemk());
          dtptanggal.setValue(d.getTanggal().toLocalDate());  
          sldhadir.setValue(d.getHadir());
          txtnilai.setText(String.valueOf(d.getNilai()));
          txtnpm.setEditable(false);
          txtkodemk.setEditable(false);
          dtptanggal.requestFocus();
        }
    }
    
    @FXML
    private void simpanklik(ActionEvent event) {
        NilaiModel n=new NilaiModel();
        n.setNpm(txtnpm.getText());
        n.setKodemk(txtkodemk.getText());
        n.setHadir((int)sldhadir.getValue());
        n.setNilai(Double.parseDouble(txtnilai.getText()));
        n.setTanggal(Date.valueOf(dtptanggal.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));        
        FXMLDocumentController.dtnilai.setNilaiModel(n);

        if(editdata){
            if(FXMLDocumentController.dtnilai.update()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   txtnpm.setEditable(true);        batalklik(event);                
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
               a.showAndWait();                    
            }
        }else if(FXMLDocumentController.dtnilai.validasi(n.getNpm(),n.getKodemk())<=0){
            if(FXMLDocumentController.dtnilai.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
               a.showAndWait();            batalklik(event);
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
        }else{
            Alert a=new Alert(Alert.AlertType.ERROR,"Data sudah ada",ButtonType.OK);
            a.showAndWait();
            txtnpm.requestFocus();
        }
    }


    @FXML
    private void batalklik(ActionEvent event) {
        txtnpm.setText("");
        txtkodemk.setText("");
        txtnilai.setText("");
        dtptanggal.getEditor().clear();
        sldhadir.setValue(0);
        txtnpm.setEditable(true);        
        txtkodemk.setEditable(true);
        txtnpm.requestFocus();
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }
    
}
