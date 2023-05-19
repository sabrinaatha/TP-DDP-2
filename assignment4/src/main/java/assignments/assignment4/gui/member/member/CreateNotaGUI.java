// Nama : Sabrina Atha Shania
// Kelas : DDP-B
// NPM : 2206829591

// Import Library.
package assignments.assignment4.gui.member.member;
import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

// Class untuk tampilan buat nota.
public class CreateNotaGUI extends JPanel {
    // datafields.
    public static final String KEY = "CREATE_NOTA";
    private JPanel mainPanel;
    private JLabel paketLabel;
    private JComboBox<String> paketComboBox;
    private JButton showPaketButton;
    private JLabel beratLabel;
    private JTextField beratTextField;
    private JCheckBox setrikaCheckBox;
    private JCheckBox antarCheckBox;
    private JButton createNotaButton;
    private JButton backButton;
    private final SimpleDateFormat fmt;
    private final Calendar cal;
    private final MemberSystemGUI memberSystemGUI;

    private String paket;
    private int berat;
    private boolean setrika, antar;

    GridBagConstraints c = new GridBagConstraints();

    // Constructor createnota.
    public CreateNotaGUI(MemberSystemGUI memberSystemGUI) {
        super(new BorderLayout());
        this.memberSystemGUI = memberSystemGUI;
        this.fmt = NotaManager.fmt;
        this.cal = NotaManager.cal;

        mainPanel = new JPanel(new GridBagLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Inisiasi GUI.
        initGUI();

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        // Set seluruh label, button, textfield, combobox, dan checkbox.
        paketLabel = new JLabel("Paket Laundry");
        c.gridx = 0;
        c.gridy = 0;
        c.ipady = 15;
        c.ipadx = 0;
        mainPanel.add(paketLabel, c);

        JPanel panel1 = new JPanel();
        paketComboBox = new JComboBox<>(new String[]{"Express", "Fast", "Reguler"});
        showPaketButton = new JButton("Show Paket");
        setPaket("Express");
        panel1.add(paketComboBox);
        panel1.add(showPaketButton);
        c.gridx = 0;
        c.gridy = 1;
        c.ipady = 15;
        c.ipadx = 0;
        mainPanel.add(panel1, c);

        beratLabel = new JLabel("Berat Cucian (Kg)");
        c.gridx = 0;
        c.gridy = 2;
        c.ipady = 15;
        c.ipadx = 0;
        mainPanel.add(beratLabel, c);

        beratTextField = new JTextField();
        c.gridx = 0;
        c.gridy = 3;
        c.ipady = 0;
        c.ipadx = 200;
        mainPanel.add(beratTextField, c);

        setrikaCheckBox = new JCheckBox("Tambah Setrika Service (1000/kg)");
        c.gridx = 0;
        c.gridy = 4;
        c.ipady = 15;
        c.ipadx = 0;
        mainPanel.add(setrikaCheckBox, c);

        antarCheckBox = new JCheckBox("Tambah Antar Service (2000/4kg pertama, kemudian 500/kg)");
        c.gridx = 0;
        c.gridy = 5;
        c.ipady = 15;
        c.ipadx = 0;
        mainPanel.add(antarCheckBox, c);

        JPanel panelKosong = new JPanel();
        c.gridx = 0;
        c.gridy = 6;
        c.ipady = 25;
        c.ipadx = 600;
        mainPanel.add(panelKosong, c);

        createNotaButton = new JButton("Buat Nota");
        c.gridx = 0;
        c.gridy = 7;
        c.ipady = 5;
        c.ipadx = 0;
        mainPanel.add(createNotaButton, c);

        backButton = new JButton("Kembali");
        c.gridx = 0;
        c.gridy = 8;
        c.ipady = 5;
        c.ipadx = 0;
        mainPanel.add(backButton, c);

        // Set semua action yang berhubungan dengan button dan textfield.
        paketComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String result = (String) paketComboBox.getSelectedItem();
                setPaket(result);
            }
        });

        showPaketButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                showPaket();
            }
        });

        beratTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String berat = beratTextField.getText();
                if (!isNumeric(berat)) {
                    setBerat(-1);
                } else {
                    int result = Integer.parseInt(beratTextField.getText());
                    setBerat(result);
                }
            }
        });

        setrikaCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                setSetrika(true);
            }
        });

        antarCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                setAntar(true);
            }
        });

        createNotaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                createNota();
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                handleBack();
            }
        });
    }

    /**
     * Menampilkan list paket pada user.
     * Akan dipanggil jika pengguna menekan "showPaketButton"
     * */
    private void showPaket() {
        String paketInfo = """
                        <html><pre>
                        +-------------Paket-------------+
                        | Express | 1 Hari | 12000 / Kg |
                        | Fast    | 2 Hari | 10000 / Kg |
                        | Reguler | 3 Hari |  7000 / Kg |
                        +-------------------------------+
                        </pre></html>
                        """;

        JLabel label = new JLabel(paketInfo);
        label.setFont(new Font("monospaced", Font.PLAIN, 12));
        JOptionPane.showMessageDialog(this, label, "Paket Information", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method untuk melakukan pengecekan input user dan mendaftarkan nota yang sudah valid pada sistem.
     * Akan dipanggil jika pengguna menekan "createNotaButton"
     * */
    private void createNota() {
        Member member = memberSystemGUI.getLoggedInMember();
        // Kondisi apabila ada yang null.
        if ((paket == null) || (berat == 0)) {
            JOptionPane.showMessageDialog(mainPanel, "Semua field diatas wajib diisi!", "Empty Field", JOptionPane.INFORMATION_MESSAGE);
        // Kondisi apabila berat tidak sesuai.
        } else if (getBerat() < 0) {
            JOptionPane.showMessageDialog(mainPanel,  "Berat Cucian harus berisi angka.", "Error", JOptionPane.ERROR_MESSAGE);
            beratTextField.setText("");
        // Kondisi apabila berat kurang dari 2.
        } else if (getBerat() < 2) {
            setBerat(2);
            JOptionPane.showMessageDialog(mainPanel,  "Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg", "Info", JOptionPane.INFORMATION_MESSAGE);
            Nota nota = new Nota(memberSystemGUI.getLoggedInMember(), berat, paket, fmt.format(cal.getTime()));
            if (getSetrika() == true) {
                nota.addService(new SetrikaService());
            }
            if (getAntar() == true) {
                nota.addService(new AntarService());
            }
            NotaManager.addNota(nota);
            memberSystemGUI.getLoggedInMember().addNota(nota);
            JOptionPane.showMessageDialog(mainPanel,  "Nota berhasil dibuat!", "Success", JOptionPane.INFORMATION_MESSAGE);
            startAgain();
        } else {
            Nota nota = new Nota(member, berat, paket, fmt.format(cal.getTime()));
            if (getSetrika() == true) {
                nota.addService(new SetrikaService());
            }
            if (getAntar() == true) {
                nota.addService(new AntarService());
            }
            NotaManager.addNota(nota);
            member.addNota(nota);
            JOptionPane.showMessageDialog(mainPanel,  "Nota berhasil dibuat!", "Success", JOptionPane.INFORMATION_MESSAGE);
            startAgain();
        }
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.navigateTo(MemberSystemGUI.KEY);
    }

    // Method untuk mengecek apakah seluruh nomor adalah angka.
    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }

    // Reset seluruh button dan textfield.
    public void startAgain() {
        setrikaCheckBox.setSelected(false);
        antarCheckBox.setSelected(false);
        beratTextField.setText("");
        paketComboBox.setSelectedIndex(0);
        setPaket("Express");
        setBerat(0);
        setSetrika(false);
        setAntar(false);
    }

    // Getter.
    public String getPaket() {
        return paket;
    }

    public int getBerat() {
        return berat;
    }

    public boolean getSetrika() {
        return setrika;
    }

    public boolean getAntar() {
        return antar;
    }

    // Setter.
    public void setPaket(String paket) {
        this.paket = paket;
    }

    public void setBerat(int berat) {
        this.berat = berat;
    }

    public void setSetrika(Boolean setrika) {
        this.setrika = setrika;
    }

    public void setAntar(Boolean antar) {
        this.antar = antar;
    }
}
