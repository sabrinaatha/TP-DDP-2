package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterGUI extends JPanel {
    public static final String KEY = "REGISTER";
    private JPanel mainPanel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel phoneLabel;
    private JTextField phoneTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton registerButton;
    private LoginManager loginManager;
    private JButton backButton;

    private String name, numberPhone, password;

    public RegisterGUI(LoginManager loginManager) {
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
        nameLabel = new JLabel("Masukkan nama Anda");
        JPanel panel1 = new JPanel();
        nameTextField = new JTextField();
        panel1.add(nameTextField);

        mainPanel.add(nameLabel);
        mainPanel.add(panel1);

        phoneLabel = new JLabel("Masukkan nomor handphone Anda");
        JPanel panel2 = new JPanel();
        phoneTextField = new JTextField();
        panel2.add(phoneTextField);

        mainPanel.add(phoneLabel);
        mainPanel.add(panel2);

        passwordLabel = new JLabel("Masukkan password Anda");
        JPanel panel3 = new JPanel();
        passwordField = new JPasswordField();
        panel3.add(passwordField);

        mainPanel.add(passwordLabel);
        mainPanel.add(panel3);

        JPanel panel4 = new JPanel();
        registerButton = new JButton("Register");
        panel4.add(registerButton);

        JPanel panel5 = new JPanel();
        backButton = new JButton("Kembali");
        panel5.add(backButton);

        mainPanel.add(panel4);
        mainPanel.add(panel5);

        nameTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){ 
                String userName = nameTextField.getText();
                setName(userName);
            }
        });

        phoneTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){ 
                String userPhone = phoneTextField.getText();
                setNoPhone(userPhone);
            }
        }) ;

        passwordField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){ 
                String userPassword = String.valueOf(passwordField.getPassword());
                setPassword(userPassword);
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){  
                handleRegister();
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
    * Method untuk mendaftarkan member pada sistem.
    * Akan dipanggil jika pengguna menekan "registerButton"
    * */
    private void handleRegister() {
        if ((name == null) || (numberPhone == null) || (password == null)) {
            JOptionPane.showMessageDialog(mainPanel, "Semua field diatas wajib diisi!", "Empty Field", JOptionPane.INFORMATION_MESSAGE);
        } else if (!isNumeric(numberPhone)) {
            JOptionPane.showMessageDialog(mainPanel, "Nomor handphone harus berisi angka!", "Invalid Phone Number", JOptionPane.ERROR_MESSAGE);
            phoneTextField.setText("");
        } else {
            Member newMember = loginManager.register(name, numberPhone, password);
            if (newMember == null) {
                JOptionPane.showMessageDialog(mainPanel, "User dengan nama " + name + " dan nomor hp " + numberPhone + " sudah ada!\n", "Registration Failed", JOptionPane.ERROR_MESSAGE);
                startAgain();
            } else {
                JOptionPane.showMessageDialog(mainPanel, "Berhasil membuat user dengan ID " + newMember.getId() + "!\n", "Registration Succesful", JOptionPane.INFORMATION_MESSAGE);
                startAgain();
            }
        }
    }

    public void startAgain() {
        nameTextField.setText("");
        phoneTextField.setText("");
        passwordField.setText("");
        handleBack();
    }
    public String getName() {
        return name;
    }

    public String getNoPhone() {
        return numberPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNoPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }
}
