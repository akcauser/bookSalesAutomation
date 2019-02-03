/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje_java;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author ertugrulgaziakca
 */
public class DBConnection {

    private Statement st;
    private Connection con;
    private ResultSet rs;
    
    
    public DBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/odev", "root", "");
            st = con.createStatement();
        } catch (Exception e) {
            System.out.println("Error  : " + e);
        }
    }

    public void addData(String addedName, String addedPhoneNumber, String addedPassword) {
        String add = "INSERT INTO `kullanici_bilgileri`(`kullanici_adi`, `telefon`, `password`) VALUES ('" + addedName + "','" + addedPhoneNumber + "','" + addedPassword + "')";

        try {
            st.executeUpdate(add);
            System.out.println("Veri eklendi");
        } catch (Exception e) {
            System.out.println("Error occured when adding value to database : " + e);
        }
    }
    public void updatePass(String kullancıAdi, String newPass) {
        String query = "UPDATE `kullanici_bilgileri` SET `password`='"+newPass+"' WHERE `kullanici_adi`='"+kullancıAdi+"'";

        try {
            st.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Error occured when adding value to database : " + e);
        }
    }

    public void getData(String tablo_adi, String hVeri) { //veriler okunuyor.
        String data = null;
        try {
            String query = "SELECT * FROM " + tablo_adi;
            rs = st.executeQuery(query);
            while (rs.next()) {
                data = rs.getString(hVeri);
                System.out.println(data);
            }
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }

    public boolean existsData(String tablo_adi, String hVeri, String value) {

        String query = "SELECT * FROM " + tablo_adi + " WHERE EXISTS (SELECT * FROM " + tablo_adi + " WHERE " + hVeri + "='" + value + "')";
        try {
            rs = st.executeQuery(query);
            while (rs.next()) {
                if (value.equals(rs.getString(hVeri))) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            System.out.println("Error occured when finding value from database : " + e);
        }
        return false;
    }
    public void getItemData(String tablo_adi, String hVeri, String key, String value){
        String data = null;
        try {
            String query = "SELECT "+hVeri+" FROM `"+tablo_adi+"` WHERE EXISTS(SELECT * FROM "+tablo_adi+" WHERE "+key+"='"+value+"') AND "+key+"='"+value+"'";
            rs = st.executeQuery(query);
            while(rs.next()){
                data = rs.getString(hVeri);
                System.out.println(data);
            }
        } catch (Exception e) {
            System.out.println("Error:"+ e);
        }
    }
    public String getItemDataValue(String tablo_adi, String hVeri, String key, String value){
        String data = null;
        try {
            String query = "SELECT "+hVeri+" FROM `"+tablo_adi+"` WHERE EXISTS(SELECT * FROM "+tablo_adi+" WHERE "+key+"='"+value+"') AND "+key+"='"+value+"'";
            rs = st.executeQuery(query);
            while(rs.next()){
                data = rs.getString(hVeri);
                return data;
            }
        } catch (Exception e) {
            System.out.println("Error:"+ e);
        }
        return "";
    }
     public void get2Data(String tablo_adi, String hVeri, String hVeri2){
        String data = null;
        String data2 = null;
        try {
            String query = "SELECT "+hVeri+","+hVeri2+" FROM `"+tablo_adi+"`";
            rs = st.executeQuery(query);
            while(rs.next()){
                data2 = rs.getString(hVeri2);
                data = rs.getString(hVeri);
                System.out.println(data2+"-"+data);
            }
        } catch (Exception e) {
            System.out.println("Error:"+ e);
        }
    }

}
