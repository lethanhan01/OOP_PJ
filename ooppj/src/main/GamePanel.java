package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	// srceen
	final int originalTilesize = 16; // 16x16 pixels for all Pc, NPc, map tit, ...
	final int scale = 3; // Tilesizexscale cho phu hop voi man hinh.

	public final int tilesize = originalTilesize*scale;
	public final int maxScrCol = 16;
	public final int maxScrRow = 12;
	public final int ScrWidth = tilesize*maxScrCol; //48 x n, n=16
	public final int ScrHeight = tilesize*maxScrRow;//48 x m, n=12
	
	//WORLD SETTING
	
	public int maxWorldCol = 50 ;
	public int maxWorldRow = 50 ;
	public final int WldWidth = tilesize*maxWorldCol;
	public final int WldHeight = tilesize*maxWorldRow;
	public final int maxMap = 10;
	public final int currentMap = 0;
	
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
    public	KeyHandler keyh = new KeyHandler(this);
    public EventHandler eHandler = new EventHandler(this);
	
	Thread ingame; //1 phan cua gameloop
	public CollisionCheck cCheck = new CollisionCheck(this);// khoi tao check coll
	public AssetSetter aSetter = new AssetSetter(this);// khoi tao dat vat pham
	
	public UI ui = new UI(this);
	
	public Player player = new Player(this,keyh);// tao player
	public SuperObject obj[] = new SuperObject[10]; // tao ra 10 slot vat pham
	public Entity NPC[] = new Entity[10];
	
	//GAME STATE
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogState = 3;
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(ScrWidth,ScrHeight));
		this.setBackground(Color.blue);
		this.setDoubleBuffered(true);
		
		this.addKeyListener(keyh);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		aSetter.setObject();
		aSetter.setNPC();
		gameState = titleState;
	}
	
	public void startgameThread() {
		ingame = new Thread(this);
		ingame.start();
	}

	public void run() {
		
		double drawInterval = 1000000000/FPS; // dv = ns
		double delta = 0;
		long lasttime = System.nanoTime();
		long currentTime;
		
		while (ingame != null) {
			
			currentTime = System.nanoTime();
			delta += (currentTime - lasttime)/drawInterval;
			lasttime = currentTime;
			
			if (delta >= 1) {
				update();
				repaint();
				delta --;
			}
		}
	}
	
	public void update() {
		if (gameState == playState)
			player.update();
			
		for ( int i = 0; i < NPC.length; i++) {
			if ( NPC[i]  != null ) {
				NPC[i].update();
			}
		}
		    
		if (gameState == dialogState) {
			
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 =(Graphics2D)g;
		
		if (gameState == titleState) {
			ui.draw(g2);
		}else {
			tileM.draw(g2);
			
			for ( int i = 0; i < obj.length; i++) {
				if ( obj[i]  != null ) {
					obj[i].draw(g2, this);
				}
			}
			
			for ( int i = 0; i < NPC.length; i++) {
				if ( NPC[i]  != null ) {
					NPC[i].draw(g2);
				}
			}
		
			player.draw(g2);
			
			ui.draw(g2);
			g2.dispose();// free g2
		}
	}
}
