// Nama : Sabrina Atha Shania
// Kelas : DDP-B
// NPM : 2206829591

// Import Library.
package assignments.assignment4.gui;
import assignments.assignment3.LoginManager;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Class untuk tampilan register.
public class RegisterGUI extends JPanel {
    // Data fields.
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

    GridBagConstraints c = new GridBagConstraints();
    private String name, numberPhone, password;

    //Constructor registerGUI.
    public RegisterGUI(LoginManager loginManager) {
        super(new BorderLayout()); // Setup layout
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
        // Set semua label, button, dan textfield.
        nameLabel = new JLabel("Masukkan nama Anda");
        nameTextField = new JTextField();

        c.gridx = 0;
        c.gridy = 0;
        c.ipady = 25;
        c.ipadx = 0;
        mainPanel.add(nameLabel, c);

        c.gridx = 0;
        c.gridy = 1;
        c.ipady = 0;
        c.ipadx = 600;
        mainPanel.add(nameTextField, c);

        phoneLabel = new JLabel("Masukkan nomor handphone Anda");
        phoneTextField = new JTextField();

        c.gridx = 0;
        c.gridy = 2;
        c.ipady = 25;
        c.ipadx = 0;
        mainPanel.add(phoneLabel, c);

        c.gridx = 0;
        c.gridy = 3;
        c.ipady = 0;
        c.ipadx = 600;
        mainPanel.add(phoneTextField, c);

        passwordLabel = new JLabel("Masukkan password Anda");
        passwordField = new JPasswordField();

        c.gridx = 0;
        c.gridy = 4;
        c.ipady = 25;
        c.ipadx = 0;
        mainPanel.add(passwordLabel, c);

        c.gridx = 0;
        c.gridy = 5;
        c.ipady = 0;
        c.ipadx = 600;
        mainPanel.add(passwordField, c);

        JPanel panelKosong = new JPanel();
        c.gridx = 0;
        c.gridy = 6;
        c.ipady = 25;
        c.ipadx = 600;
        mainPanel.add(panelKosong, c);

        registerButton = new JButton("Register");

        c.gridx = 0;
        c.gridy = 7;
        c.ipady = 5;
        c.ipadx = 0;
        mainPanel.add(registerButton, c);

        backButton = new JButton("Kembali");

        c.gridx = 0;
        c.gridy = 8;
        c.ipady = 5;
        c.ipadx = 0;
        mainPanel.add(backButton, c);

        // Set semua action yang berhubungan dengan button dan textfield.
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
        startAgain();
    }

    /**
    * Method untuk mendaftarkan member pada sistem.
    * Akan dipanggil jika pengguna menekan "registerButton"
    * */
    private void handleRegister() {
        // Pengecekan sesuai dengan syarat soal.
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

    // Reset seluruh button dan textfield.
    public void startAgain() {
        nameTextField.setText("");
        phoneTextField.setText("");
        passwordField.setText("");
        setName(null);
        setPassword(null);
        setNoPhone(null);
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.navigateTo(HomeGUI.KEY);
    }

    // Getter.
    public String getName() {
        return name;
    }

    public String getNoPhone() {
        return numberPhone;
    }

    public String getPassword() {
        return password;
    }

    // Setter.
    public void setName(String name) {
        this.name = name;
    }

    public void setNoPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Method untuk mengecek apakah seluruh nomor adalah angka.
    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }
}
