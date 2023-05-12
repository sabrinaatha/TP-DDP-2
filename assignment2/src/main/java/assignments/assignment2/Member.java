/* Nama     : Sabrina Atha Shania
 * NPM      : 2206829591
 * Kelas    : DDP-B
 */

// Import library
package assignments.assignment2;
import assignments.assignment1.NotaGenerator;

public class Member {
    // Data field yang dibutuhkan dalam kelas Member
    private String nama;
    private String noHp;
    private String id;
    private int bonusCounter;

    // Constructor kelas member
    public Member(String nama, String noHp) {
        this.nama = nama;
        this.noHp = noHp;
    }
    // Mengganti nilai nama yang baru
    public void setNama(String nama) {
        this.nama = nama;
    }
    // Mengganti nilai no Hp yang baru
    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }
    // Mengganti nilai ID yang baru
    public void setId(String id) {
        this.id = id;
    }
    // Mengambil nilai nama 
    public String getNama() {
        return this.nama;
    }
    // Mengambil nilai no Hp
    public String getNoHp() {
        return this.noHp;
    }
    // Mengambil nilai id
    public String getId() {
        return this.id;
    }
    // Mengambil nilai bonus counter
    public int getBonusCounter() {
        return this.bonusCounter;
    }
    // Merubah nilai dalam bentuk string
    public String toString() {
		return String.format(this.id);
	}
    // Memastikan kebenaran perbandingan 
    public boolean equals(Member other) {
        return this.generate().equals(other.generate());
    }
    // Mengambil nilai ID member
    public String generate() {
	    String id = NotaGenerator.generateId(nama, noHp);
        setId(id);
        return this.id;
	}
    // Merubah nilai bonus
    public void setbonusCounter(int bonusCounter) {
        this.bonusCounter = bonusCounter;
    }
}
