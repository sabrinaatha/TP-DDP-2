// Nama : Sabrina Atha Shania
// Kelas : DDP-B
// NPM : 2206829591

// Import Library.
package assignments.assignment4.gui;
import assignments.assignment3.LoginManager;
import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Class untuk tampilan login.
public class LoginGUI extends JPanel {
    // Datafields.
    public static final String KEY = "LOGIN";
    private JPanel mainPanel;
    private JLabel idLabel;
    private static JTextField idTextField;
    private JLabel passwordLabel;
    private static JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;
    private LoginManager loginManager;
    private static String id, passwordID;

    GridBagConstraints c = new GridBagConstraints();

    // Costructor loginGUI.
    public LoginGUI(LoginManager loginManager) {
        super(new BorderLayout()); // Setup layout.
        this.loginManager = loginManager;

        // Set up main panel.
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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
        // Set seluruh label, button, dan textfield.
        idLabel = new JLabel("Masukkan ID Anda:");
        c.gridx = 0;
        c.gridy = 0;
        c.ipady = 25;
        c.ipadx = 0;
        mainPanel.add(idLabel, c);

        idTextField = new JTextField();
        c.gridx = 0;
        c.gridy = 1;
        c.ipady = 0;
        c.ipadx = 600;
        mainPanel.add(idTextField, c);

        passwordLabel = new JLabel("Masukkan password Anda:");
        c.gridx = 0;
        c.gridy = 2;
        c.ipady = 25;
        c.ipadx = 0;
        mainPanel.add(passwordLabel, c);

        passwordField = new JPasswordField();
        c.gridx = 0;
        c.gridy = 3;
        c.ipady = 0;
        c.ipadx = 600;
        mainPanel.add(passwordField, c);

        JPanel panelKosong = new JPanel();
        c.gridx = 0;
        c.gridy = 4;
        c.ipady = 25;
        c.ipadx = 600;
        mainPanel.add(panelKosong, c);

        loginButton = new JButton("Login");
        c.gridx = 0;
        c.gridy = 5;
        c.ipady = 5;
        c.ipadx = 0;
        mainPanel.add(loginButton, c);

        backButton = new JButton("Kembali");
        c.gridx = 0;
        c.gridy = 6;
        c.ipady = 5;
        c.ipadx = 0;
        mainPanel.add(backButton, c);

        // Set semua action yang berhubungan dengan button dan textfield.
        idTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){  
                String userID = idTextField.getText();
                id = userID;
            }
        });

        passwordField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){  
                String userPassword = String.valueOf(passwordField.getPassword());
                passwordID = userPassword;
            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){ 
                handleLogin();
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){ 
                handleBack();
            }
        });
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.navigateTo(HomeGUI.KEY);
        startAgain();
    }

    /**
     * Method untuk login pada sistem.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private void handleLogin() {
        MainFrame mainFrame = MainFrame.getInstance();
        SystemCLI userMember = loginManager.getSystem(id);
        // Pengecekan sesuai dengan syarat di soal.
        if ((id == null) || (passwordID == null)) {
            JOptionPane.showMessageDialog(mainPanel, "Semua field diatas wajib diisi!", "Empty Field", JOptionPane.INFORMATION_MESSAGE);
        // Kondisi ketika tidak ada membernya.
        } else if(userMember == null){
            JOptionPane.showMessageDialog(mainPanel,  "ID atau password invalid.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            startAgain();
        } else {
            boolean success = mainFrame.login(id, passwordID);
            // Kondisi ketika password atau id salah input.
            if (!success) {
                JOptionPane.showMessageDialog(mainPanel,  "Invalid ID or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                startAgain();
                mainFrame.navigateTo(HomeGUI.KEY);
            } 
            
        }
    }

    // Reset seluruh button dan textfield.
    public static void startAgain() {
        idTextField.setText("");
        passwordField.setText("");
        id = null;
        passwordID = null;
    }

    // Getter.
    public String getID() {
        return id;
    }

    public String getPasswordID() {
        return passwordID;
    }
}
