/* Nama     : Sabrina Atha Shania
 * NPM      : 2206829591
 * Kelas    : DDP-B
 */

// Meimport library.
package assignments.assignment3;
import assignments.assignment1.NotaGenerator;
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.EmployeeSystem;
import assignments.assignment3.user.menu.MemberSystem;
import assignments.assignment3.user.menu.SystemCLI;

// Class untuk login program.
public class LoginManager {
    // Nilai variabel.
    private final EmployeeSystem employeeSystem;
    private final MemberSystem memberSystem;

    // Construktor dengan argumen. 
    public LoginManager(EmployeeSystem employeeSystem, MemberSystem memberSystem) {
        this.employeeSystem = employeeSystem;
        this.memberSystem = memberSystem;
    }

    // Method mapping dari ke SystemCLI yang sesuai.
    public SystemCLI getSystem(String id){
        if(employeeSystem.isMemberExist(id)){
            return employeeSystem;
        }
        if(memberSystem.isMemberExist(id)){
            return memberSystem;
        }
        return null;
    }

    // Mendaftarkan member baru dengan informasi yang diberikan.
    public Member register(String nama, String noHp, String password) {
        Member memberBaru = new Member(nama, noHp, password);
        Member[] memberLama = memberSystem.getMemberList();
        for (int a = 0; a < memberLama.length;a++) {
            // Kondisi ketika nilainya null
            if (!memberLama[a].equals(memberBaru)) {
                String id = NotaGenerator.generateId(nama, noHp);
                String idLama = memberLama[a].getId();
                if (id.equals(idLama)) {
                    return null;
                // Kondisi ketika nilainya berbeda dengan memberList indeks ke-a
                } else {
                    continue;
                }
            // Kondisi ketika nilainya tidak null
            } else {
                return null;
            } 
        }
        String id = NotaGenerator.generateId(nama, noHp);
        memberBaru.setId(id);
        MemberSystem mySystem = new MemberSystem();
        mySystem.addMember(memberBaru);
        memberSystem.addMember(memberBaru);
        return memberBaru;
    }

}