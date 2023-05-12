/* Nama     : Sabrina Atha Shania
 * NPM      : 2206829591
 * Kelas    : DDP-B
 */

// Meimport library.
package assignments.assignment3.user;
import assignments.assignment3.nota.Nota;

// Class khusus untuk program member.
public class Member {
    // Nilai awal variabel.
    protected String id;
    protected String password;
    protected String nama;
    protected Nota[] notaList = new Nota[0];

    // Constructor dengan argumen.
    public Member(String nama, String id, String password) {
        this.nama = nama;
        this.id = id;
        this.password = password;
    }

    // Method otentikasi member dengan ID dan password yang diberikan.
    public boolean login(String id, String password) {
        return id.equals(this.id) && authenticate(password);
    }

    // Menambahkan nota baru ke NotaList instance member.
    public void addNota(Nota nota) {
        Nota[] notaLama = getNotaList();
        Nota[] newNotaList = new Nota[notaLama.length + 1];
        for (int i = 0; i < notaLama.length; i++) {
            newNotaList[i] = notaLama[i];
          }
          notaLama = newNotaList;
    
          notaLama[newNotaList.length - 1] = nota;
          setNotaList(notaLama);
    }

    // Method otentikasi member dengan password yang diberikan.
    protected boolean authenticate(String password) {
        if (password.equals(this.password)) {
            return true;
        }
        return false;
    }

    // Dibawah ini adalah getter
    public String getNama() {
        return nama;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public Nota[] getNotaList() {
        return notaList;
    }

    // Dibawah ini adalah setter.
    public void setId(String id) {
        this.id = id;
    }
    public void setNotaList(Nota[] notaList) {
        this.notaList = notaList;
    }
}