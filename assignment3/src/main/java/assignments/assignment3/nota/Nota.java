/* Nama     : Sabrina Atha Shania
 * NPM      : 2206829591
 * Kelas    : DDP-B
 */

// Meimport library.
package assignments.assignment3.nota;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;

// Class untuk membuat nota.
public class Nota {

    // Nilai awal variabel.
    private Member member;
    private String paket;
    private LaundryService[] services = new LaundryService[0];
    private long baseHarga;
    private int sisaHariPengerjaan;
    private  int berat;
    private int id;
    private int kompensasi;
    private String hasilKerjakan;
    private String tanggalMasuk;
    private boolean isDone;
    private int noService;
    static public int totalNota;

    // Constructor dengan argumen.
    public Nota(Member member, int berat, String paket, String tanggal) {
        this.member = member;
        this.berat = berat;
        this.paket = paket;
        tanggalMasuk = tanggal;
        setStatus(paket);
    }

    // Menambahkan service ke dalam array laundry service.
    public void addService(LaundryService service){
        LaundryService[] serviceListAwal = getServices();

        // Kondisi apabila service tidak termasuk cuci service dan panjang array sama dengan nol.
        if ((serviceListAwal.length == 0) && (!(service instanceof CuciService))){
            LaundryService[] newServiceList = new LaundryService[serviceListAwal.length + 1];
            serviceListAwal = newServiceList;
    
            serviceListAwal[0] = new CuciService();
            setServiceList(serviceListAwal);
        }

        // Menambahkan list array services.
        LaundryService[] serviceList = getServices();
        LaundryService[] newServiceList = new LaundryService[serviceList.length + 1];
        for (int i = 0; i < serviceList.length; i++) {
            newServiceList[i] = serviceList[i];
          }
          serviceList = newServiceList;
    
          serviceList[newServiceList.length - 1] = service;
          setServiceList(serviceList);
    }

    // Mengerjakan laundry.
    public String kerjakan(){  
        // Pengecekan panjang array services.
        int panjangList = getServices().length;
        int noService = getNoService();
        LaundryService[] serviceList = getServices();

        // Kondisi apabila panjang services sama dengan nol.
        if ((panjangList == 0)) {
            LaundryService cucian = new CuciService();
            addService(cucian);
            ((CuciService)cucian).setCuci(true);
            setIsDone();
            return "Nota " + id() + " : Sedang mencuci...";
        }

        // Pengulangan untuk mengecek tiap service.
        for(int e = noService; e < serviceList.length;e++){
            // Kondisi apabila service belum selesai dan termasuk kelas cuci service.
            if ((!serviceList[e].isDone()) && (serviceList[e] instanceof CuciService)) {
                noService += 1;
                setNoService(noService);
                if (noService == serviceList.length) {
                    setIsDone();
                }
                ((CuciService) serviceList[e]).setCuci(true);
                return "Nota " + id() + ((CuciService) serviceList[e]).doWork();

            // Kondisi apabila service belum selesai dan termasuk kelas antar service.
            } else if ((!serviceList[e].isDone()) && (serviceList[e] instanceof AntarService)) {
                noService += 1;
                setNoService(noService);
                if (noService == serviceList.length) {
                    setIsDone();
                }
                ((AntarService) serviceList[e]).setAntar(true);
                return "Nota " + id() + ((AntarService) serviceList[e]).doWork();

            // Kondisi apabila service belum selesai dan termasuk kelas setrika service.
            } else if ((!serviceList[e].isDone()) && (serviceList[e] instanceof SetrikaService)) {
                noService += 1;
                setNoService(noService);
                if (noService == serviceList.length) {
                    setIsDone();
                }
                ((SetrikaService) serviceList[e]).setSetrika(true);
                return "Nota " + id() + ((SetrikaService) serviceList[e]).doWork();
            } 
        }
        
        // Set service jika semua nya sudah selesai.
        if (noService == serviceList.length) {
            setIsDone();
        }
        return getNotaStatus();
    }
    
    // Melanjutkan ke hari selanjutnya.
    public void toNextDay() {
        // Kondisi apabila belum selesai.
        if (!isDone()) {
            int status = getSisaHariPengerjaan();
            status = status - 1;
            if (status < 0) {
                int kompensasi = getKompensasi();
                kompensasi += 1;
                setKompensasi(kompensasi);
                setSisaHariPengerjaan(-1);
            
            // Kondisi apabila sudah selesai.
            } else {
                setSisaHariPengerjaan(status);
            }
        }

    }

    // Menghitung harga dasar.
    public long calculateHargaAja(){
        long hargaPaket = getHargaPaket(paket);
        long hargaTotal = berat*hargaPaket;
        return hargaTotal;
    }

    // Menghitung total keseluruhan harga.
    public long calculateHarga(){
        LaundryService[] serviceList = getServices();
        LaundryService antar = null;
        LaundryService setrika = null;

        // Mengecek apakah termasuk antar service atau setrika service.
        for (int u = 0; u < getServices().length; u++) {
            if (serviceList[u] instanceof AntarService) {
                antar = serviceList[u];
            } else if (serviceList[u] instanceof SetrikaService) {
                setrika = serviceList[u];
            }
        }
        long hargaTotalAkhir = (calculateHargaAja() + calculateAntar(antar) + calculateSetrika(setrika) - calculateTelat());
        // Kondisi apabila harga total akhir adalah nol.
        if (hargaTotalAkhir < 0) {
            return 0;
        }
        return hargaTotalAkhir;
    }

    // Mengembalikan status nota.
    public String getNotaStatus(){
        if(isDone()) {
            return "Nota " + id() + " : Sudah selesai.";
        }
        return "Nota " + id() + " : Belum selesai.";
    }

    // Mencetak hasil akhir.
    @Override
    public String toString(){
        String hasil = "";
        hasil += "[ID Nota = " + id() + "]";
        String idMember = member.getId();
        String tanggalTerima = NotaManager.generateWaktu(idMember, paket, berat, tanggalMasuk);
        String perkalianHarga = berat + " kg x " + getHargaPaket(paket) + " = " + calculateHargaAja(); 
        hasil += "\nID    : " + idMember + "\n" + "Paket : " + getPaket() + "\n" + "Harga :\n" + perkalianHarga + 
        "\n" + "tanggal terima : " + getTanggal() + "\ntanggal selesai : " + tanggalTerima + "\n--- SERVICE LIST ---" + 
        "\n-Cuci @ Rp.0";

        LaundryService[] serviceList = getServices();
        LaundryService antar = null;
        LaundryService setrika = null;

        // Pengecekan adanya service antar dan service setrika
        for (int u = 0; u < getServices().length; u++) {
            if (serviceList[u] instanceof AntarService) {
                antar = serviceList[u];
                hasil += "\n-Antar @ Rp." + calculateAntar(antar);
            } else if (serviceList[u] instanceof SetrikaService) {
                setrika = serviceList[u];
                hasil += "\n-Setrika @ Rp." + calculateSetrika(setrika);
            }
        }

        hasil += "\nHarga Akhir: " + calculateHarga();

        // Kondisi apabila saat hari selesai, nota belum selesai.
        if (calculateTelat() != 0){
            hasil += " Ada kompensasi keterlambatan " + (getKompensasi()) + " * 2000 hari";
        } 

        return hasil + "\n";
    }

    // Mengkalkulasi total harga apabila terdapat keterlambatan.
    public long calculateTelat() {
        int status = getSisaHariPengerjaan();
        // Kondisi apabila nota sudah selesai dan status masih diatas nol.
        if ((isDone()) && (status > 0)) {
            return 0;
        } 
        int hariTelat = getKompensasi();
        int total = hariTelat*2000;
        return total;
    }

    // Mengkalkulasi total service antar.
    public long calculateAntar(LaundryService serviceList){
        if (serviceList == null) {
            return 0;
        }
        int berat = getBerat();
        long hargaTotal = serviceList.getHarga(berat);
        return hargaTotal;
    }

    // Mengkalkulasi total service setrika.
    public long calculateSetrika(LaundryService serviceList) {
        if (serviceList == null) {
            return 0;
        }
        int berat = getBerat();
        long hargaTotal = serviceList.getHarga(berat);
        return hargaTotal;
    }

    // Dibawah ini adalah getter
    public String getPaket() {
        return this.paket;
    }

    public int getNoService() {
        return this.noService;
    }

    public int getBerat() {
        return this.berat;
    }

    public String getHasilKerjakan() {
        return hasilKerjakan;
    }
    
    public String getTanggal() {
        return tanggalMasuk;
    }

    public int getSisaHariPengerjaan(){
        return sisaHariPengerjaan;
    }
    public boolean isDone() {
        return isDone;
    }

    public LaundryService[] getServices(){
        return services;
    }

    public int id() {
        return id;
    }

    public long getBaseHarga() {
        return baseHarga;
    }

    public int getKompensasi() {
        return kompensasi;
    }

    // Dibawah ini adalah setter
    public void setBerat(int berat) {
        this.berat = berat;
    }

    public void setHasilKerjakan(String hasilKerjakan) {
        this.hasilKerjakan = hasilKerjakan;
    }
    public void setNoService(int noService) {
        this.noService = noService;
    } 

    public void setSisaHariPengerjaan(int sisaHariPengerjaan) {
        this.sisaHariPengerjaan = sisaHariPengerjaan;
    }

    public void setIsDone() {
        this.isDone = true;
    }

    public void setKompensasi(int kompensasi) {
        this.kompensasi = kompensasi;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setServiceList(LaundryService[] services) {
        this.services = services;
    }

    // Mengetahui waktu tiap jenis paket.
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

    // Mengetahui harga tiap paket.
    public static long getHargaPaket(String paket) {
        String paketKirim = paket.toLowerCase();
        if (paketKirim.equals("express")) return 12000;
        if (paketKirim.equals("fast")) return 10000;
        if (paketKirim.equals("reguler")) return 7000;
        return -1;
    }
}
