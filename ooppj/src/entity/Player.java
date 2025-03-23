package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	KeyHandler keyh;
	
	public final int screenX, screenY;
	public int hasKey = 0;
	
	public Player(GamePanel gp, KeyHandler keyh) {
		super(gp); // Đảm bảo rằng player có thể truy cập vào gp và sử dụng các thuộc tính 
		           //hoặc phương thức cần thiết từ GamePanel.
		this.keyh = keyh;
		// set camera di theo nv
		screenX = gp.ScrWidth/2 - (gp.tilesize/2);     
		screenY = gp.ScrHeight/2 - (gp.tilesize/2);
		
		solidArea = new Rectangle(8,16,24,24); 
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		SetDefaultValues();
		getPlayerImage();
	}
	public void SetDefaultValues() {
	
		worldX = gp.tilesize*23; // set up nv luon o giua man hinh
		worldY = gp.tilesize*21;
		
		//worldX = 100; //set up defaul
		//worldY = 100;
		maxLife = 6;
		Life = 6;
		speed = 4;
		direction = "down";
	}
	
	public void getPlayerImage() {
		up1 = setup("/player/u1");
		up2 = setup("/player/u2");
		down1 = setup("/player/d1");
		down2 = setup("/player/d2");
		left1 = setup("/player/l1");
		left2 = setup("/player/l2");
		right1 = setup("/player/r1");
		right2 = setup("/player/r2");
	}
	
	public void update() {
		if (keyh.up == true || keyh.down == true ||
				keyh.right == true || keyh.left == true) {
			if (keyh.up == true) {
				direction = "up";
			}
			else if (keyh.down == true) {
				direction = "down";
			}
			else if (keyh.left == true) {
				direction = "left";	
			}
			else if (keyh.right == true) {
				direction = "right";
			}
			
			collisionOn = false;
			gp.cCheck.checkTile(this);
			
			int ObjIndex = gp.cCheck.checkObject(this, true);
			pickupOb(ObjIndex);
			
			int NPCIndex = gp.cCheck.checkEntity(this, gp.NPC);
			interactNPC(NPCIndex);
			gp.eHandler.checkEvent();
			
			if (collisionOn == false) {
				switch(direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
				
			}
			
			spriteCounter++;
			if (spriteCounter > 15 ) {// cu' n f thi ve lai hoat anh 1 lan ~ 60/n lan ve trong 1 s ~ tang toc do chuyen dong hoat anh
				if (spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
	}
	
	public void pickupOb (int i) {
		if (i != 999) {
		}
	}
	
	public void interactNPC(int i) {
		if (i != 999) {
			if (keyh.enter == true ) {
				gp.gameState = gp.dialogState;
				gp.NPC[i].speak();
			}
		}
		gp.keyh.enter = false;
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch (direction) {
		case "up":
			if (spriteNum == 1 ) {
				image = up1;
			}
			if (spriteNum == 2 ) {
				image = up2;
			}
			break;
		case "down":
			if (spriteNum == 1 ) {
				image = down1;
			}
			if (spriteNum == 2 ) {
				image = down2;
			}
			break;
		case "left":
			if (spriteNum == 1 ) {
				image = left1;
			}
			if (spriteNum == 2 ) {
				image = left2;
			}
			break;
		case "right":
			if (spriteNum == 1 ) {
				image = right1;
			}
			if (spriteNum == 2 ) {
				image = right2;
			}
			break;
		}
		
		g2.drawImage(image, screenX, screenY, gp.tilesize, gp.tilesize, null);
		
	}
}
