

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class test extends JFrame {

    private final CardLayout cl = new CardLayout();
    private final JPanel cards = new JPanel(cl);
    private final Border border = BorderFactory.createEmptyBorder(60, 60, 60, 60);

    public test() {

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JPanel panel1 = new JPanel(new GridBagLayout());
        panel1.setBorder(border);
        panel1.setBackground(Color.RED);
        panel1.add(new JLabel("Card 1"));
        cards.add(panel1, "First Panel");

        JPanel panel2 = new JPanel(new GridBagLayout());
        panel2.setBorder(border);
        panel2.setBackground(Color.GREEN);
        panel2.add(new JLabel("Card 2"));
        cards.add(panel2, "Second Panel");

        JPanel panel3 = new JPanel(new GridBagLayout());
        panel3.setBorder(border);
        panel3.setBackground(Color.BLUE);
        panel3.add(new JLabel("Card 3"));
        cards.add(panel3, "Third Panel");

        JButton controlButton = new JButton("Switch");
        controlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.next(cards);
            }
        });
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(Color.LIGHT_GRAY);
        controlPanel.add(controlButton);

        contentPane.add(cards);
        contentPane.add(controlPanel);

        cl.show(cards, "First Panel");
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                test frame = new test();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}