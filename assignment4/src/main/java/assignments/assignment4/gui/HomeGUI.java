// Nama : Sabrina Atha Shania
// Kelas : DDP-B
// NPM : 2206829591

// Import Library.
package assignments.assignment4.gui;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static assignments.assignment3.nota.NotaManager.cal;
import static assignments.assignment3.nota.NotaManager.toNextDay;

// Class untuk tampilan awal.
public class HomeGUI extends JPanel {
    // Data fields.
    public static final String KEY = "HOME";
    private JLabel titleLabel;
    private JLabel dateLabel;
    private JPanel mainPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JButton toNextDayButton;

    private String today;
    GridBagConstraints c = new GridBagConstraints();

    // Constructor homeGUI.
    public HomeGUI(){
        // Setup layout.
        super(new BorderLayout()); 

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
        // Set semua label dan button.
        JPanel panel1 = new JPanel();
        titleLabel = new JLabel("Selamat datang di CuciCuci System!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        c.gridx = 0;
        c.gridy = 0;
        c.ipady = 50;
        panel1.add(titleLabel);
        mainPanel.add(panel1, c);

        JPanel panel2 = new JPanel();
        loginButton = new JButton("Login");
        c.gridx = 0;
        c.gridy = 1;
        c.ipady = 50;
        panel2.add(loginButton);
        mainPanel.add(panel2, c);
    

        JPanel panel3 = new JPanel();
        registerButton = new JButton("Register");
        c.gridx = 0;
        c.gridy = 2;
        c.ipady = 50;
        panel3.add(registerButton);
        mainPanel.add(panel3, c);

        JPanel panel4 = new JPanel();
        toNextDayButton = new JButton("Next Day");
        c.gridx = 0;
        c.gridy = 3;
        c.ipady = 50;
        panel4.add(toNextDayButton);
        mainPanel.add(panel4, c);

        JPanel panel5 = new JPanel();
        dateLabel = new JLabel();
        dateLabel.setText("Hari ini:" + today());
        c.gridx = 0;
        c.gridy = 4;
        c.ipady = 0;
        panel5.add(dateLabel);
        mainPanel.add(panel5, c);

        // Set semua action yang berhubungan dengan button.
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){  
                handleToLogin();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){ 
                handleToRegister(); 
            }
        });

        toNextDayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){  
                handleNextDay();
            }   
        });
    }

    /**
     * Method untuk pergi ke halaman register.
     * Akan dipanggil jika pengguna menekan "registerButton"
     * */
    private static void handleToRegister() {
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.navigateTo(RegisterGUI.KEY);
    }

    /**
     * Method untuk pergi ke halaman login.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private static void handleToLogin() {
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.navigateTo(LoginGUI.KEY);
    }

    /**
     * Method untuk skip hari.
     * Akan dipanggil jika pengguna menekan "toNextDayButton"
     * */
    private void handleNextDay() {
        JOptionPane.showMessageDialog(mainPanel, "Kamu tidur hari ini... zzz...");
        NotaManager.toNextDay();
        String nextDay = NotaManager.fmt.format(cal.getTime());
        setToday(nextDay);
        dateLabel.setText("Hari ini:" + nextDay);

    }

    // Set tanggal untuk hari ini.
    private static String today() {
        String thisDay = NotaManager.fmt.format(cal.getTime());
        return thisDay;
    }

    // Getter.
    public String getToday() {
        return today;
    }

    // Setter.
    public void setToday(String today) {
        this.today = today;
    } 
}
