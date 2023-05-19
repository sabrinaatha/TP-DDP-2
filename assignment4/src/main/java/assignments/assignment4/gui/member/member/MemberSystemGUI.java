// Nama : Sabrina Atha Shania
// Kelas : DDP-B
// NPM : 2206829591

// Import Library.
package assignments.assignment4.gui.member.member;
import assignments.assignment3.nota.Nota;
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.MainFrame;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Class untuk tampilan membersystemGUI.
public class MemberSystemGUI extends AbstractMemberGUI {
    // Datafields.
    public static final String KEY = "MEMBER";
    private JPanel mainPanel;

    // Constructor membersystemGUI.
    public MemberSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    // Page dari membersystem.
    @Override
    public String getPageName(){
        return KEY;
    }

    // Getter.
    public Member getLoggedInMember(){
        return loggedInMember;
    }

    /**
     * Method ini mensupply buttons yang sesuai dengan requirements MemberSystem.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    @Override
    protected JButton[] createButtons() {
        JButton laundryButton = new JButton("Saya ingin laundry");
        JButton notaButton = new JButton("Lihat detail nota saya");

        return new JButton[]{
            laundryButton, notaButton
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
                e -> createNota(),
                e -> showDetailNota(),
        };
    }

    /**
     * Menampilkan detail Nota milik loggedInMember.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     * */
    private void showDetailNota() {
        String result = "";
        Nota[] notaList = getLoggedInMember().getNotaList();

        // Kondisi ketika tidak ada nota.
        if (notaList.length == 0) {
            // Set info GUI.
            JTextArea textArea = new JTextArea(30, 30);
            JScrollPane scrollPane = new JScrollPane(textArea);
            setPreferredSize(new Dimension(500, 500));
            textArea.setText("Belum pernah laundy di CuciCuci, hiks :'(");
            add(scrollPane, BorderLayout.CENTER);
            JOptionPane.showMessageDialog(mainPanel, scrollPane,  "Detail nota", JOptionPane.INFORMATION_MESSAGE);

        } else {
            // Ambil semua hasil tostring nota.
            for (Nota nota:
                getLoggedInMember().getNotaList()) {
                result += nota.toString() + "\n"; 
            }
            // Set info GUI.
            JTextArea textArea = new JTextArea(30, 30);
            JScrollPane scrollPane = new JScrollPane(textArea);
            setPreferredSize(new Dimension(500, 500));
            textArea.setText(result);
            add(scrollPane, BorderLayout.CENTER);
            JOptionPane.showMessageDialog(mainPanel, scrollPane,"Detail nota", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Pergi ke halaman CreateNotaGUI.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void createNota() {
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.navigateTo(CreateNotaGUI.KEY);
    }

}
