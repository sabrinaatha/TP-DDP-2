
/* Nama     : Sabrina Atha Shania
 * NPM      : 2206829591
 * Kelas    : DDP-B
 */

package assignments.assignment1;

//Mengimport library
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NotaGenerator {
    //Inisiasi Scanner
    private static final Scanner input = new Scanner(System.in);
    
    // Method main
    public static void main(String[] args) {
        boolean valuePilihan = true;
        // Pengulangan menu pilihan
        while (valuePilihan) {
           
            //Nilai Awal
            boolean valuePaket = true, valueBeratCucian = true;
            int berat = 0;
            String nama = "", nomorHP = "", paket = "", inputPilihan = "", hasil = "", beratCucian = "";
           
            // Mencetak daftar menu dan meminta input pilihan
            printMenu();
            System.out.print("Pilihan: "); 
            inputPilihan = input.next();

            // Menjalankan program berdasarkan nilai inputPilihan
            switch (inputPilihan) {
                // Kumpulan statement yang dijalankan apabila pilihan sama dengan satu
                case "1" :    
                    // Meminta input nama dan nomor HP
                    System.out.print("Masukkan nama Anda:\n");
                    input.nextLine();
                    nama = input.nextLine().toUpperCase();
                    System.out.print("Masukkan nomor handphone Anda:\n");
                    nomorHP = input.next();
                    
                    // Validasi input nomorHP
                    for (int j = 0; j < nomorHP.length()-1; j++) {   
                        if ((nomorHP.length() == 1) && (nomorHP.charAt(j) == '0')) {
                            continue;
                        } else if (!Character.isDigit(nomorHP.charAt(j))){
                            while (!Character.isDigit(nomorHP.charAt(j))) {
                                System.out.println("Nomor HP hanya menerima digit");
                                nomorHP = input.next();
                                j = 0;
                            }    
                        } 
                    }
    
                    // Masuk ke method generateID dan mencetak hasil keseluruhan generateID
                    hasil = generateId(nama, nomorHP);
                    System.out.println("ID Anda : " + hasil);
                    break;
                
                // Kumpulan statement yang dijalankan apabila pilihan sama dengan dua
                case "2" :
                    // Mencetak menu dan meminta input nama serta nomor
                    showPaket();
                    System.out.print("Masukkan nama Anda: \n");
                    input.nextLine();
                    nama = input.nextLine().toUpperCase();
                    System.out.print("Masukkan nomor handphone Anda: \n");
                    nomorHP = input.next();
                    
                    // Validasi input nomorHP
                    for (int j = 0; j < nomorHP.length()-1; j++) {   
                        if ((nomorHP.length() == 1) && (nomorHP.charAt(j) == '0')) {
                            continue;
                        } else if (!Character.isDigit(nomorHP.charAt(j))){
                            while (!Character.isDigit(nomorHP.charAt(j))) {
                                System.out.println("Nomor HP hanya menerima digit");
                                nomorHP = input.next();
                                j = 0;
                            }    
                        } 
                    }
                    
                    // Menginisiasi nilai id melalui method generateId
                    String id = generateId(nama, nomorHP);

                    // Meminta input tanggal dan paket
                    System.out.print("Masukkan tanggal terima: \n");
                    String tanggalTerima = input.next();
                    System.out.print("Masukkan paket laundry: \n");
                    paket = input.nextLine();

                    // Validasi input paket
                    while (valuePaket) {
                        // Menjalankan program berdasarkan nilai input paket
                        switch (paket.toLowerCase()) {
                            // Kumpulan statement yang dijalankan apabila paket sama dengan express
                            case "express" :
                                valuePaket = false;
                                break; 
                            // Kumpulan statement yang dijalankan apabila paket sama dengan fast
                            case "fast" :
                                valuePaket = false;
                                break;
                            // Kumpulan statement yang dijalankan apabila paket sama dengan reguler    
                            case "reguler" :
                                valuePaket = false;
                                break;
                            // Kumpulan statement yang dijalankan apabila paket sama dengan ?
                            case "?" :
                                showPaket();
                                System.out.print("Masukkan paket laundry: \n");
                                paket = input.next();
                                break;
                            // Kumpulan statement yang dijalankan apabila paket selain case diatas    
                            default :
                                System.out.println("Paket " + paket + " tidak diketahui");
                                System.out.println("[ketik ? untuk mencari tahu jenis paket]");
                                System.out.print("Masukkan paket laundry: \n");
                                paket = input.nextLine();
                                break;
                        }
                    }
                    // Meminta input berat cucian
                    System.out.print("Masukkan berat cucian Anda [Kg]: \n");
                    beratCucian = input.next();

                    // Validasi input berat cucian
                    while (valueBeratCucian) {
                        try { 
                            Integer.parseInt(beratCucian); 
                            berat = Integer.valueOf(beratCucian);
                            if ((berat == 1) || (berat == 0)) {
                                berat = 2;
                                System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                            } else if (berat < 0) {     //Kondisi ketika berat kurang dari nol
                                System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif."); 
                                beratCucian = input.next();
                                Integer.parseInt(beratCucian); 
                                berat = Integer.valueOf(beratCucian);
                            }
                            break;
                        }  catch (Exception InputMismatchException)  {      //Kondisi ketika berat bukan bilangan
                            System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif."); 
                            beratCucian = input.next();
                        } 
                    }

                    // Masuk ke method generateNota dan mencetak hasil keseluruhan generateNota
                    hasil = generateNota(id, paket, berat, tanggalTerima);
                    System.out.println("Nota Laundry");
                    System.out.println(hasil);
                    break;
                
                // Kumpulan statement yang dijalankan apabila pilihan sama dengan nol
                case "0" :
                    System.out.println("Terima kasih telah menggunakan NotaGenerator!");
                    System.exit(0);
                    break;
                // Kumpulan statement yang dijalankan apabila pilihan selain case diatas   
                default :
                    System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
                    break;
            }        
        }
    }

    // Method untuk menampilkan menu di NotaGenerator.
    private static void printMenu() {
        System.out.println("Selamat datang di NotaGenerator!");
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate ID");
        System.out.println("[2] Generate Nota");
        System.out.println("[0] Exit");
    }

    // Method untuk menampilkan paket.
    public static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }

    // Method untuk membuat ID dari nama dan nomor handphone.
    public static String generateId(String nama, String nomorHP){
        String id = "";
        id += (nama.split(" ")[0] + "-").toUpperCase();
        id += nomorHP;

        int checksum = 0;
        for (char c : id.toCharArray()) {
            if (Character.isDigit(c))
                checksum += c - '0';
            else if (Character.isLetter(c))
                checksum += (c - 'A') + 1;
            else
                checksum += 7;
        }
        id += String.format("-%02d", checksum % 100);
        return id;
    }

    // Method untuk membuat Nota
    public static String generateNota(String id, String paket, int berat, String tanggalTerima){
        // Nilai lokal awal
        int hargaPaket = 0, waktu = 0;
        String hasil = "";

        //Menjalankan program berdasarkan nilai paket
        switch (paket.toLowerCase()) {
            //Kumpulan statement untuk paket sama dengan express
            case "express" :    
                hargaPaket = 12000;
                waktu = 1;
                break;
            //Kumpulan statement untuk paket sama dengan fast    
            case "fast" :   
                hargaPaket = 10000;
                waktu = 2;
                break;
            //Kumpulan statement untuk paket sama dengan reguler    
            case "reguler" :    
                hargaPaket = 7000;
                waktu = 3;
                break;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        int year = Integer.parseInt(tanggalTerima.substring(6));
        int month = Integer.parseInt(tanggalTerima.substring(3, 5)) - 1;
        int date = Integer.parseInt(tanggalTerima.substring(0, 2));
        cal.set(year, month, date);
        cal.add(Calendar.DATE, waktu);
        

        // Menghitung harga keseluruhan 
        int hargaTotal = berat*hargaPaket;

        // Mencetak hasil akhir dari generateNota
        String perkalianHarga = berat + " kg x " + hargaPaket + " = " + hargaTotal;
        hasil =  "ID    : " + id + "\n" + "Paket : " + paket + "\n" + "Harga :\n" + perkalianHarga + "\n" + "Tanggal Terima  : " 
            + tanggalTerima + "\n" + "Tanggal Selesai : " + formatter.format(cal.getTime());
        return hasil;
     }

     public static String generateWaktu(String id, String paket, int berat, String tanggalTerima) {
        // Nilai lokal awal
        int waktu = 0;

        //Menjalankan program berdasarkan nilai paket
        switch (paket.toLowerCase()) {
            //Kumpulan statement untuk paket sama dengan express
            case "express" :    
                waktu = 1;
                break;
            //Kumpulan statement untuk paket sama dengan fast    
            case "fast" :   
                waktu = 2;
                break;
            //Kumpulan statement untuk paket sama dengan reguler    
            case "reguler" :    
                waktu = 3;
                break;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        int year = Integer.parseInt(tanggalTerima.substring(6));
        int month = Integer.parseInt(tanggalTerima.substring(3, 5)) - 1;
        int date = Integer.parseInt(tanggalTerima.substring(0, 2));
        cal.set(year, month, date);
        cal.add(Calendar.DATE, waktu);
        return formatter.format(cal.getTime());
    }
    public static long getHargaPaket(String paket) {
        paket = paket.toLowerCase();
        if (paket.equals("express")) return 12000;
        if (paket.equals("fast")) return 10000;
        if (paket.equals("reguler")) return 7000;
        return -1;
    }
}
