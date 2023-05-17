package assignments.assignment4.gui;

import assignments.assignment3.nota.NotaManager;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static assignments.assignment3.nota.NotaManager.cal;
import static assignments.assignment3.nota.NotaManager.toNextDay;

public class HomeGUI extends JPanel {
    public static final String KEY = "HOME";
    private JLabel titleLabel;
    private JLabel dateLabel;
    private JPanel mainPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JButton toNextDayButton;

    private String today;

    public HomeGUI(){
        super(new BorderLayout()); // Setup layout, Feel free to make any changes

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
        JPanel panel1 = new JPanel();
        titleLabel = new JLabel("Selamat datang di CuciCuci System!");
        panel1.add(titleLabel);

        JPanel panel2 = new JPanel();
        loginButton = new JButton("Login");
        panel2.add(loginButton);

        JPanel panel3 = new JPanel();
        registerButton = new JButton("Register");
        panel3.add(registerButton);

        JPanel panel4 = new JPanel();
        toNextDayButton = new JButton("Next Day");
        panel4.add(toNextDayButton);

        JPanel panel5 = new JPanel();
        dateLabel = new JLabel();
        dateLabel.setText("Hari ini:" + today());
        panel5.add(dateLabel);

        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(panel3);
        mainPanel.add(panel4);
        mainPanel.add(panel5);

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
        toNextDay();
        String nextDay = NotaManager.fmt.format(cal.getTime());
        setToday(nextDay);
        dateLabel.setText("Hari ini:" + nextDay);

    }

    private static String today() {
        String thisDay = NotaManager.fmt.format(cal.getTime());
        return thisDay;
    }

    public String getToday() {
        return today;
    }
    public void setToday(String today) {
        this.today = today;
    } 
}
