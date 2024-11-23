package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PlayerNamePanel extends JPanel implements ActionListener {
	private MainFrame mainView;
	
	private JLabel l1;
	private JLabel l2;
	
	private JTextField name1;
	private JTextField name2;
	
	private JButton next;
	
	public PlayerNamePanel(MainFrame mainView) {
		this.mainView = mainView;
		
		this.setLayout(null);
		
		l1 = new JLabel("Player 1 Name");
		l1.setBounds(30, 100, 220, 25);
		l1.setFont(new Font ("Stencil", Font.BOLD, 25));
		l1.setForeground(Color.WHITE);
		this.add(l1);
		
		name1 = new JTextField();
		name1.setBounds(230, 100, 150, 25);
		this.add(name1);
		
		l2 = new JLabel("Player 2 Name");
		l2.setBounds(400, 100, 220, 25);
		l2.setFont(new Font ("Stencil", Font.BOLD, 25));
		l2.setForeground(Color.WHITE);
		this.add(l2);
		
		name2 = new JTextField();
		name2.setBounds(600, 100, 150, 25);
		this.add(name2);
		
		next = new JButton("Next >>");
		next.setBounds(250, 200, 200, 50);
		next.addActionListener(this);
		this.add(next);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == next) {
			if(name1.getText().equals("")) {
				displayErrorMessage("Player 1 must have a name");
			}
			else if(name2.getText().equals("")) {
				displayErrorMessage("Player 2 must have a name");
			}
			else {
				mainView.switchToNextPanel(name1.getText(), name2.getText());
			}
		}
		
	}
	public void displayErrorMessage(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
