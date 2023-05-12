/* Nama     : Sabrina Atha Shania
 * NPM      : 2206829591
 * Kelas    : DDP-B
 */

// Meimport library.
package assignments.assignment3.user;

// Subclass memer untuk employee.
public class Employee extends Member {
    public static int employeeCount;
    public Employee(String nama, String password) {
        super(nama, generateId(nama), password);
    }

    // Membuat ID employee dari nama employee dengan format.
    private static String generateId(String nama) {
        // Kondisi apabila nama sama dengan Dek Depe.
        if(nama.equals("Dek Depe")) {
            if (employeeCount != 0) {
                employeeCount += 1;
            }
        
        // Kondisi apabila nama sama dengan Depram.
        } else if (nama.equals("Depram")) {
            employeeCount += 1;
            return "DEPRAM-" + employeeCount;
        
        // Kondisi apabila nama sama dengan Lita Duo.
        } else if (nama.equals("Lita Duo")) {
            employeeCount += 1;
            return "LITA-" + employeeCount;
        
        // Kondisi apabila nama sama dengan Ivan Hoshimachi.
        } else if (nama.equals("Ivan Hoshimachi")) {
            employeeCount += 1;
            return "IVAN-" + employeeCount;
        
        // Kondisi yang lainnya
        } else {
            employeeCount += 1;
            String id = "";
            id += (nama.split(" ")[0] + "-").toUpperCase();
            id += employeeCount;
            return id;
        }
        return "DEK-" + employeeCount;
    }
}
