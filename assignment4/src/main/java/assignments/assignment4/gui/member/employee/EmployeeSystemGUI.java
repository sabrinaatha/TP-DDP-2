// Nama : Sabrina Atha Shania
// Kelas : DDP-B
// NPM : 2206829591

// Import Library.
package assignments.assignment4.gui.member.employee;
import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Class untuk tampilan employee.
public class EmployeeSystemGUI extends AbstractMemberGUI {
    // Datafields.
    public static final String KEY = "EMPLOYEE";
    private JPanel mainPanel;

    // Constructor employeesystem.
    public EmployeeSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    // Page dari employeesystem.
    @Override
    public String getPageName(){
        return KEY;
    }

    /**
     * Method ini mensupply buttons yang sesuai dengan requirements Employee.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    @Override
    protected JButton[] createButtons() {
        JButton cuci = new JButton("It's nyuci time");
        JButton display = new JButton("Display List Nota");
        return new JButton[]{
            cuci, display
        };
    }

    /**
     * Method ini mensupply ActionListener korespondensi dengan button yang dibuat createButtons()
     * sesuai dengan requirements MemberSystem.
     *
     * @return Array of ActionListener.
     * */
    @Override
    protected ActionListener[] createActionListeners() {
        return new ActionListener[]{
                e -> cuci(),
                e -> displayNota(),
        };
    }

    /**
     * Menampilkan semua Nota yang ada pada sistem.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     * */
    private void displayNota() {
        Nota[] notaList = NotaManager.notaList;
        String result = "";
        // Kondisi apabila nota sama dengan nol.
        if (notaList.length == 0) {
            JOptionPane.showMessageDialog(mainPanel,"Belum ada nota",  "Detail nota", JOptionPane.ERROR_MESSAGE);
        } else {
            for (Nota nota:notaList) {
                result += nota.getNotaStatus() + "\n";
            }
            JOptionPane.showMessageDialog(mainPanel, result, "List Nota", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Menampilkan dan melakukan action mencuci.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void cuci() {
        Nota[] notaList = NotaManager.notaList;
        String result = "";
        JOptionPane.showMessageDialog(mainPanel, "Stand back! " + loggedInMember.getNama() + " beginning to nyuci!\n", "Nyuci Time", JOptionPane.INFORMATION_MESSAGE);
        // Kondisi apabila nota sama dengan nol.

        if (notaList.length == 0) {
            JOptionPane.showMessageDialog(mainPanel,"Nothing to cuci here",  "Detail nota", JOptionPane.ERROR_MESSAGE);
        } else {
            for (Nota nota:notaList) {
                result += nota.kerjakan() + "\n";
            }

            JOptionPane.showMessageDialog(mainPanel, result, "Nyuci Results", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
