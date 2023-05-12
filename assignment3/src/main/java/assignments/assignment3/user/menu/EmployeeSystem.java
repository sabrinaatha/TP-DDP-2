/* Nama     : Sabrina Atha Shania
 * NPM      : 2206829591
 * Kelas    : DDP-B
 */

// Meimport library.
package assignments.assignment3.user.menu;
import assignments.assignment3.nota.Nota;
import assignments.assignment3.user.Employee;
import assignments.assignment3.user.Member;
import static assignments.assignment3.nota.NotaManager.notaList;

// Subclass SystemCLI untuk alur program Employee.
public class EmployeeSystem extends SystemCLI {

    // Membuat object baru EmployeeSystem dan mendaftarkan Employee pada CuciCuci.
    public EmployeeSystem() {
        memberList = new Member[]{
                new Employee("Dek Depe", "akuDDP"),
                new Employee("Depram", "musiktualembut"),
                new Employee("Lita Duo", "gitCommitPush"),
                new Employee("Ivan Hoshimachi", "SuamiSahSuisei"),
        };
    }

    // Memproses pilihan dari employee yang masuk ke sistem ini sesuai dengan menu specific.
    @Override
    protected boolean processChoice(int choice) {

        // Nilai awal variabel.
        boolean logout = false;
        Member member = getMember();

        // Kondisi apabila choice sama dengan tiga atau logout.
        if (choice == 3) {
            logout = true;

        // Kondisi apabila choice sama dengan dua atau melihat status nota.
        } else if (choice == 2) {
            for (int y = 0; y < notaList.length; y++) {
                Nota notaKerjakan = notaList[y];
                System.out.println(notaKerjakan.getNotaStatus());
            }
            
        // Kondisi apabila choice sama dengan satu atau mengerjakan tugas.
        } else if (choice == 1) {
            System.out.println("Stand back! " + member.getNama() + " beginning to nyuci!");
            for (int y = 0; y < notaList.length; y++) {
                Nota notaKerjakan = notaList[y];
                System.out.println(notaKerjakan.kerjakan());
            }
        }
        return logout;
    }

    // Displays specific menu untuk Employee.
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. It's nyuci time");
        System.out.println("2. Display List Nota");
        System.out.println("3. Logout");
    }

    // Mengecek adanya member di dalam memberlist.
    public boolean isMemberExist(String id){
        for (Member member: memberList) {
            if (member instanceof Employee) {
                if(member.getId().equals(id)){
                    return true;
                }
            }
        }
        return false;
    }
}