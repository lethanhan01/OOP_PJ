package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import object.SuperObject;
import object.KEY;
import object.HEART;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font Arial_40, arial_80B, maruMonica; 
	BufferedImage hf, hl, h;
	public boolean messageOn = false;
	public boolean gameFinished = false;
	public String message = "";
	public int messageCount = 0;
	public String currentDialog = "";
	public int commandNum = 0;

	
	public UI(GamePanel gp) {
		this.gp = gp;
		Arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.PLAIN, 80);
		try {
			InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		SuperObject heart = new HEART(gp);
		hf = heart.image1;
		hl = heart.image2;
		h = heart.image3;
	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
		
	}
	public void draw(Graphics2D g2) {
		this.g2 =g2;
		
		g2.setFont(maruMonica);
		g2.setColor(Color.red);
		
		if(gp.gameState == gp.titleState) {
			drawTitleScr();
		}
		
		if(gp.gameState == gp.playState) {
			drawPlayerLife();
		}
		
		if(gp.gameState == gp.pauseState) {
			drawPlayerLife();
			drawPauseScr();
			drawMenu();
		}
		if(gp.gameState == gp.dialogState) {
			drawPlayerLife();
			drawDialogScr();
		}
	}
	
	public void drawTitleScr() {
		//SET BG COLOR
		g2.setColor(new Color(0,0,0));
		g2.fillRect(0, 0, gp.ScrWidth, gp.ScrHeight);
		// SET TITLE
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
		String text  = "Test";
		int x = getXforcenterText(text);
		int y = gp.tilesize*3;
		
		// SET SHADOW
		g2.setColor(Color.red);
		g2.drawString(text, x+5, y+5);
		
		// MAIN COLOR
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		// IMAGE
		
		x = gp.ScrWidth/2 - gp.tilesize;
		y += gp.tilesize*2;
		g2.drawImage(gp.NPC[1].down1, x, y, gp.tilesize*2, gp.tilesize*2, null);
		
		//MENU
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
		
		text  = "NEW GAME";
		x = getXforcenterText(text);
		y += gp.tilesize*4;
		g2.drawString(text, x, y);
		if (commandNum == 0) {
			g2.drawString(">", x - gp.tilesize, y);
		}

		text  = "LOAD GAME";
		x = getXforcenterText(text);
		y += gp.tilesize;
		g2.drawString(text, x, y);
		if (commandNum == 1) {
			g2.drawString(">", x - gp.tilesize, y);
		}

		text  = "EXIT";
		x = getXforcenterText(text);
		y += gp.tilesize;
		g2.drawString(text, x, y);
		if (commandNum == 2) {
			g2.drawString(">", x - gp.tilesize, y);
		}
		
	}
	
	public void drawMenu() {
		
	}
	
	public void drawPauseScr() {
		String text = "PAUSE";
		int x = getXforcenterText(text) ;
		int y = gp.ScrHeight/2;
		
		g2.drawString(text, x, y);
	}
	
	public void drawDialogScr() {
		int x = gp.tilesize*2;
		int y = gp.tilesize/2;
		int width = gp.ScrWidth - (4*gp.tilesize);
		int height = gp.tilesize*4;
		
		subWindow (x, y, width, height);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
		x += gp.tilesize;
		y += gp.tilesize;
		
		/* for (String line : currentDialog.split("\n")) {
			g2.drawString(line, x, y);
			y += 40;
		} */
		g2.drawString(currentDialog, x, y);
	}
	
	public void drawPlayerLife() {
		int x = gp.tilesize/2;
		int y = gp.tilesize/2;
		int i = 0;
		while (i < gp.player.maxLife/2) {
			g2.drawImage(h, x, y, null);
			i++;
			x += gp.tilesize;
		}
		x = gp.tilesize/2;
		i = 0;
		while (i < gp.player.Life) {
			g2.drawImage(hl, x, y, null);
			i++;
			if (i < gp.player.Life) {
				g2.drawImage(hf, x, y, null);
				i++;
			}
			x += gp.tilesize;
		}
		
	}
	
	public void subWindow(int x, int y, int width, int height) {
		Color c = new Color(0, 0, 0, 125); // x=red, y=green, z=blue, t = do trong suot
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width, height-10, 25, 25);		
	}
	public int getXforcenterText(String text) {
		int lenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // lay do dai doan text 
		int x = gp.ScrWidth/2  - lenght/2;
		return x;
	}
}
