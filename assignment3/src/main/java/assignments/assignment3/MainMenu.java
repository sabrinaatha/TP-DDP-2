/* Nama     : Sabrina Atha Shania
 * NPM      : 2206829591
 * Kelas    : DDP-B
 */

// Meimport library.
package assignments.assignment3;

import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.EmployeeSystem;
import assignments.assignment3.user.menu.MemberSystem;
import assignments.assignment3.user.menu.SystemCLI;
import java.util.Scanner;

import static assignments.assignment3.nota.NotaManager.cal;
import static assignments.assignment3.nota.NotaManager.fmt;

// Public class Main Menu, awal program utama berjalan.
public class MainMenu {
    // Nilai awal variabe.
    private final Scanner in;
    private final LoginManager loginManager;

    // Entry point for the CuciCuci System application.
    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu(new Scanner(System.in), new LoginManager(new EmployeeSystem(), new MemberSystem()));
        mainMenu.run();
    }

    // Menginisasi scanner dan login manager.
    public MainMenu(Scanner in, LoginManager loginManager) {
        this.in = in;
        this.loginManager = loginManager;
    }

    // Menjalankan main menu.
    public void run() {
        // Nilai awal variabel.
        boolean exit = false;

        // Kondisi apabila exit sama dengan true.
        while (!exit) {
            displayMenu();
            int choice = in.nextInt();
            in.nextLine();
            switch (choice) {
                case 1 -> login();
                case 2 -> register();
                case 3 -> toNextDay();
                case 4 -> exit = true;
                default -> System.out.println("Pilihan tidak valid, silakan coba lagi.");
            }
        }
        // Menutup scanner.
        in.close();
    }

    // Skips ke hari selanjutnya dan mengupdate sistem.
    private void toNextDay() {
        System.out.println("Kamu tidur hari ini... zzz...\n");
        NotaManager.toNextDay();
    }

    // Mendaftarkan user pada sistem.
    void register() {
        System.out.println("Masukan nama Anda: ");
        String nama = in.nextLine();
        System.out.println("Masukan nomor handphone Anda: ");
        String noHp = in.nextLine();

        // Validasi input nomorHP
        for (int j = 0; j < noHp.length()-1; j++) {   
            if ((noHp.length() == 1) && (noHp.charAt(j) == '0')) {
                continue;
            } else if (!Character.isDigit(noHp.charAt(j))){
                while (!Character.isDigit(noHp.charAt(j))) {
                    System.out.println("Nomor HP hanya menerima digit");
                    noHp = in.nextLine();
                    j = 0;
                }    
            } 
        }
        System.out.println("Masukan password Anda: ");
        String password = in.nextLine();


        Member registeredMember = loginManager.register(nama, noHp, password);

        // Kondisi apabila register member sama dengan null.
        if(registeredMember == null){
            System.out.printf("User dengan nama %s dan nomor hp %s sudah ada!\n\n", nama, noHp);
            return;
        }
        System.out.printf("Berhasil membuat user dengan ID %s!\n\n", registeredMember.getId());
    }

    // Meminta user untuk login dan memulai SystemCLI yang sesuai.
    private void login() {
        System.out.print("Masukan ID Anda: ");
        String inputId = in.nextLine();
        System.out.print("Masukan password Anda: ");
        String inputPassword = in.nextLine();
        SystemCLI systemCLI = loginManager.getSystem(inputId);

        // Kondisi apabila systemCLI.
        if(systemCLI == null){
            System.out.println("ID atau password invalid.\n");
            return;
        }
        systemCLI.login(in, inputId, inputPassword);
    }

    // Menampilkan menu
    private void displayMenu() {
        System.out.println("Selamat datang di CuciCuci System!");
        System.out.printf("Sekarang tanggal %s\n", fmt.format(cal.getTime()));
        System.out.println("1. Login");
        System.out.println("2. Register Member");
        System.out.println("3. Tidur (Skip hari)");
        System.out.println("4. Exit");
        System.out.print("Apa yang ingin Anda lakukan hari ini? ");
    }
}