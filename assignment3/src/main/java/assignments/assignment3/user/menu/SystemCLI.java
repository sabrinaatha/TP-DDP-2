/* Nama     : Sabrina Atha Shania
 * NPM      : 2206829591
 * Kelas    : DDP-B
 */

// Meimport library.
package assignments.assignment3.user.menu;
import assignments.assignment3.user.Member;
import java.util.Scanner;

// Class abstract.
public abstract class SystemCLI {

    // Nilai awal variabel dan inisiasi array.
    protected Member[] memberList = new Member[0];
    protected Member loginMember;
    protected Scanner in;

    /* Otentikasi pengguna dengan ID dan password yang diberikan dan memulai sesi pengguna. 
    Akan berhenti jika logout atau ID / Password salah. */
    public void login(Scanner in, String inputId, String inputPassword){
        Member authMember = authUser(inputId, inputPassword);

        // Kondisi apabila authmember tidak null.
        if (authMember != null) {
            this.in = in;
            System.out.println("Login successful!");
            run(in, authMember);
            return;
        }

        System.out.println("Invalid ID or password.\n");
    }

    // Memulai sesi pengguna dan menangani input.
    public void run(Scanner in, Member member){
        setMember(member);
        boolean logout = false;

        while (!logout) {
            displayMenu();
            int choice = in.nextInt();
            in.nextLine();
            logout = processChoice(choice);
        }
        loginMember = null;
        System.out.println("Logging out...\n");
    }

    // Mengecek semua user dengan ID dan password yang diberikan.
    public Member authUser(String id, String pass) {
        for (Member user : memberList) {

            if (!user.getId().equals(id)) {
                continue;
            }
            if(user.login(id, pass)){
                return user;
            }
            return null;
        }
        return null;
    };

    // Memeriksa apakah ada Member dengan ID yang diberikan.
    public boolean isMemberExist(String id){
        for (Member member: memberList) {
            if(member.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    // Displays main menu untuk user yang menggunakan sistem.
    protected void displayMenu(){
        System.out.printf("\nLogin as : %s\nSelamat datang %s!\n\n", loginMember.getId(), loginMember.getNama());
        displaySpecificMenu();
        System.out.print("Apa yang ingin Anda lakukan hari ini? ");
    }

    // Memproses pilihan dari pengguna yang menggunakan sistem sesuai dengan rolesnya.
    protected abstract boolean processChoice(int choice);

    // Displays specific menu sesuai class yang menginheritnya.
    protected abstract void displaySpecificMenu();

    // Mencetak jenis jenis paket.
    public static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }

    // Dibawah ini adalah getter.
    public Member[] getMemberList() {
        return memberList;
    }

    public Member getMember() {
        return loginMember;
    } 

    // Dibawah ini adalah setter.
    public void setMember(Member member) {
        this.loginMember = member;
    }
}