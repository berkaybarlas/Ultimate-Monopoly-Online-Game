package com.nullPointer.UI;
import com.nullPointer.Controller.CommunicationController;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MessageBoxDisplay extends JPanel{
	private JScrollPane scrollPane;
	private JTextField textEnter;
	private JButton submit;
	private JPanel panel;
	private CommunicationController communicationController = CommunicationController.getInstance();
	public MessageBoxDisplay(){
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
		enterPane.add(submit);
		enterPane.add(textEnter);

		submit.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				//client sends textEnter.getText()to Server
				//now client waits for the message from server
				//then when message comes, it is written to the label and
				//added to the scrollable panel
                communicationController.sendClientMessage("message/" + textEnter.getText());

				scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				panel.validate();

			} 
		} );
		this.setVisible(true);
	}

	public void addMessage(String msg) {
        Label message=new Label();
        message.setText(msg);
        panel.add(message);
    }
}
