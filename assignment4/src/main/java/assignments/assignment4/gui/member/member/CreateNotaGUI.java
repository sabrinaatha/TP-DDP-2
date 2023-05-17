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
import java.util.Arrays;
import java.util.Calendar;

//MEMBER MEMBERAN MASIH ERROR
public class CreateNotaGUI extends JPanel {
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

    public CreateNotaGUI(MemberSystemGUI memberSystemGUI) {
        super(new BorderLayout());
        this.memberSystemGUI = memberSystemGUI;
        this.fmt = NotaManager.fmt;
        this.cal = NotaManager.cal;

        mainPanel = new JPanel(new GridBagLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Set up main panel, Feel free to make any changes
        initGUI();

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */

    private void initGUI() {
        //Null
        JPanel panel1 = new JPanel();
        paketLabel = new JLabel("Paket Laundry");
        paketComboBox = new JComboBox<>(new String[]{"Express", "Fast", "Reguler"});
        showPaketButton = new JButton("Show Paket");
        panel1.add(paketLabel);
        panel1.add(paketComboBox);
        panel1.add(showPaketButton);
        mainPanel.add(panel1);

        JPanel panel2 = new JPanel();
        beratLabel = new JLabel("Berat Cucian (Kg)");
        beratTextField = new JTextField();
        panel2.add(beratLabel);
        panel2.add(beratTextField);
        mainPanel.add(panel2);

        JPanel panel3 = new JPanel();
        setrikaCheckBox = new JCheckBox("Tambah Setrika Service (1000/kg)");
        antarCheckBox = new JCheckBox("Tambah Antar Service (2000/4kg pertama, kemudian 500/kg)");
        panel3.add(setrikaCheckBox);
        panel3.add(antarCheckBox);
        mainPanel.add(panel3);

        JPanel panel4 = new JPanel();
        createNotaButton = new JButton("Buat Nota");
        panel4.add(createNotaButton);
        mainPanel.add(panel4);

        JPanel panel5 = new JPanel();
        backButton = new JButton("Kembali");
        panel5.add(backButton);
        mainPanel.add(panel5);

        //gatau action atau key
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
        if (memberSystemGUI.getLoggedInMember() != null) {
            System.out.println(memberSystemGUI.getLoggedInMember().getNama());
        } else {
            System.out.println("sini");
        }
        if (getBerat() < 0) {
            JOptionPane.showMessageDialog(mainPanel,  "Berat cucian harus berisi angka.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (getBerat() < 2) {
            JOptionPane.showMessageDialog(mainPanel,  "Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg", "Infor", JOptionPane.INFORMATION_MESSAGE);
        } else {
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

    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }

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
