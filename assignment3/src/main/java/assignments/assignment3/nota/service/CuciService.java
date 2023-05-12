/* Nama     : Sabrina Atha Shania
 * NPM      : 2206829591
 * Kelas    : DDP-B
 */

// Meimport library.
package assignments.assignment3.nota.service;

// Class cuci service yang mengimplementasikan laundry service.
public class CuciService implements LaundryService{
    
    // Nilai awal variabel.
    private boolean isDone;

    // Melakukan kerjaan mencuci.
    @Override
    public String doWork() {
        return " : Sedang mencuci...";
    }

    // Mengambil nilai is done.
    @Override
    public boolean isDone() {
        return isDone;
    }

    // Mengambil harga dari cuci service.
    @Override
    public long getHarga(int berat) {
        return 0;
    }

    // Mengambil nama dari cuci service.
    @Override
    public String getServiceName() {
        return "Cuci";
    }

    // Mengubah boolean isdone menjadi true yang menandakan telah selesai.
    public void setCuci(boolean cuci) {
        this.isDone = cuci;
    }
}
