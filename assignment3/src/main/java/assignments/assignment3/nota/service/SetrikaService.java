/* Nama     : Sabrina Atha Shania
 * NPM      : 2206829591
 * Kelas    : DDP-B
 */

// Meimport library.
package assignments.assignment3.nota.service;

// Class setrika service yang mengimplementasikan laundry service.
public class SetrikaService implements LaundryService{

    // Nilai awal variabel.
    public static int setrika;
    private boolean isDone;

    // Melakukan kerjaan menyetrika.
    @Override
    public String doWork() {
        setrika += 1; 
        return " : Sedang menyetrika...";
    }

    // Mengambil nilai is done.
    @Override
    public boolean isDone() {
        return isDone;
    }

    // Mengambil harga dari setrika service.
    @Override
    public long getHarga(int berat) {
        long harga = 1000;
        long hargaTotal = harga*berat;
        return hargaTotal;
    }

    // Mengambil nama dari setrika service.
    @Override
    public String getServiceName() {
        return "Setrika";
    }
    
    // Mengubah boolean isdone menjadi true yang menandakan telah selesai.
    public void setSetrika(boolean setrika) {
        this.isDone = setrika;
    }
}
