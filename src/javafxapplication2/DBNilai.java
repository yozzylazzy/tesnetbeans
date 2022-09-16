/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author asus
 */
public class DBNilai {
    private NilaiModel dt=new NilaiModel();    
    public NilaiModel getNilaiModel(){ return(dt);}
    public void setNilaiModel(NilaiModel s){ dt=s;}
    
    public ObservableList<NilaiModel>  Load() {
        try {
            ObservableList<NilaiModel> tableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "Select NPM, KodeMK, tanggal, nilai, hadir from nilai");
            int i = 1;
            while (rs.next()) {
                NilaiModel d=new NilaiModel();
                d.setNpm(rs.getString("NPM"));
                d.setKodemk(rs.getString("KodeMK"));
                d.setTanggal(rs.getDate("tanggal"));
                d.setNilai(rs.getDouble("Nilai"));
                d.setHadir(rs.getInt("Hadir"));
                tableData.add(d);
                i++;
            }
            con.tutupKoneksi();
            return tableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
            public int validasi(String nomor, String kode) {
        int val = 0;
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "select count(*) as jml from nilai where NPM = '" + nomor + 
                            "' and kodemk='"+kode+"'");
            while (rs.next()) {
                val = rs.getInt("jml");
            }
            con.tutupKoneksi();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return val;
    }
        
     public boolean insert() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into nilai (NPM,kodemk,tanggal, nilai,hadir) values (?,?,?,?,?)");
            con.preparedStatement.setString(1, getNilaiModel().getNpm());
            con.preparedStatement.setString(2, getNilaiModel().getKodemk());
            con.preparedStatement.setDate(3, getNilaiModel().getTanggal());
            con.preparedStatement.setDouble(4, getNilaiModel().getNilai());
            con.preparedStatement.setInt(5, getNilaiModel().getHadir());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }
        
    public boolean delete(String nomor,String kode) {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from nilai where NPM  = ? and kodemk = ?");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.setString(2, kode);
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

        public boolean update() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement(
                    "update nilai set tanggal = ?, nilai = ?, hadir = ?  where  NPM = ? and kodemk = ? ");
            con.preparedStatement.setDate(1, getNilaiModel().getTanggal());
            con.preparedStatement.setDouble(2, getNilaiModel().getNilai());
            con.preparedStatement.setInt(3, getNilaiModel().getHadir());
            con.preparedStatement.setString(4, getNilaiModel().getNpm());
            con.preparedStatement.setString(5, getNilaiModel().getKodemk());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

}
