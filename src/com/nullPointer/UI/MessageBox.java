package com.nullPointer.UI;
import com.nullPointer.Controller.CommunicationController;
import com.nullPointer.Model.GameEngine;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MessageBox extends JPanel implements Observer{
	private JScrollPane scrollPane;
	private JTextField textEnter;
	private JButton submit;
	private JPanel panel;
	private CommunicationController communicationController = CommunicationController.getInstance();
	private GameEngine gameEngine = GameEngine.getInstance();

	public MessageBox(){
		this.setLayout(new BorderLayout());

		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		scrollPane = new JScrollPane(panel);
		scrollPane.setBounds(0, 0,500,200);
		JPanel contentPane = new JPanel(null);
		contentPane.setPreferredSize(new Dimension(500, 200));
		contentPane.add(scrollPane);
		this.add(contentPane,BorderLayout.NORTH);

		JPanel enterPane = new JPanel(null);
		enterPane.setPreferredSize(new Dimension(100, 200));
		this.add(enterPane,BorderLayout.SOUTH);

		submit = new JButton();
		submit.setBounds(401,0,100,30);
		submit.setText("Submit");
		textEnter=new JTextField();
		textEnter.setBounds(0,0, 400, 30);
		textEnter.addActionListener(e -> {
			if(textEnter.getText() != "") {
				communicationController.sendClientMessage("message/" + textEnter.getText());
				textEnter.setText("");
			}
		});
		enterPane.add(submit);
		enterPane.add(textEnter);

		//wrong
		communicationController.setMessageBox(this);
		//wrong

		submit.addActionListener(e -> {
			if(textEnter.getText() != "") {
				communicationController.sendClientMessage("message/" + textEnter.getText());
				textEnter.setText("");
			}
        });
		this.setVisible(true);
		gameEngine.subscribe(this);
	}

	public void addMessage(String msg) {
        Label message=new Label();
        message.setText(msg);
        panel.add(message);
        panel.validate();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    @Override
    public void onEvent(String message) {
        if(message.contains("message/")){
            addMessage(message.substring("message/".length()+1));
        }
    }
}
