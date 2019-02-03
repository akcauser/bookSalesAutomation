/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje_java;

/**
 *
 * @author ertugrulgaziakca
 */
public class Kitap {

    static String hakkında = "Yayın kitabevine hoşgeldiniz.İyi alışverişler dileriz.";

    private String kitap_adi;
    private String kitap_yazari;
    private String kitap_sayfasi;
    private String kitap_fiyati;

    public String getKitap_adi() {
        return kitap_adi;
    }

    public void setKitap_adi(String kitap_adi) {
        this.kitap_adi = kitap_adi;
    }

    public String getKitap_yazari() {
        return kitap_yazari;
    }

    public void setKitap_yazari(String kitap_yazari) {
        this.kitap_yazari = kitap_yazari;
    }

    public String getKitap_sayfasi() {
        return kitap_sayfasi;
    }

    public void setKitap_sayfasi(String kitap_sayfasi) {
        this.kitap_sayfasi = kitap_sayfasi;
    }

    public String getKitap_fiyati() {
        return kitap_fiyati;
    }

    public void setKitap_fiyati(String kitap_fiyati) {
        this.kitap_fiyati = kitap_fiyati;
    }

}
