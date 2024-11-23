package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import engine.Game;
import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.world.Champion;
import model.world.Cover;
import model.world.Direction;

public class BoardPanel extends JPanel implements ActionListener {
	private MainFrame mainView;
	private JButton[][]buttons;
	private JButton moveUp;
	private JButton moveDown;
	private JButton moveRight;
	private JButton moveLeft;
	private JButton attackUp;
	private JButton attackDown;
	private JButton attackRight;
	private JButton attackLeft;
	private JButton castAbility;
	private JButton endTurn;
	private JComboBox<String> Abilities;
	private JPanel p1;
	private JPanel p2;
	private JPanel p3;
	private JPanel p4;
	private Direction direction;
	private JTextArea currPlayerChamp;
	private JLabel playername;
	private JComboBox<String> abilitiescombo;
	private JTextField currX;
	private JTextField currY;
	private JLabel targetY;
	private JLabel targetX;
	
	
	public BoardPanel(MainFrame mainView) {
		this.mainView = mainView;
		this.setLayout(null);
		p1= new JPanel();
		p1.setLayout(new GridLayout(5, 5));//board
		p1.setBounds(81,100,500,600);
		this.add(p1);
		buttons = new JButton[5][5];
		
		for(int i = 0; i < buttons.length; i++) {
			for(int j = 0; j < buttons.length; j++) {
				buttons[i][j] = new JButton();
				buttons[i][j].addActionListener(this);
				p1.add(buttons[i][j]);
			}
		}
		p2= new JPanel();
		p2.setLayout(null);//board
		p2.setBounds(615,485,600,250);
		this.add(p2);
		
		p2.setOpaque(false);
		p2.setBackground(new Color(0,0,0,0));
		
		
		
		playername =new JLabel("First Player: "+ mainView.getGame().getFirstPlayer().getName() + "        Second Player: "+ mainView.getGame().getSecondPlayer().getName());
		playername.setBounds(83,25,500,40);
		playername.setFont(new Font ("Stencil", Font.BOLD, 20));
		playername.setForeground(Color.WHITE);
				this.add(playername); 
				
			/*	targetX= new JLabel("Target X");
				targetX.setBounds(35,55,100,40);
				targetX.setFont(new Font ("Stencil", Font.BOLD, 20));
		targetX.setForeground(Color.BLACK);
				p3.add(targetX);*/
				
				/*targetY= new JLabel("Target Y");
				targetY.setBounds(35,55,100,40);
				targetY.setFont(new Font ("Stencil", Font.BOLD, 20));
		targetY.setForeground(Color.BLACK);
				p3.add(targetY);*/
				
				
				moveUp= new JButton("Move Down");
				moveUp.setBounds(70, 130, 100, 40);
				moveUp.addActionListener(this);
				p2.add(moveUp);
				
				moveDown= new JButton("Move Up");
				moveDown.setBounds(70, 30, 100, 40);
				moveDown.addActionListener(this);
				p2.add(moveDown);
				
				moveRight= new JButton("Move Right");
				moveRight.setBounds(120, 80, 100, 40);
				moveRight.addActionListener(this);
				p2.add(moveRight);
				
				moveLeft= new JButton("Move Left");
				moveLeft.setBounds(20, 80, 100, 40);
				moveLeft.addActionListener(this);
				p2.add(moveLeft);
				
				attackUp= new JButton("Attack Down");
				attackUp.setBounds(337,130, 100, 40);
				attackUp.addActionListener(this);
				p2.add(attackUp);
				
				attackDown= new JButton("Attack Up");
				attackDown.setBounds(337, 30, 100, 40);
				attackDown.addActionListener(this);
				p2.add(attackDown);
				
				attackRight= new JButton("Attack Right");
				attackRight.setBounds(387, 80, 100, 40);
				attackRight.addActionListener(this);
				p2.add(attackRight);
				
				attackLeft= new JButton("Attack Left");
				attackLeft.setBounds(287, 80, 100, 40);
				attackLeft.addActionListener(this);
				p2.add(attackLeft); 
				
				
				castAbility = new JButton("castAbility");
				castAbility.setBounds(20, 200, 250, 25);
				castAbility.addActionListener(this);
				p2.add(castAbility);
				
				
				endTurn = new JButton("endTurn");
				endTurn.setBounds(270,200 , 250, 25);
				endTurn.addActionListener(this);
				p2.add(endTurn);
				endTurn.setForeground(Color.BLACK);
				endTurn.setBackground(Color.RED);
				
				p3= new JPanel();
				p3.setLayout(null);
				p3.setBounds(650,200,310,240);
				this.add(p3);
				
				abilitiescombo= new JComboBox<String>();
				abilitiescombo.setLayout(null);
				abilitiescombo.setBounds(122, 160, 100, 30);
				p3.add(abilitiescombo);
				
				currX= new JTextField();
				currX.setBounds(200, 55, 120, 40);
				p3.add(currX);
				
				currY= new JTextField();
				currY.setBounds(35, 55, 120, 40);
				p3.add(currY);
				
				p4= new JPanel();
				p4.setLayout(null);
				p4.setBounds(1000,100,290,200);
				this.add(p4);
				
				currPlayerChamp= new JTextArea();
				currPlayerChamp.setEditable(false);
				JScrollPane sc= new JScrollPane(currPlayerChamp);
						sc.setBounds(0, 0, 350, 200);
						p4.add(sc);
	


						
						updateBoard();
						currPlayerChamp.setText(mainView.showChamp());
						loadAbilities();
						}

	private void updateBoard() {
		// TODO Auto-generated method stub
		for(int i = 0; i < 5; i++) {
			for(int j=0; j<5;j++) {
				if(mainView.getGame().getBoard()[i][j] instanceof Cover)
				{
					Cover co = (Cover)mainView.getGame().getBoard()[i][j];
					buttons[i][j].setBackground(Color.BLACK);
					buttons[i][j].setForeground(Color.YELLOW);
					buttons[i][j].setText(co.getCurrentHP()+" ");
				}
				else if(mainView.getGame().getBoard()[i][j] instanceof Champion)
				{
					Champion c = (Champion)mainView.getGame().getBoard()[i][j];
					if(c==mainView.getGame().getCurrentChampion())
					{
					buttons[i][j].setBackground(Color.CYAN);
					buttons[i][j].setForeground(Color.BLACK);
				}
					else if(mainView.getGame().getFirstPlayer().getTeam().contains(c))
					{
						buttons[i][j].setBackground(Color.DARK_GRAY);
						buttons[i][j].setForeground(Color.YELLOW);
					}
					else
					{
						buttons[i][j].setBackground(Color.LIGHT_GRAY);
						buttons[i][j].setForeground(Color.BLUE);
					}
					buttons[i][j].setText(c.getName() + "-" + c.getCurrentHP()+ "");
				}
				else
				{
					buttons[i][j].setBackground(Color.WHITE);
					buttons[i][j].setText("");
				}
			}
		}
	
	}
	
	
/*public void actionPerformed(ActionEvent e) {
	for(int i = 0; i < buttons.length; i++) {
	for(int j=0; j<buttons.length;j++) {
		if(e.getSource()==buttons[i][j]) {
			if(mainView.getGame().getBoard()[i][j] instanceof Champion)
			{
				Champion c= (Champion)mainView.getGame().getBoard()[i][j];
				String s="Champion info: " + "\n" +"Name: " + c.getName() + "\n"
						+ "\n"+ "AttackDamage: "+ c.getAttackDamage()
						+ "\n"+ "Range: "+ c.getAttackRange()
						+ "\n"+ "HealthPoints: "+ c.getCurrentHP()
						+ "\n"+ "Mana: "+ c.getMana()
						+ "\n"+ "Action Points: "+ c.getCurrentActionPoints();
			
		JOptionPane.showMessageDialog(this, s, "info" , JOptionPane.INFORMATION_MESSAGE);
	}
		}
 }
 }
	
 }
 }*/

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < buttons.length; i++) {
		for(int j=0; j<buttons.length;j++) {
			if(e.getSource()==buttons[i][j]) {
				if(mainView.getGame().getBoard()[i][j] instanceof Champion)
				{
					Champion c= (Champion)mainView.getGame().getBoard()[i][j];
					String s="Champion info: " + "\n" +"Name: " + c.getName() + "\n"
							+ "\n"+ "AttackDamage: "+ c.getAttackDamage()
							+ "\n"+ "Range: "+ c.getAttackRange()
							+ "\n"+ "HealthPoints: "+ c.getCurrentHP()
							+ "\n"+ "Mana: "+ c.getMana()
							+ "\n"+ "Action Points: "+ c.getCurrentActionPoints();
				
			JOptionPane.showMessageDialog(this, s, "info" , JOptionPane.INFORMATION_MESSAGE);
		}
			}
	}
	}
		
		if(e.getSource()== moveUp || e.getSource()== attackUp)
		{
			direction=Direction.UP;
		}
		if(e.getSource()== moveDown || e.getSource()== attackDown)
		{
			direction=Direction.DOWN;
		}
		if(e.getSource()== moveLeft || e.getSource()== attackLeft)
		{
			direction=Direction.LEFT;
		}
		if(e.getSource()== moveRight || e.getSource()==attackRight)
		{
			direction=Direction.RIGHT;
		}
		if(e.getSource()== moveUp || e.getSource()==moveDown || e.getSource()==moveRight || e.getSource()==moveLeft)
		{
			try {
				mainView.getGame().move(direction);
				updateBoard();
				currPlayerChamp.setText(mainView.showChamp());
			}
				catch (NotEnoughResourcesException | UnallowedMovementException e2 ) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(this, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
		}
		if(mainView.getGame().checkGameOver()!= null)
		{
			JOptionPane.showMessageDialog(this, "winner: " + mainView.getGame().checkGameOver().getName(), "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
		mainView.dispose();
		}
		if(e.getSource()== attackUp || e.getSource()==attackDown || e.getSource()==attackRight || e.getSource()==attackLeft)
		{
			try {
				mainView.getGame().attack(direction);
				updateBoard();
				currPlayerChamp.setText(mainView.showChamp());
			} 
			catch (NotEnoughResourcesException | ChampionDisarmedException |InvalidTargetException e2 ) 
			{
				// TODO: handle exception
				JOptionPane.showMessageDialog(this, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			}
		if(mainView.getGame().checkGameOver()!= null)
		{
			JOptionPane.showMessageDialog(this, "winner: " + mainView.getGame().checkGameOver().getName(), "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
		mainView.dispose();
		}
		if(e.getSource()== castAbility)
		{
			
			int i = abilitiescombo.getSelectedIndex();
			Ability ab = mainView.getGame().getCurrentChampion().getAbilities().get(i);
			try {if(ab.getCastArea()== AreaOfEffect.DIRECTIONAL)
			{
				mainView.getGame().castAbility(ab, direction);
			}else if(ab.getCastArea()== AreaOfEffect.SINGLETARGET)
			{
				if(currX.getText().equals("")||currY.getText().equals(""))
				{
					JOptionPane.showMessageDialog(this, "choose target position","Error", JOptionPane.ERROR_MESSAGE);
				return;
				}
				int x =Integer.parseInt( currX.getText());
				int y = Integer.parseInt( currY.getText());
					mainView.getGame().castAbility(ab, x, y);
			}
			else{
				mainView.getGame().castAbility(ab);
			}
			updateBoard();
			currPlayerChamp.setText(mainView.showChamp());
			} catch (NotEnoughResourcesException| AbilityUseException|NumberFormatException|CloneNotSupportedException | InvalidTargetException e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(this, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		if(mainView.getGame().checkGameOver()!= null)
		{
			JOptionPane.showMessageDialog(this, "winner: " + mainView.getGame().checkGameOver().getName(), "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
		mainView.dispose();
		}
		
		if(e.getSource()== endTurn)
		{
			mainView.getGame().endTurn();
			updateBoard();
			currPlayerChamp.setText(mainView.showChamp());
			loadAbilities();
		}
		this.validate();
		this.repaint();
		
		
		}

	
		public void loadAbilities() {
		// TODO Auto-generated method stub
		abilitiescombo.removeAll();
		String [] r= mainView.getabilitiesname();
		int i=0;
		while(i<r.length)
		{
			abilitiescombo.addItem(r[i]);
			i++;
		}
			
		/*for(int i = 0; i < mainView.getGame().getCurrentChampion().getAbilities().size(); i++) {
			abilitiescombo.addItem(mainView.getGame().getCurrentChampion().getAbilities().get(i).getName() + "\n" + 
		mainView.getGame().getCurrentChampion().getAbilities().get(i).getCurrentCooldown());
		}*/
		
	}

}

