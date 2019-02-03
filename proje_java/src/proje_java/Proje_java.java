/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje_java;

import java.io.IOException;
import java.util.Scanner;
import java.sql.*;

/**
 *
 * @author ertugrulgaziakca
 */
public class Proje_java {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        DBConnection dBConnection = new DBConnection();

        int üyeOlmadanGelenSayac = 0;

        System.out.println(Kitap.hakkında);
        
        
        
        //TODO:First Loop
        while (true) {

            Scanner scanner = new Scanner(System.in);
            try {

                int secim;
                if (üyeOlmadanGelenSayac == 1) {
                    secim = 2;
                } else {
                    System.out.println("----------------------------");
                    System.out.println("| 1.Üye Girişi\t\t   |");
                    System.out.println("| 2.Üye Ol\t\t   |");
                    System.out.println("| 3.Üye Olmadan devam et   |");
                    System.out.println("| 4.Ayrıl\t\t   |");
                    System.out.println("----------------------------");
                    System.out.print("Seçiminiz: ");
                    secim = scanner.nextInt();
                }
                System.out.print("\n");
                if (secim == 4) {
                    System.out.println("İyi günler dileriz.");
                    break;
                }
                switch (secim) {
                    case 1:
                        Member current_member;
                        System.out.print("Kullanıcı adınızı giriniz:");
                        String temp_kullanici_adi = scanner.next();
                        if (dBConnection.existsData("kullanici_bilgileri", "kullanici_adi", temp_kullanici_adi)) {
                            System.out.println("Şifrenizi giriniz:");
                            String temp_sifre = scanner.next();
                            if (dBConnection.existsData("kullanici_bilgileri", "password", temp_sifre)) {
                                System.out.println("Giriş Yapıldı");
                                //kullanıcı artık içerde. kullanıcı adı ve şifresi belli -- telefon ve bakiye veritabanından çekilmeli nesnemiz oluşturulmalı
                                current_member = new Member(temp_kullanici_adi, dBConnection.getItemDataValue("kullanici_bilgileri", "telefon", "kullanici_adi", temp_kullanici_adi) , temp_sifre);
                                int girisCikisSayaci = 0;
                                int counter = 0;

                                while (girisCikisSayaci == 0) {
                                    System.out.println("----------------------------");
                                    System.out.println("| 1.Kitap bilgileri Listele|");
                                    System.out.println("| 2.Kitap Seç\t\t   |");
                                    System.out.println("| 3.Siparişi tamamla\t   |");
                                    System.out.println("| 4.Şifre değiştir\t   |");
                                    System.out.println("| 5.Sepeti Boşalt\t   |");
                                    System.out.println("| 6.Bilgilerimi göster     |");
                                    System.out.println("| 7.Çıkış Yap\t\t   |");
                                    System.out.println("----------------------------");
                                    System.out.print("Seçiminiz:");
                                    switch (scanner.next()) {
                                        case "1":
                                            dBConnection.get2Data("kitap", "kitap_adi", "id");
                                            break;
                                        case "2":
                                            if (counter == 5) {
                                                System.out.println("Aynı anda 5 kitap sipariş edebilirsiniz.");
                                                break;
                                            }
                                            System.out.println("Kitap seçme");
                                            System.out.println("Eklenecek kitabın sıra numarasını giriniz.");
                                            int kitapSirasi = scanner.nextInt();
                                            String kitap_ismi = dBConnection.getItemDataValue("kitap", "kitap_adi", "id", ""+kitapSirasi);
                                            System.out.println(kitap_ismi);
                                            current_member.sepeteEkle(kitap_ismi, counter);

                                            counter += 1;
                                            break;
                                        case "3":
                                            if (counter == 0) {
                                                System.out.println("Sepetiniz boş");
                                            }else{
                                                current_member.sepetiGoruntule(counter);
                                                System.out.println("Kredi kartı Numaranızı giriniz:");
                                                int krediNo = scanner.nextInt();
                                                
                                                if(krediNo != 0){
                                                current_member.siparisiTamamla(counter);
                                                }
                                                
                                            }
                                            

                                            counter = 0;
                                            break;
                                        case "4":
                                            System.out.print("Eski şifrenizi girin:");
                                            String oldPass = scanner.next();
                                            if (oldPass.equals(current_member.getUye_sifresi())) {
                                                System.out.print("Yeni şifrenizi girin:");
                                                String newPass = scanner.next();
                                                System.out.print("Yeni şifreyi tekrar girin:");
                                                if (newPass.equals(scanner.next())) {
                                                    dBConnection.updatePass(current_member.getUye_adi(), newPass);
                                                }else{
                                                    System.out.println("Yeni şifreler uyuşmuyor!");
                                                    break;
                                                }
                                            }else{
                                                System.out.println("Eski şifre uyuşmuyor!");
                                                break;
                                            }
                                            //şifre değiştirme sorgusu yapılacak 
                                            break; 
                                        case "5":
                                            System.out.println("Sepet Boşaltıldı!");
                                            current_member.sepetiTemizle(counter);
                                            counter = 0;    
                                        case "6":
                                            System.out.println("*****");
                                            System.out.println("Kullanıcı Adı:"+current_member.getUye_adi()+"\nTelefon Numarası:"+current_member.getUye_telefon());
                                            System.out.println("*****");
                                            break;    
                                        case "7":
                                            System.out.println("Çıkış Yapıldı!");
                                            girisCikisSayaci = 1;
                                            break;
                                        
                                        default:
                                            System.out.println("Geçerli bir seçim yapınız!");
                                            break;

                                    }
                                }
                            } else {
                                System.out.println("Şifreniz yanlıştır!");
                            }
                        } else {
                            System.out.println("Girdiğiniz kullanıcı bulunamadı!");
                            continue;
                        }
                        break;
                    case 2:
                        üyeOlmadanGelenSayac = 0;
                        Member new_member;
                        System.out.print("Kullanıcı adı girin:");
                        String adi = scanner.next();
                        if(dBConnection.existsData("kullanici_bilgileri", "kullanici_adi", adi)){
                            System.out.println("Girdiğiniz kullanıcı adı üye listemizde bulunmaktadır");
                            break;
                        }
                        System.out.print("Telefon numarası giriniz:");
                        String telNo = scanner.next();
                        System.out.print("Şifre giriniz:");
                        String temp = scanner.next();
                        System.out.print("Şifrenizi tekrar giriniz:");
                        if (temp.equals(scanner.next())) {
                            new_member = new Member(adi, telNo, temp);
                            System.out.println("Üyelik tamamlandı giriş yapabilirsiniz.");
                            //Üye bilgileri veri tabanına eklenir
                            dBConnection.addData(new_member.getUye_adi(), new_member.getUye_telefon(), new_member.getUye_sifresi());
                        } else {
                            System.out.println("Şifreler eşleşmiyor");
                            break;
                        }
                        break;
                    case 3:
                        //üye olmadan devam et
                        int girisCikisSayaci = 0;
                        while (girisCikisSayaci == 0) {
                            System.out.println("----------------------------");
                            System.out.println("| 1.Kitap bilgileri Listele|");
                            System.out.println("| 2.Üye ol\t\t   |");
                            System.out.println("| 3.Anasayfa\t\t   |");
                            System.out.println("----------------------------");
                            switch (scanner.next()) {
                                case "1":
                                    dBConnection.getData("kitap", "kitap_adi");
                                    break;
                                case "2":
                                    System.out.println("Üye ol bölümüne geçildi");
                                    üyeOlmadanGelenSayac = 1;
                                    girisCikisSayaci = 1;
                                    break;
                                case "3":
                                    System.out.println("Anasayfa");
                                    girisCikisSayaci = 1;
                                    break;
                                default:
                                    System.out.println("Geçerli bir seçim yapınız.");
                                    break;

                            }
                        }
                        break;
                    default:
                        System.out.println("Seçim aralıkta bulunamadı! Geçerli bir seçim yapınız!");
                        continue;
                }
            } catch (Exception e) {
                System.out.println("Seçim aralıkta bulunamadı! Geçerli bir seçim yapınız!");
                continue;
            }

        }

    }

}
