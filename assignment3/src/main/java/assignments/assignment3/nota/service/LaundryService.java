/* Nama     : Sabrina Atha Shania
 * NPM      : 2206829591
 * Kelas    : DDP-B
 */

// Meimport library.
package assignments.assignment3.nota.service;

public interface LaundryService {
    /* Akan mengembalikan String yang menandakan bahwa Nota tersebut sedang dikerjakan.
    Jika pernah dipanggil minimal sekali akan membuat method isDone mengembalikan true.
     */
    String doWork();

    // Akan bernilai true ketika method doWork() pernah dipanggil minimal sekali, selain itu akan bernilai false.
    boolean isDone();

    // Akan mengkalkulasi harga berdasarkan berat dari argumen yang masuk.
    long getHarga(int berat);

    // Akan mengembalikan nama dari service.
    String getServiceName();

}