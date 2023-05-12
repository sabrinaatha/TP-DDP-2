/* Nama     : Sabrina Atha Shania
 * NPM      : 2206829591
 * Kelas    : DDP-B
 */

// Meimport library.
package assignments.assignment3.user.menu;
import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;

// Subclass SystemCLI untuk alur program khusus setelah login member.
public class MemberSystem extends SystemCLI {
    // Memproses pilihan dari Member yang masuk ke sistem ini sesuai dengan menu specific.
    @Override
    protected boolean processChoice(int choice) {
        // Nilai awal variabel dan inisiasi variabel.
        String paket = "";
        boolean logout = false, valuePaket = true;
        int berat = 0;
        Member member = getMember();

        // Kondisi apabila choice sama dengan tiga atau logout.
        if (choice == 3) {
            logout = true;
        // Kondisi apabila choice sama dengan satu atau membuat nota.
        } else if (choice == 1) {
            System.out.print("Masukkan paket laundry: \n");
            showPaket();
            paket = in.nextLine();
            while (valuePaket) {
                // Menjalankan program berdasarkan nilai input paket.
                switch (paket.toLowerCase()) {
                    // Statement yang dijalankan sesuai dengan input paket.
                    case "express" :
                        valuePaket = false;
                        break; 
                    case "fast" :
                        valuePaket = false;
                        break; 
                    case "reguler" :
                        valuePaket = false;
                        break;
                    case "?" :
                        showPaket();
                        System.out.print("Masukkan paket laundry: \n");
                        paket = in.nextLine();
                        break;

                    // Statement yang dijalankan apabila paket selain case diatas.    
                    default :
                        System.out.println("Paket " + paket + " tidak diketahui");
                        System.out.println("[ketik ? untuk mencari tahu jenis paket]");
                        System.out.print("Masukkan paket laundry: \n");
                        paket = in.nextLine();
                        break;
                }
            }
            // Meminta input berat cucian.
            System.out.println("Masukkan berat cucian Anda [Kg]: ");
            String beratInput = in.nextLine();

            // Validasi input berat cucian.
            while (!isNumeric(beratInput) || Integer.parseInt(beratInput) < 1) {
                System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                beratInput = in.nextLine();
            }
            berat = Integer.parseInt(beratInput);
            if (berat < 2) {
                System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                berat = 2;
            }
            // Mengambil tanggal hari ini.
            String tanggal = NotaManager.tanggalMasuk();

            // Membuat nota baru dan memasukkannya kedalam notalist yang ada di member.
            Nota notaBaru = new Nota(member, berat, paket, tanggal);
            member.addNota(notaBaru);

            // Menambah cuci service dalam tiap nota.
            LaundryService cuciLaundryService = new CuciService();
            notaBaru.addService(cuciLaundryService);

            // Meminta input untuk service tambahan selain cuci.
            System.out.println("Apakah kamu ingin cucianmu disetrika oleh staff professional kami?");
            System.out.println("Hanya tambah 1000 / kg :0");
            System.out.print("[Ketik x untuk tidak mau]: ");
            String jasaSetrika = in.nextLine();
            if ((jasaSetrika.equals("x")) || (jasaSetrika.equals("X"))) {
            } else {
                LaundryService setrikaLaundryService = new SetrikaService();
                notaBaru.addService(setrikaLaundryService);
            }
            System.out.println("Mau diantar oleh kurir kami? Dijamin aman dan cepat sampai tujuan!");
            System.out.println("Cuma 2000 / 4kg, kemudian 500 / kg");
            System.out.print("[Ketik x untuk tidak mau]: ");
            String jasaAntar = in.nextLine();
            if ((jasaAntar.equals("x")) || (jasaAntar.equals("x"))) {
            } else {
                LaundryService antarLaundryService = new AntarService();
                notaBaru.addService(antarLaundryService);
            }

            /* Membuat nota baru dan memasukkannya kedalam notalist yang ada dari seluruh file, 
            serta set seluruh variabel yang dibutuhkan. */
            System.out.println("Nota berhasil dibuat!");
            notaBaru.setStatus(paket);
            notaBaru.setNoService(0);
            int panjangNotaMember = NotaManager.notaList.length;
            notaBaru.setId(panjangNotaMember);
            NotaManager.addNota(notaBaru);

        // Kondisi apabila choice sama dengan dua atau melihat seluruh list nota yang telah terbuat.
        } else if (choice == 2) {
            Nota[] notaListMember = member.getNotaList();
            for (int a = 0; a < notaListMember.length;a++) {
                System.out.println(notaListMember[a]);
            }
        }
        return logout;
    }

    // Displays specific menu untuk Member biasa.
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. Saya ingin laundry");
        System.out.println("2. Lihat detail nota saya");
        System.out.println("3. Logout");
    }

    // Menambahkan Member baru ke sistem.
    public void addMember(Member member) {
        Member[] newMemberList = new Member[memberList.length + 1];
        for (int i = 0; i < memberList.length; i++) {
            newMemberList[i] = memberList[i];
          }
          memberList = newMemberList;
    
          newMemberList[memberList.length - 1] = member;
    }

    // Mengecek adanya member dalam memberlist.
    public boolean isMemberExist(String id){
        for (Member member: memberList) {
            if(member.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    // Pengecekan validasi angka.
    private static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }
}