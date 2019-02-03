/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje_java;

import java.util.Scanner;

/**
 *
 * @author ertugrulgaziakca
 */
public class Member extends Person {

    private String uye_adi = "";
    private String uye_sifresi = "";
    private String uye_telefon = "";

    String secilenKitaplar[] = new String[5];

    public Member(String uye_adi, String uye_telefon, String uye_sifresi) {
        this.uye_adi = uye_adi;
        this.uye_sifresi = uye_sifresi;
        this.uye_telefon = uye_telefon;
    }

    public String getUye_adi() {
        return uye_adi;
    }

    public void setUye_adi(String uye_adi) {
        this.uye_adi = uye_adi;
    }

    public String getUye_sifresi() {
        return uye_sifresi;
    }

    public void setUye_sifresi(String uye_sifresi) {
        this.uye_sifresi = uye_sifresi;
    }

    public String getUye_telefon() {
        return uye_telefon;
    }

    public void setUye_telefon(String uye_telefon) {
        this.uye_telefon = uye_telefon;
    }

    @Override
    void changePassword() {

        System.out.println("Eski şifreyi gir:");
        //if bloğu şifre doğru mu kontrol et
        System.out.println("Yeni şifreyi gir:");
        System.out.println("Yeni şifreyi tekrar gir:");
        Scanner scan = new Scanner(System.in);
        setUye_sifresi(scan.next());

    }

    @Override
    void kitapSat() {
        //kitap satış işlemi burada yapılcak
    }

    @Override
    void kitapAl() {
        //kitapların listelenmesi ve kitap seçimine gidilmesi 
    }

    @Override
    void sepetiGoruntule(int count) {
      
        System.out.println("Sepetinizdeki kitaplar:");

        for (int i = 0; i < count; i++) {
            if (count == 0) {
                System.out.println("Sepetiniz boş.");
                break;
            }
            System.out.println(secilenKitaplar[i]);
            
        }
    }

    @Override
    void siparisiTamamla(int count) {
       
        System.out.println("Siparişiniz tamamlandı");
        System.out.println("Sipariş edilen kitaplar:");

        for (int i = 0; i < count; i++) {
            System.out.println(secilenKitaplar[i]);
            secilenKitaplar[i] = "";
        }
    }
    

    @Override
    void sepeteEkle(String kitap_adi1, int count) {
        //bir kitabı sepete ekleme  
        //en fazla 3 kitap sepete ekelenebilir 
        secilenKitaplar[count] = kitap_adi1;
    }

    @Override
    void satınAl(String kitap_adi1) {
        //1 kitap satın alma
    }

    @Override
    void satınAl(String kitap_adi1, String kitap_adi2) {
        //2 kitap satın alma
    }

    @Override
    void satınAl(String kitap_adi1, String kitap_adi2, String kitap_adi3) {
        //3 kitap satın alma
    }

    @Override
    void sepetiTemizle(int count) {
        System.out.println("Sepet boşaltıldı");
        for (int i = 0; i < count; i++) {
            secilenKitaplar[count] = "";
        }
    }

}
