/* Nama     : Sabrina Atha Shania
 * NPM      : 2206829591
 * Kelas    : DDP-B
 */

// Meimport library.
package assignments.assignment3.nota.service;

// Class antar service yang mengimplementasikan laundry service.
public class AntarService implements LaundryService{

    // Nilai awal variabel.
    public static int antar;
    private boolean isDone;

    // Melakukan kerjaan mengantar.
    @Override
    public String doWork() {
        return " : Sedang mengantar...";
    }

    // Mengambil nilai is done.
    @Override
    public boolean isDone() {
        return isDone;
    }

    // Mengambil harga dari antar service.
    @Override
    public long getHarga(int berat) {
        int hargaSatuan = 500;
        int totalHargaAntar = hargaSatuan*berat;
        if (totalHargaAntar < 2000) {
            return 2000;
        }
        return totalHargaAntar;
    }

    // Mengambil nama dari antar service.
    @Override
    public String getServiceName() {
        return "Antar";
    }

    // Mengubah boolean isdone menjadi true yang menandakan telah selesai.
    public void setAntar(boolean antar) {
        this.isDone = antar;
    }
}
