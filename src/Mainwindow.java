import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;


import javax.swing.JMenuItem;
import javax.swing.JLabel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Mainwindow {
	private JFrame frmGame;
	private GameEvent event;
	private final int DISPLAYSPEED = 50;		//0.05s

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mainwindow window = new Mainwindow();
					window.frmGame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Mainwindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		event = new GameEvent(1000,500);
		frmGame = new JFrame();
		frmGame.setResizable(false);
		final GameMap panel = new GameMap(event);
		final JLabel infoLabel = new JLabel("\u4FE1\u606F");
		frmGame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				 infoLabel.setText("按下："+e.getKeyChar());
				 if(event.gamestate==Gamestate.running)
				 {
					 switch(e.getKeyCode())
					 {
					 case KeyEvent.VK_A:event.move(event.lead,-9,0);break;
					 case KeyEvent.VK_D:event.move(event.lead,9,0);break;
					 case KeyEvent.VK_W:event.jump(event.lead);break;
					 case KeyEvent.VK_S:break;
					 case KeyEvent.VK_SPACE:event.shoot(event.lead);break;
					 default:break;
					 }
				 }
				 else
				 {
					 switch(e.getKeyCode())
					 {
					 case KeyEvent.VK_W:event.menuchoice.move(-1);break;
					 case KeyEvent.VK_S:event.menuchoice.move(1);break;
					 case KeyEvent.VK_SPACE:event.click();break;
					 default:break;
					 }
					 panel.repaint();
				 }
			}
			@Override
			public void keyReleased(KeyEvent e) {
				infoLabel.setText("松开："+e.getKeyChar());
				if(event.gamestate==Gamestate.running)
				{
					switch(e.getKeyCode())
					 {
					 case KeyEvent.VK_A:if(event.lead.v1<0)event.stop(event.lead);break;
					 case KeyEvent.VK_D:if(event.lead.v1>0)event.stop(event.lead);break;
					 default:break;
					 }
				}
				 
			}
		});
		frmGame.setTitle("Game1");
		frmGame.setBounds(100, 100, 721, 601);
		frmGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmGame.setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u9009\u9879");
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("\u9000\u51FA");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmGame.dispatchEvent(new WindowEvent(frmGame,WindowEvent.WINDOW_CLOSING) );
			}
		});
		
		JMenuItem menuItem_1 = new JMenuItem("\u91CD\u65B0\u5F00\u59CB");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				event.gamestate=Gamestate.basicmenu;
				panel.repaint();
			}
		});
		menu.add(menuItem_1);
		menu.add(menuItem);
		frmGame.getContentPane().setLayout(null);
		
		panel.setBounds(10, 10, 700, 500);
		frmGame.getContentPane().add(panel);
		
		infoLabel.setBounds(10, 517, 373, 15);
		frmGame.getContentPane().add(infoLabel);
		Thread gameth = new Thread()
		{
			@Override
            public void run() {
                while(event!=null ){
                	try {
                        Thread.sleep(DISPLAYSPEED); 		//延时
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                	if(event.gamestate==Gamestate.running)
                	{
	                	event.display();
	                }
                	else if(event.gamestate == Gamestate.exit)
                	{
                		frmGame.dispatchEvent(new WindowEvent(frmGame,WindowEvent.WINDOW_CLOSING) );
                		break;
                	}
                	panel.repaint();
                }
            }
		};
		gameth.start();
	}
}
