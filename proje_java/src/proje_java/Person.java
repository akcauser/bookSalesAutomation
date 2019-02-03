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
public abstract class Person {

    static void showBooks() {

    }
    
    
    abstract void sepetiTemizle(int count);

    abstract void changePassword();

    abstract void kitapSat();

    abstract void kitapAl();

    abstract void sepetiGoruntule(int count);
    
    abstract void siparisiTamamla(int count);

    abstract void sepeteEkle(String kitap_adi1, int count);

    abstract void satınAl(String kitap_adi1);

    abstract void satınAl(String kitap_adi1, String kitap_adi2);

    abstract void satınAl(String kitap_adi1, String kitap_adi2, String kitap_adi3);

}
