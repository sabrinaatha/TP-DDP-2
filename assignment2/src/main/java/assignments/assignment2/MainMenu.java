/* Nama     : Sabrina Atha Shania
 * NPM      : 2206829591
 * Kelas    : DDP-B
 */

// Meimport library
package assignments.assignment2;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import assignments.assignment1.NotaGenerator;
import static assignments.assignment1.NotaGenerator.*;

public class MainMenu {
    // Inisiasi list
    private static final Scanner input = new Scanner(System.in);
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private static Calendar cal = Calendar.getInstance();
    private static Nota[] notaList;
    private static Member[] memberList;
    private static String[] namaList;
    private static int idNota;
    private static String[] listIdNota;

    // Method utama
    public static void main(String[] args) {
        // inisiasi jumlah panjang list dan nilai awal
        memberList = new Member [1000];
        namaList = new String[1000];
        notaList = new Nota [1000];
        listIdNota = new String [1000];
        boolean isRunning = true;

        // Pengulangan untuk main menu
        while (isRunning) {
            printMenu();
            System.out.print("Pilihan : ");
            String command = input.next();
            System.out.println("================================");
            switch (command){
                case "1" -> handleGenerateUser();
                case "2" -> handleGenerateNota();
                case "3" -> handleListNota();
                case "4" -> handleListUser();
                case "5" -> handleAmbilCucian();
                case "6" -> handleNextDay();
                case "0" -> isRunning = false;
                default -> System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("Terima kasih telah menggunakan NotaGenerator!");
    }

    // Method untuk membuat ID member 
    private static void handleGenerateUser() {
        // Nilai awal lokal
        String nama = "", nomorHP = "";
        
        //Mencetak nama dan nomor HP
        System.out.println("Masukan nama Anda:");
        input.nextLine();
        nama = input.nextLine();
        String namaUpperCase = nama.toUpperCase();
        System.out.println("Masukan nomor handphone Anda:");
        nomorHP = input.nextLine();
        // Validasi input nomor HP
        while ((nomorHP.equals("")) || (!isNumeric(nomorHP))) {
            System.out.println("Field Nomor hp hanya menerima digit.");
            nomorHP = input.nextLine();
        }

        // Membuat variabel kelas member
        Member idMember = new Member(namaUpperCase, nomorHP);

        // Pengulangan hingga sepanjang memberlist 
        for (int a = 0; a < memberList.length;a++) {
            // Kondisi ketika nilainya null
            if (memberList[a] == null) {
                memberList[a] = idMember;
                namaList[a] = nama;
                String id = idMember.generate();
                System.out.println(memberList.toString());
                System.out.println("Berhasil membuat member dengan ID " + id + "!");
                break;
            // Kondisi ketika nilainya tidak null
            } else if (memberList[a] != null) {
                // Kondisi ketika nilainya sama dengan memberList indeks ke-a
                if (idMember.equals(memberList[a])) {
                    System.out.println("Member dengan nama " + nama + " dan nomor hp " + nomorHP + " sudah ada!");
                    break;
                // Kondisi ketika nilainya berbeda dengan memberList indeks ke-a
                } else {
                    continue;
                }
            } 
        }
    }

    // Method untuk menjalankan nota
    private static void handleGenerateNota() {
        // Nilai awal lokal
        String inputId = "", paket = "";
        boolean valuePaket = true;
        int nilaiValidasiMember = 0, berat = 0,listNotaKosong = 0;

        // Meminta input masukan
        System.out.println("Masukan ID member: ");
        input.nextLine();
        inputId = input.nextLine();

        // Pengulangan untuk menemukan id
        for (int b = 0; b < memberList.length;b++) {
            // Kondisi ketika member list tidak null
            if (memberList[b] != null) {  
                // Kondisi ketika isi member list tidak sama dengan inputid
                if (!inputId.equals(memberList[b].toString())) {
                    nilaiValidasiMember += 1;
                    continue;
                    // Kondisi ketika isi member list tidak sama dengan inputid
                } else { 
                    break;
                }
            // Kondisi ketika member list sama dengan null
            } else {
                nilaiValidasiMember += 1;
                continue;
            }
        }

        // Pengulangan untuk memasukkan value ke dalam nota list
        for (int z = 0; z < notaList.length; z++) {
            // Kondisi ketika semua member list berbeda dengan input id
            if (nilaiValidasiMember == memberList.length) { 
                System.out.println("Member dengan ID " + inputId + " tidak ditemukan!");
                break;
            // Kondisi ketika nota list ke z tidak kosong
            } else if (notaList[z] != null) {
                continue;
            } else {
                listNotaKosong = z;
                Member member = cariMember(inputId);
                String tanggal = fmt.format(cal.getTime());

                // Meminta input paket 
                System.out.print("Masukkan paket laundry: \n");
                paket = input.nextLine();

                // Validasi input paket
                while (valuePaket) {
                    // Menjalankan program berdasarkan nilai input paket
                    switch (paket.toLowerCase()) {
                        // Statement yang dijalankan apabila paket sama dengan express
                        case "express" :
                            valuePaket = false;
                            break; 
                        // Statement yang dijalankan apabila paket sama dengan fast
                        case "fast" :
                            valuePaket = false;
                            break;
                        // Statement yang dijalankan apabila paket sama dengan reguler    
                        case "reguler" :
                            valuePaket = false;
                            break;
                        // Statement yang dijalankan apabila paket sama dengan ?
                        case "?" :
                            NotaGenerator.showPaket();
                            System.out.print("Masukkan paket laundry: \n");
                            paket = input.nextLine();
                            break;
                        // Statement yang dijalankan apabila paket selain case diatas    
                        default :
                            System.out.println("Paket " + paket + " tidak diketahui");
                            System.out.println("[ketik ? untuk mencari tahu jenis paket]");
                            System.out.print("Masukkan paket laundry: \n");
                            paket = input.nextLine();
                            break;
                    }
                }

                // Meminta input berat cucian
                System.out.println("Masukkan berat cucian Anda [Kg]: ");
                String beratInput = input.nextLine();

                // Validasi input berat cucian
                while (!isNumeric(beratInput) || Integer.parseInt(beratInput) < 1) {
                    System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                    beratInput = input.nextLine();
                }
                berat = Integer.parseInt(beratInput);
                if (berat < 2) {
                    System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                    berat = 2;

                }

                // Inisiasi nota ke dalam list nota
                Nota nota = new Nota(member, paket, berat, tanggal);
                notaList[listNotaKosong] = nota;
                member.setbonusCounter(member.getBonusCounter()+1);
                String hasil = nota.generate(member, paket, berat, tanggal, listNotaKosong);
                notaList[listNotaKosong].setIdNota(idNota);

                // Pengulangan untuk memasukkan nilai id nota
                for (int g = 0; g<listIdNota.length;g++) {
                    if (listIdNota[g] == null) {
                        listIdNota[g] = String.valueOf(nota.getidNota());
                        break;
                    }
                    else {
                        continue;
                    }
                }
                idNota += 1;

                // Mencetak hasil akhir dari membuat nota 
                System.out.println("Berhasil menambahkan nota!");
                System.out.println("[ID Nota = " + (nota.getidNota()) + "]");
                System.out.println(hasil);
                System.out.println("Status \t\t: Belum bisa diambil :(");
                break;
            }
        }
    }

    // Pengecekan validasi angka
    private static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }

    // Method untuk mencari Member berdasarkan id
    private static Member cariMember(String id){
        for(Member member: memberList){
            if(member.getId().equals(id))
                return member;
        }
        return null;
    }

    // Method untuk mencetak seluruh list nota
    private static void handleListNota() {
        // Nilai awal lokal
        String hasilAkhir = "";
        int totalNota = 0;
        boolean nilai = true;

        // Pengulangan untuk mengetahui jumlah array yang tidak kosong
        for (int b = 0; b < notaList.length; b++) {
            if (notaList[b] != null) {
                totalNota += 1;
            } else {
                continue;
            }
        }
        // Mencetak total nota
        System.out.println("Terdaftar " + totalNota + " nota dalam sistem.");
        
        // Pengulangan untuk mencetak hasil akhir 
        while (nilai) {
            // Kondisi ketika total nota sama dengan nol
            if (totalNota == 0) {
                nilai = false;
            // Kondisi ketika total nota tidak sama dengan nol
            } else {
                // Pengulangan untuk mengambil satu angka di list id nota
                for (int f = 0; f < listIdNota.length; f++) {
                    if (listIdNota[f] != null) {
                        // Pengulangan untuk cek apakah get id nota dalam nota list ada dan sama dengan angka di list id nota
                        for (int g = 0; g<notaList.length; g++) {
                            if (notaList[g] != null) {
                                int value = Integer.parseInt(listIdNota[f]);
                                // apabila kedua angka sama akan mengeluarkan kondisi seperti ini
                                if ( value == notaList[g].getidNota()) {
                                    if (notaList[g].getisReady()) {
                                        hasilAkhir = "Sudah dapat diambil!";
                                    } else {
                                        hasilAkhir = "Belum bisa diambil :(";
                                    }
                                    System.out.println("- [" + listIdNota[f] + "] Status\t\t: " + hasilAkhir);
                                    break;
                                }
                            } else {
                                continue;
                            }
                        }
                    } else {
                        continue;
                    }
                }
                nilai = false;
            }
        }
    }

    // Method untuk mencetak seluruh list member
    private static void handleListUser() {
        // Nilai awal lokal
        int totalMember = 0;
        boolean nilai = true;

        // Pengulangan untuk mengetahui jumlah array yang tidak kosong
        for (int b = 0; b < memberList.length; b++) {
            if (memberList[b] != null) {
                totalMember += 1;
            } else {
                continue;
            }
        }
        // Mencetak total member
        System.out.println("Terdaftar " + totalMember + " member dalam sistem.");

        // Pengulangan untuk mencetak hasil akhir 
        while (nilai) {
            // Kondisi ketika total nota sama dengan nol
            if (totalMember == 0) {
                nilai = false;
            // Kondisi ketika total nota tidak sama dengan nol
            } else {
                for (int c = 0; c < memberList.length; c++) {
                    if (memberList[c] != null) {
                        System.out.println("- " + memberList[c].toString() + " : " + namaList[c]);
                    } else {
                        continue;
                    }
                }
                nilai = false;
            }
        }
    }

    // Method untuk ambil cucian 
    private static void handleAmbilCucian() {
        // Nilai awal
        int idNota = 0, nilaiValidasiCucian = 0;

        // Meminta input id nota yang akan diambil
        System.out.println("Masukan ID nota yang akan diambil: ");
        input.nextLine();
        String idNotaString = input.nextLine();
        
        // Validasi nilai id nota input
        for (int f = 0; f < idNotaString.length(); f++) {   
            if (!Character.isDigit(idNotaString.charAt(f))){
                while (!Character.isDigit(idNotaString.charAt(f))) {
                    System.out.println("ID nota berbentuk angka!");
                    idNotaString = input.nextLine();
                    f = 0;
                }    
            } else if (idNotaString.charAt(f) < 0){
                System.out.println("Harap masukkan Id Nota Anda dalam bentuk bilangan positif.");
                idNotaString = input.nextLine();
                f = 0;
            }   
            idNota = Integer.parseInt(idNotaString);
        }

        // Pengulangan untuk validasi nomor id
        for (int g = 0; g < notaList.length; g++) {
            // Kondisi ketika nota list kosong
            if ((notaList[g] == null)){
                nilaiValidasiCucian += 1;
                continue;
            } else  {
                // Kondisi ketika id nota dalam nota list sama dengan id nota input
                if (notaList[g].getidNota() == idNota) {
                    if (notaList[g].getisReady()) {
                        notaList[g] = null;
                        System.out.println("Nota dengan ID " + idNotaString + " berhasil diambil!");
                        break;
                    } else {
                        System.out.println("Nota dengan ID " + idNotaString + " gagal diambil!");
                        break;
                    }
                } else {
                    nilaiValidasiCucian += 1;
                }
            }
        }
        // Kondisi ketika semua id nota list tidak sesuai dengan input id
        if (nilaiValidasiCucian == notaList.length) {
            System.out.println("Nota dengan ID " + idNotaString + " tidak ditemukan!");
        }
    }

    // Method untuk mengganti hari 
    private static void handleNextDay() {
        // Mengganti hari melalui method dalam library
        System.out.println("Dek Depe tidur hari ini... zzz...");
        cal.add(Calendar.DATE, 1);
        // Pengulangan untuk mencari jadwal laundry yang sudah selesai
        for (int f = 0; f < listIdNota.length; f++) {
            if (listIdNota[f] != null) {
                // Pengulangan untuk cek apakah get id nota dalam nota list ada dan sama dengan angka di list id nota
                for (int c = 0; c < notaList.length; c++) {
                    if (notaList[c] != null) {
                        int value = Integer.parseInt(listIdNota[f]);
                        // Kumpulan statement yang dijalankan apabila id nota dari kedua array sama 
                        if ( value == notaList[c].getidNota()) {
                            notaList[c].setReady();
                            if (notaList[c].getisReady()) {
                                System.out.println("Laundry dengan nota ID " + listIdNota[f] + " sudah dapat diambil!");
                            } else {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        // Mencetak kalimat akhir dalam next day
        System.out.println("Selamat pagi dunia!");
        System.out.println("Dek Depe: It's CuciCuci Time.");
    }

    // Method untuk mencetak menu 
    private static void printMenu() {
        System.out.println("\nSelamat datang di CuciCuci!");
        System.out.printf("Sekarang Tanggal: %s\n", fmt.format(cal.getTime()));
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate User");
        System.out.println("[2] Generate Nota");
        System.out.println("[3] List Nota");
        System.out.println("[4] List User");
        System.out.println("[5] Ambil Cucian");
        System.out.println("[6] Next Day");
        System.out.println("[0] Exit");
    }

}
