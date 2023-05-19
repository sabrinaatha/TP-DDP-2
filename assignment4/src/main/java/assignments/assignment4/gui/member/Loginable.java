// Nama : Sabrina Atha Shania
// Kelas : DDP-B
// NPM : 2206829591

// Import Library.
package assignments.assignment4.gui.member;

// Interface untuk membersystem dan employeesystem.
public interface Loginable {
    boolean login(String id, String password);
    void logout();
    String getPageName();

}
