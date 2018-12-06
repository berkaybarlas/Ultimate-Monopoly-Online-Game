

import com.nullPointer.Domain.Model.Player;
import com.nullPointer.Utils.ColorSet;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.math.BigDecimal;

public class test extends JFrame {

    private static final Object Player = new Object() {
        int a = 5;
    };
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

    static final String[] descs = {"Java T-shirt",
            "Java Mug",
            "Duke Juggling Dolls",
            "Java Pin",
            "Java Key Chain"};
    static final Player player = new Player("a");

    static final String dataFile = "invoicedata";

    static final BigDecimal[] prices = {
            new BigDecimal("19.99"),
            new BigDecimal("9.99"),
            new BigDecimal("15.99"),
            new BigDecimal("3.99"),
            new BigDecimal("4.99")};
    static final int[] units = {12, 8, 13, 29, 50};

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        {
            ColorSet colorSet = new ColorSet();

            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    test frame = new test();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
            });


            ObjectOutputStream out = null;
            try {
                out = new ObjectOutputStream(new
                        BufferedOutputStream(new FileOutputStream(dataFile)));

                out.writeObject(player);
            } finally {
                out.close();
            }

            ObjectInputStream in = null;
            try {
                in = new ObjectInputStream(new
                        BufferedInputStream(new FileInputStream(dataFile)));

                Player readPlayer;

                try {
                    while (true) {
                        readPlayer = (Player) in.readObject();
                        System.out.println(readPlayer.toString());
                    }
                } catch (EOFException e) {
                }

            } finally {
                in.close();
            }
        }
    }
}