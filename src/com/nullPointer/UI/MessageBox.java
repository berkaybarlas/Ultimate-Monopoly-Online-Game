package com.nullPointer.UI;

import com.nullPointer.Domain.Controller.CommunicationController;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Observer;
import com.nullPointer.Domain.Server.ServerInfo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MessageBox extends JPanel implements Observer {
    private JScrollPane scrollPane;
    private JTextField textEnter;
    private JButton submit;
    private JPanel panel;
    private CommunicationController communicationController = CommunicationController.getInstance();
    private GameEngine gameEngine = GameEngine.getInstance();

    public MessageBox(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setOpaque(false);
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setBackground(Color.WHITE);
        panel.validate();
        scrollPane = new JScrollPane(panel);
        // scrollPane.setOpaque(false);
        scrollPane.setPreferredSize(new Dimension(width, height - 100));
        //scrollPane.setBounds(0, 0, 500, 200);

        JPanel contentPane = new JPanel();
        contentPane.setOpaque(false);
        contentPane.setPreferredSize(new Dimension(width, height - 100));
        contentPane.add(scrollPane);
        this.add(contentPane);

        JPanel enterPane = new JPanel();
        enterPane.setPreferredSize(new Dimension(width, 50));
        enterPane.setOpaque(false);


        textEnter = new JTextField();
        textEnter.setPreferredSize(new Dimension(width - 110, 30));
        //textEnter.setBounds(0, 0, 400, 30);
        ActionListener  actionListener = e -> {
            if (textEnter.getText() != "") {
                String message = textEnter.getText();
                if(message.length()>100)
                    message = message.substring(0,100);
                communicationController.sendClientMessage("message/" + messageWithClient(message));
                textEnter.setText("");
            }
        };
        textEnter.addActionListener(actionListener);
        enterPane.add(textEnter);

        submit = new JButton();
        //submit.setBounds(401, 0, 100, 30);
        submit.setPreferredSize(new Dimension(100, 30));
        submit.setMaximumSize(new Dimension(100, 30));
        submit.setMinimumSize(new Dimension(100, 30));
        submit.setText("Submit");
        submit.addActionListener(actionListener);
        enterPane.add(submit);

        this.add(enterPane);
        this.setVisible(true);
        gameEngine.subscribe(this);
    }

    public String messageWithClient(String message) {
        String client = ServerInfo.getInstance().getClientID();
        return "Computer " + (ServerInfo.getInstance().getClientList().indexOf(client) + 1) + " : " + message;

    }

    public void addMessage(String msg) {
        Label message = new Label();
        message.setText(" " + msg);
        panel.add(message);
        panel.validate();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    @Override
    public void onEvent(String message) {
        if (message.contains("message/")) {
            addMessage(message.substring("message/".length()));
        }
    }
}
