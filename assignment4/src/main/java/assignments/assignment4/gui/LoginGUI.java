package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment3.user.menu.MemberSystem;
import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.MainFrame;
import assignments.assignment4.gui.member.employee.EmployeeSystemGUI;
import assignments.assignment4.gui.member.member.MemberSystemGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JPanel {
    public static final String KEY = "LOGIN";
    private JPanel mainPanel;
    private JLabel idLabel;
    private JTextField idTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;
    private LoginManager loginManager;
    private String id, passwordID;

    public LoginGUI(LoginManager loginManager) {
        super(new BorderLayout()); // Setup layout, Feel free to make any changes
        this.loginManager = loginManager;

        // Set up main panel, Feel free to make any changes
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initGUI();

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        idLabel = new JLabel("Masukkan ID Anda:");
        JPanel panel1 = new JPanel();
        idTextField = new JTextField();
        panel1.add(idTextField);

        mainPanel.add(idLabel);
        mainPanel.add(panel1);

        passwordLabel = new JLabel("Masukkan password Anda:");
        JPanel panel2 = new JPanel();
        passwordField = new JPasswordField();
        panel2.add(passwordField);

        mainPanel.add(passwordLabel);
        mainPanel.add(panel2);

        JPanel panel3 = new JPanel();
        loginButton = new JButton("Login");
        panel3.add(loginButton);

        JPanel panel4 = new JPanel();
        backButton = new JButton("Kembali");
        panel4.add(backButton);

        mainPanel.add(panel3);
        mainPanel.add(panel4);

        idTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){  
                String userID = idTextField.getText();
                setID(userID);
            }
        });

        passwordField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){  
                String userPassword = String.valueOf(passwordField.getPassword());
                setPasswordID(userPassword);
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
    }

    /**
     * Method untuk login pada sistem.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private void handleLogin() {
        MainFrame mainFrame = MainFrame.getInstance();
        SystemCLI userMember = loginManager.getSystem(id);
        if(userMember == null){
            JOptionPane.showMessageDialog(mainPanel,  "ID atau password invalid.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            startAgain();
        } else {
            boolean success = mainFrame.login(id, passwordID);
            if (success == false) {
                JOptionPane.showMessageDialog(mainPanel,  "Invalid ID or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                startAgain();
            } 
        }
    }

    public void startAgain() {
        idTextField.setText("");
        passwordField.setText("");
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.navigateTo(HomeGUI.KEY);
    }

    public String getID() {
        return id;
    }

    public String getPasswordID() {
        return passwordID;
    }

    public void setID(String id) {
        this.id = id;
    }

    public void setPasswordID(String password) {
        this.passwordID = password;
    }
}
