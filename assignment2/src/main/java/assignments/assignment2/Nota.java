/* Nama     : Sabrina Atha Shania
 * NPM      : 2206829591
 * Kelas    : DDP-B
 */

// Import library
package assignments.assignment2;
import assignments.assignment1.NotaGenerator;

public class Nota {
    // Data field yang dibutuhkan dalam kelas Nota
    private int idNota;
    private String paket;
    private Member member;
    private int berat;
    private String tanggalMasuk;
    private int sisaHariPengerjaan;
    private boolean isReady;

    // Constructor nota
    public Nota(Member member, String paket, int berat, String tanggalMasuk) {
        this.member = member;
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk = tanggalMasuk;
    }

    // Merubah nilai id nota
    public void setIdNota(int idNota) {
        this.idNota = idNota;
    }
    // Merubah nilai paket
    public void setPaket(String paket) {
        this.paket = paket;
    }
    // Merubah nilai member
    public void setMember(Member member) {
        this.member = member;
    }
    // Merubah nilai berat
    public void setBerat(int berat) {
        this.berat = berat;
    }
    // Merubah nilai tanggal masuk
    public void setTanggalMasuk(String tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }
    // Merubah nilai sisa hari
    public void setSisaHariPengerjaan(int sisaHariPengerjaan) {
        this.sisaHariPengerjaan = sisaHariPengerjaan;
    }
    // Merubah nilai true/false ready
    public void setIsReady(boolean isReady) {
        this.isReady = isReady;
    }
    // Melihat kondisi untuk mengetahui nilai is ready
    public void setReady() {
        if (getisReady() == false) {
            int sisaHariAwal = this.getSisaHariPengerjaan();
            this.setSisaHariPengerjaan(sisaHariAwal-1);
            int sisaHariAkhir = sisaHariAwal-1;
            if (sisaHariAkhir == 0) {
                setIsReady(true);
            } else {
                setIsReady(false);
            }
        } else {
            setIsReady(isReady);
        }
    }
    // Mengambil nilai id nota
    public int getidNota() {
        return this.idNota;
    }
    // Mengambil nilai paket
    public String getPaket() {
        return this.paket;
    }
    // Mengambil nilai member
    public Member getMember() {
        return this.member;
    }
    // Mengambil nilai berat
    public int getBerat() {
        return this.berat;
    }
    // Mengambil nilai tanggal masuk
    public String getTanggalMasuk() {
        return this.tanggalMasuk;
    }
    // Mengambil nilai sisa hari
    public int getSisaHariPengerjaan() {
        return this.sisaHariPengerjaan;
    }
    // Mengambil nilai true/false is ready
    public boolean getisReady() {
        return this.isReady;
    }
    // Mengambil nilai nota
    public String generate(Member member, String paket, int berat, String tanggalMasuk, int idNota) {
        // Nilai awal lokal
        String hasil = "";
        String tanggal = tanggalMasuk;
        setTanggalMasuk(tanggal);
        String id = member.getId();

        // Menemukan tanggal terima dan harga paket
	    String tanggalTerima = NotaGenerator.generateWaktu(id, paket, berat, tanggalMasuk);
        long hargaPaket = NotaGenerator.toHargaPaket(paket);

        // Menghitung harga keseluruhan 
        long hargaTotal = berat*hargaPaket;
        int bonus = member.getBonusCounter();

        // Kondisi ketika bonus kelipatan 3
        if (bonus%3 == 0) {
            long hargaSetelahDiskon = hargaTotal/2;
            member.setbonusCounter(0);
            String perkalianHarga = berat + " kg x " + hargaPaket + " = " + hargaTotal + " = " + hargaSetelahDiskon + " (Discount member 50%!!!)";
            hasil =  "ID    : " + id + "\n" + "Paket : " + paket + "\n" + "Harga :\n" + perkalianHarga + "\n" + "Tanggal Terima  : " 
            + tanggalMasuk + "\n" + "Tanggal Selesai : " + tanggalTerima;
        } else {
            // Mencetak hasil akhir dari generateNota
            String perkalianHarga = berat + " kg x " + hargaPaket + " = " + hargaTotal;
            hasil =  "ID    : " + id + "\n" + "Paket : " + paket + "\n" + "Harga :\n" + perkalianHarga + "\n" + "Tanggal Terima  : " 
            + tanggalMasuk+ "\n" + "Tanggal Selesai : " + tanggalTerima;
        }
        setStatus(paket);
        return hasil;
	}

    // Merubah nilai sisa hari
    public int setStatus(String paket) {
        int waktu = 0;
        //Untuk paket sama dengan express
        if (paket.toLowerCase().equals("express")) {   
            waktu = 1;
            setSisaHariPengerjaan(waktu);
        // Untuk paket sama dengan fast    
        } else if (paket.toLowerCase().equals("fast")) {  
            waktu = 2;
            setSisaHariPengerjaan(waktu);
        
        // Untuk paket sama dengan reguler    
        } else if (paket.toLowerCase().equals("reguler")) {
            waktu = 3;
            setSisaHariPengerjaan(waktu);
        } 
        return waktu;
    }
    // Membandingkan id nota yang satu dengan yang lain
    public boolean equals(Nota other) {
        return this.idNota == other.idNota;
    }
}
