/*
Nama    : Sabrina Atha Shania
NPM     : 2206829591
Kelas   : DDP - B
*/

import java.util.Scanner;
import java.util.Timer;

public class TP01 {
    public static void main(String[] args) {
        //Nilai Awal
        boolean value = true;

        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Selamat datang di NotaGenerator! ");
            System.out.println("==============Menu==============");
            System.out.println("[1] Generate ID ");
            System.out.println("[2] Generate Nota ");
            System.out.println("[0] Exit ");
            System.out.print("Pilihan: ");
            int pilihan = input.nextInt();
            
            switch (pilihan) {
                case 1 :
                    System.out.print("Masukkan nama Anda: \n");
                    String nama = input.next().toUpperCase();
                    String namaAkhir = Penamaan(nama);
                    System.out.println(namaAkhir);
                    System.out.print("Masukkan nomor handphone Anda: \n");
                    int nomor = input.nextInt();
                    System.out.println("ID Anda : " + nama + "-" + nomor + "-");
                    break;
                case 2 :
                    continue;
                case 0 :
                    System.exit(0);
                    break;
                default :
                    System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
                    break;
            }
        }
    }
    public static String Penamaan(String nama) {
        int i = 1;
        for (i = 1;i < nama.length();i++) {
            String namaUji = String.valueOf(nama.charAt(i));
            if (namaUji.equals(" ")) {
                break;
            }
        }
        return nama.substring(0, i);
    }
}
