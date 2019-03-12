import javax.swing.*;
import java.awt.*;

public class rrr extends JFrame{
    private JPanel panel1;
    private JButton ApplyMonstersBtn;
    private JButton ApplyPlayersBtn;
    private JTextArea textTextArea;
    private JTextArea textArea1;

    public rrr() throws HeadlessException {
        setContentPane(panel1);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new rrr();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
