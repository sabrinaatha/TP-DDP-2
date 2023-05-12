/* Nama     : Sabrina Atha Shania
 * NPM      : 2206829591
 * Kelas    : DDP-B
 */

// Meimport library.
package assignments.assignment3.nota;
import java.text.SimpleDateFormat;
import java.util.Calendar;

// Class untuk seluruh nota.
public class NotaManager {

    // Nilai awal variable.
    public static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    public static Calendar cal = Calendar.getInstance();
    static public Nota[] notaList = new Nota[0];

    // Skips ke hari berikutnya dan update semua entri nota yang sesuai.
    public static void toNextDay(){
        // Mengganti hari melalui method dalam library
        for (int i = 0; i < notaList.length; i++) {
            notaList[i].toNextDay();
        }        
        tanggalBesok();
    }

    // Menambahkan nota baru ke NotaList.
    public static void addNota(Nota nota){
        Nota[] newNotaList = new Nota[notaList.length + 1];
        for (int i = 0; i < notaList.length; i++) {
            newNotaList[i] = notaList[i];
            newNotaList[i].setId(i);
          }
          notaList = newNotaList;
    
          newNotaList[notaList.length - 1] = nota;
          newNotaList[notaList.length - 1].setId(notaList.length-1);
    }

    // Set waktu selesai berdasarkan paket yang diambil.
    public static String generateWaktu(String id, String paket, int berat, String tanggalMasuk) {
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
        int year = Integer.parseInt(tanggalMasuk.substring(6));
        int month = Integer.parseInt(tanggalMasuk.substring(3, 5)) - 1;
        int date = Integer.parseInt(tanggalMasuk.substring(0, 2));
        cal.set(year, month, date);
        cal.add(Calendar.DATE, waktu);
        return formatter.format(cal.getTime());
    }

    // Set waktu hari ini.
    public static String tanggalMasuk(){
        return fmt.format(cal.getTime());
    }

    // Set waktu besok.
    public static String tanggalBesok(){
        cal.add(Calendar.DATE, 1);
        return fmt.format(cal.getTime());
    }
}
