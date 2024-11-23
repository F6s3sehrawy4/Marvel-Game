package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class PlayerSpecs extends JPanel implements ActionListener, ItemListener {
	private JList<String> list1;
	
	private JButton Start;
	private JComboBox<String> combo;
	private MainFrame mainView;
	private JLabel one;
	private JLabel two;
	private JLabel three;
	private JLabel four;
	private JComboBox c1;
	private JComboBox c2;
	private JComboBox c3;
	private JComboBox c4;
	private JComboBox c5;
	private JComboBox c6;
	private JTextArea displaychampc;
	private JRadioButton r1, r2, r3, r4, r5, r6;
	
	
	public PlayerSpecs(MainFrame mainView) {
		this.mainView = mainView;
		
		this.setLayout(null);

	
		one = new JLabel("First Player");
		one.setBounds(100, 50, 150, 30);
		one.setFont(new Font ("Stencil", Font.BOLD, 20));
		one.setForeground(Color.WHITE);
		this.add(one);
		
		two = new JLabel("Second Player");
		two.setBounds(400, 50, 170, 30);
		two.setFont(new Font ("Stencil", Font.BOLD, 20));
		two.setForeground(Color.WHITE);
		this.add(two);
		
		three= new JLabel("Choose first player leader");
		three.setBounds(100, 130,240,30);
		three.setFont(new Font ("Stencil", Font.BOLD, 15));
		three.setForeground(Color.WHITE);
		this.add(three);
		
		four=new JLabel("Choose second player leader");
		four.setBounds(400,130,260,30);
		four.setFont(new Font ("Stencil", Font.BOLD, 15));
		four.setForeground(Color.WHITE);
		this.add(four);
		
	String []x= mainView.getAllChampions();
	c1 = new JComboBox<String>(x);
	c1.setBounds(100, 170, 150, 30);
	c1.addItemListener(this);
	this.add(c1);
	
	
	c2= new JComboBox<String>(x);
	c2.setBounds(100, 240, 150,30);
	c2.addItemListener(this);
	this.add(c2);
	
	c3= new JComboBox<String>(x);
	c3.setBounds(100, 310, 150,30);
	c3.addItemListener(this);
	this.add(c3);
	
	c4= new JComboBox<String>(x);
	c4.setBounds(400, 170, 150,30);
	c4.addItemListener(this);
	this.add(c4);
	
	c5= new JComboBox<String>(x);
	c5.setBounds(400, 240, 150,30);
	c5.addItemListener(this);
	this.add(c5);
	
	c6= new JComboBox<String>(x);
	c6.setBounds(400, 310, 150,30);
	c6.addItemListener(this);
	this.add(c6);
	
	r1 = new JRadioButton("Leader?");
	r1.setBounds(150, 170, 100, 100);
	r1.addItemListener(this);
	this.add(r1);
	
	r2 = new JRadioButton("Leader?");
	r2.setBounds(450, 170, 100, 100);
	r2.addItemListener(this);
	this.add(r2);
	
	r3 = new JRadioButton("Leader?");
	r3.setBounds(150, 240, 100, 100);
	r3.addItemListener(this);
	this.add(r3);
	
	r4 = new JRadioButton("Leader?");
	r4.setBounds(450, 240, 100, 100);
	r4.addItemListener(this);
	this.add(r4);
	
	r5 = new JRadioButton("Leader?");
	r5.setBounds(150, 310, 100, 100);
	r5.addItemListener(this);
	this.add(r5);
	
	r6 = new JRadioButton("Leader?");
	r6.setBounds(450, 310, 100, 100);
	r6.addItemListener(this);
	this.add(r6);
	
	
	displaychampc=new JTextArea();
	displaychampc.setBounds(400,390,200,250);
	displaychampc.setEditable(false);
	this.add(displaychampc);
	
	Start= new JButton("Start");
	Start.setBounds(30,450,200,50);
	Start.addActionListener(this);
	this.add(Start);
	
	}
	public void itemStateChanged(ItemEvent e) {
		
		if(e.getSource()==c1) 
			displaychampc.setText(mainView.getInfoOfChamp(c1.getSelectedIndex()));
	
		if(e.getSource()==c2) 
			displaychampc.setText(mainView.getInfoOfChamp(c2.getSelectedIndex()));
		
		if(e.getSource()==c3) 
			displaychampc.setText(mainView.getInfoOfChamp(c3.getSelectedIndex()));
		
		if(e.getSource()==c4) 
			displaychampc.setText(mainView.getInfoOfChamp(c4.getSelectedIndex()));
		
		if(e.getSource()==c5) 
			displaychampc.setText(mainView.getInfoOfChamp(c5.getSelectedIndex()));
		
		if(e.getSource()==c5) 
			displaychampc.setText(mainView.getInfoOfChamp(c5.getSelectedIndex()));
		
		if(e.getSource()==c6) 
			displaychampc.setText(mainView.getInfoOfChamp(c6.getSelectedIndex()));
		
		this.validate();
		this.repaint();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		int a=c1.getSelectedIndex();
		int b=c2.getSelectedIndex();
		int c=c3.getSelectedIndex();
		int d=c4.getSelectedIndex();
		int g=c5.getSelectedIndex();
		int f=c6.getSelectedIndex();
		
		
		if(e.getSource() == Start) {
			if(!(r1.isSelected() || r5.isSelected() || r3.isSelected()) ) {
				JOptionPane.showMessageDialog(this, "player 1 must choose a leader", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(!(r2.isSelected() || r4.isSelected() || r6.isSelected()) ) {
				JOptionPane.showMessageDialog(this, "player 2 must choose a leader", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(r1.isSelected() && (r3.isSelected() || r5.isSelected())) {
				JOptionPane.showMessageDialog(this, "player 1 must choose only 1 leader", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(r3.isSelected() && (r1.isSelected() || r5.isSelected())) {
				JOptionPane.showMessageDialog(this, "player 1 must choose only 1 leader", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(r5.isSelected() && (r1.isSelected() || r3.isSelected())) {
				JOptionPane.showMessageDialog(this, "player 1 must choose only 1 leader", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(r4.isSelected() && (r2.isSelected() || r6.isSelected())) {
				JOptionPane.showMessageDialog(this, "player 2 must choose only 1 leader", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(r6.isSelected() && (r4.isSelected() || r2.isSelected())) {
				JOptionPane.showMessageDialog(this, "player 2 must choose only 1 leader", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(r2.isSelected() && (r4.isSelected() || r6.isSelected())) {
				JOptionPane.showMessageDialog(this, "player 2 must choose only 1 leader", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(a==b || a==c || a==d || a==g || a==f) {
				JOptionPane.showMessageDialog(this, "all players must be different", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(b==a || b==c || b==d || b==g || b==f) {
				JOptionPane.showMessageDialog(this, "all players must be different", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(c==a || c==b || c==d || c==g || c==f) {
				JOptionPane.showMessageDialog(this, "all players must be different", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(d==b || d==c || a==d || d==g || d==f) {
				JOptionPane.showMessageDialog(this, "all players must be different", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(g==b || g==c || g==d || a==g || g==f) {
				JOptionPane.showMessageDialog(this, "all players must be different", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(f==b || f==c || f==d || f==g || a==f) {
				JOptionPane.showMessageDialog(this, "all players must be different", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			else {
				int firstLeader = 0;
				int secondLeader = 0;
				if(r1.isSelected())
					firstLeader = a;
				if(r3.isSelected())
					firstLeader = b;
				if(r5.isSelected())
					firstLeader = c;
				if(r2.isSelected())
					secondLeader = d;
				if(r4.isSelected())
					secondLeader = g;
				if(r5.isSelected())
					secondLeader = f;
				
				mainView.switchToBoardPanel(a, b, c, d, g, f, firstLeader, secondLeader);
				//mainView.switchToBoardPanel();
				mainView.revalidate();
				mainView.repaint();
			}
		}
		
		
		
	}	
}

