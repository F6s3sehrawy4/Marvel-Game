package views;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import engine.Game;
import engine.Player;
import model.world.Champion;

public class MainFrame extends JFrame {
	private PlayerNamePanel PlayerNamePanel;
	private BoardPanel boardPanel;
	private StartPanel StartPanel;
	private String name1;
	private String name2;
	private ImageIcon icon = new ImageIcon("20461295.jpg");
	private PlayerSpecs selection;
	private JLabel back;
	private  Game game;
	public MainFrame() {
		
		  back = new JLabel(); back.setSize(1700,1000); back.setIcon(icon);
		  back.setLayout(null);
		  this.add(back);
		  this.setTitle("Marvel Game");
		  
		  StartPanel = new StartPanel(this);
		  back.add(StartPanel);
		  StartPanel.setBounds(0,0,1700,1000);
		  StartPanel.setFocusable(false);
		  StartPanel.setOpaque(false);
		  StartPanel.setBackground(new Color(0,0,0,0));
	
		this.setSize(1700, 1000);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	} 
	
	public void switchToNextPanel(String s1, String s2) {
		name1 = s1;
		name2 = s2;
		try {
			Game.loadAbilities("Abilities.csv");
			Game.loadChampions("Champions.csv");
			this.setSize(1000,600);
			back.remove(PlayerNamePanel);
			
			selection = new PlayerSpecs(this);
			back.add(selection);
			selection.setBounds(0,0,1700,1000);
			selection.setOpaque(false);
			selection.setBackground(new Color(0,0,0,0));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[]args) {
		MainFrame MFrame = new MainFrame();
	}
	
	/*public void switchToStartPanel(String name1, String name2) {
		back.remove(PlayerNamePanel);
		
		boardPanel = new BoardPanel(this);
		boardPanel.setBounds(0,0,1000,700);
		this.setSize(650, 650);
		back.add(boardPanel);
		
		this.validate();
		this.repaint();
	}*/
	

	public String[] getAllChampions() {
		ArrayList<Champion> champs = Game.getAvailableChampions();
		String[]names= new String[champs.size()+1];
		for(int i=0; i<champs.size();i++) {
			names[i]=champs.get(i).getName();
		}
		return names;
		
	}

	public String getInfoOfChamp(int selectedIndex) {
		ArrayList<Champion> champs = game.getAvailableChampions();
		Champion c=champs.get(selectedIndex);
		String s= "Name:" + c.getName() + "\n" + "Maxhp:" + c.getMaxHP()  + 
				"\n" + "Mana:" + c.getMana() + "\n" + "Attack range:" + c.getAttackRange() + "\n" + "Speed:" + c.getSpeed() +
				"\n" + "Max action points per turn:" + c.getMaxActionPointsPerTurn() + "\n" + "current action points:" + c.getCurrentActionPoints();
		s = s + "\n" + "Abilities:";
		for(int i = 0; i < c.getAbilities().size(); i++) {
			s = s +  c.getAbilities().get(i).getName()+"\n";
		}
		
		this.validate();
		this.repaint();
		return s;

	}
	
	public void switchToBoardPanel(int i, int j, int k, int l, int m, int n, int firstLeader, int secondLeader) {
		
	back.remove(selection);
	
	Player p1 = new Player(name1);
	p1.getTeam().add(Game.getAvailableChampions().get(i));
	p1.getTeam().add(Game.getAvailableChampions().get(j));
	p1.getTeam().add(Game.getAvailableChampions().get(k));
	p1.setLeader(Game.getAvailableChampions().get(firstLeader));
	
	Player p2 = new Player(name2);
	p2.getTeam().add(Game.getAvailableChampions().get(l));
	p2.getTeam().add(Game.getAvailableChampions().get(m));
	p2.getTeam().add(Game.getAvailableChampions().get(n));
	p2.setLeader(Game.getAvailableChampions().get(secondLeader));
	
	game = new Game(p1, p2);
	
	boardPanel = new BoardPanel(this);
	boardPanel.setOpaque(false);
	boardPanel.setBackground(new Color(0,0,0,0));
	boardPanel.setBounds(0,0,1700,1000);
	this.setSize(650, 650);
	back.add(boardPanel);
	this.validate();
	this.repaint();
	}
	
	/*public void switchToBoardPanel() {
	back.remove(selection);
		
		boardPanel = new BoardPanel(this);
		boardPanel.setOpaque(false);
		boardPanel.setBackground(new Color(0,0,0,0));
		boardPanel.setBounds(0,0,1000,700);
		this.setSize(650, 650);
		back.add(boardPanel);
		
		this.validate();
		this.repaint();
	}*/

	public  Game getGame() {
		// TODO Auto-generated method stub
		return game;
	}

	public String[] getabilitiesname() {
		// TODO Auto-generated method stub
		String[] r = new String[game.getCurrentChampion().getAbilities().size() + 1];
		int i=0;
		while(i<game.getCurrentChampion().getAbilities().size())
		{
			r[i] = game.getCurrentChampion().getAbilities().get(i).getName();
			i++;
		}
		return r;
	}
public String showChamp() {
	String s="";
		if(game.getFirstPlayer().getTeam().contains(game.getCurrentChampion()))
		{
		s= game.getFirstPlayer().getName()+ "/";	
		}
		else if(game.getFirstPlayer().getTeam().contains(game.getCurrentChampion()))
		{
			s=game.getSecondPlayer().getName() + "/";
			
		}
		Champion c= game.getCurrentChampion();
		 s= "Name:" + c.getName() + "\n" + "Maxhp:" + c.getMaxHP()  + 
				"\n" + "Mana:" + c.getMana() + "\n" + "Attack range:" + c.getAttackRange() + "\n" + "Speed:" + c.getSpeed() +
				"\n" + "Max action points per turn:" + c.getMaxActionPointsPerTurn() + "\n" + "current action points:" + c.getCurrentActionPoints();
		s = s + "\n" + "Abilities:";
		for(int i = 0; i < c.getAbilities().size(); i++) {
			s = s +  c.getAbilities().get(i).getName()+"\n";
			s = s +  c.getAbilities().get(i).getManaCost()+"\n";
			s = s +  c.getAbilities().get(i).getCurrentCooldown()+"\n";
			s = s +  c.getAbilities().get(i).getRequiredActionPoints()+"\n";
			s = s +  c.getAbilities().get(i).getCastArea()+"\n";
		}
		return s;
		}

public void switchToPlayerNamePanel() {
	// TODO Auto-generated method stub
	back.remove(StartPanel);
	
	PlayerNamePanel = new PlayerNamePanel(this);
	PlayerNamePanel.setBounds(0,0,1700,1000);
	PlayerNamePanel.setFocusable(false);
	PlayerNamePanel.setOpaque(false);
	PlayerNamePanel.setBackground(new Color(0,0,0,0));
	back.add(PlayerNamePanel);
	this.validate();
	this.repaint();
	
}
	

}
