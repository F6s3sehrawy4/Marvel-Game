package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartPanel extends JPanel implements ActionListener {
	private MainFrame mainView;
	private JLabel backphoto;
	
	private JButton start;
	public StartPanel(MainFrame mainView) {
		this.mainView = mainView;
	 ImageIcon vid= new ImageIcon("newvid.gif");
		backphoto= new JLabel();
		backphoto.setSize(2000,700);
		backphoto.setIcon(vid);
	this.add(backphoto);
		this.setLayout(null);
		start= new JButton("Start");
		start.setBounds(1200, 600, 200, 50);
		start.addActionListener(this);
		this.add(start);
   StartPanel.audio();
    
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == start) {
			mainView.switchToPlayerNamePanel();
		}
	}
   	public static void audio() {
		try { 
			File file= new File("newaudio.wav");
			Clip clip= AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(file));
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
		}
		catch(Exception e) {
			System.err.println("newaudio.wav");
		}
	}
	
}
