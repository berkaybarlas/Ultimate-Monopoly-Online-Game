package com.nullPointer.UI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MessageBoxDisplay extends JPanel{
	private JScrollPane scrollPane;
	private JTextField textEnter;
	private JButton submit;
	public MessageBoxDisplay(){
		this.setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		scrollPane = new JScrollPane(panel);
		JPanel contentPane = new JPanel(null);
		contentPane.setPreferredSize(new Dimension(500, 200));
		scrollPane.setBounds(0, 0,500,200);
		this.add(contentPane,BorderLayout.NORTH);
		contentPane.add(scrollPane);

		JPanel enterPane = new JPanel(null);
		enterPane.setPreferredSize(new Dimension(100, 200));
		this.add(enterPane,BorderLayout.SOUTH);

		submit=new JButton();
		submit.setBounds(401,0,100,30);
		submit.setText("Submit");
		textEnter=new JTextField();
		textEnter.setBounds(0,0, 400, 30);
		enterPane.add(submit);
		enterPane.add(textEnter);

		submit.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				//client sends textEnter.getText().toString() to Server
				//now client waits for the message from server
				//then when message comes, it is written to the label and
				//added to the scrollable panel
				Label message=new Label();
				message.setText(textEnter.getText().toString());
				panel.add(message);
				scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				panel.validate();

			} 
		} );

		this.setVisible(true);

	}
}
