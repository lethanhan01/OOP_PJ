package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {
	GamePanel gp;
	public int worldX, worldY;
	public int speed;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public int solidAreaDefaultX; 
	public int solidAreaDefaultY;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public boolean collisionOn = false;
	
	public int actionlockCount = 0;
	
	String Dialog[] = new String[20];
	int DialogIndex = 0;
	
	public int maxLife;
	public int Life;
	
	public Entity(GamePanel gp) { //  lưu gp giúp Entity có thể truy cập vào các thành phần
		                           // của GamePanel khi cần.
		this.gp = gp;
	}
	
	public void setAction() {}
	public void speak() {
		if (Dialog[DialogIndex] == null) {
			DialogIndex = 0;
		}
		gp.ui.currentDialog = Dialog[DialogIndex];
		DialogIndex ++;
		
		switch (gp.player.direction) {
		case "up":
			direction = "down";
			break;
		case "down":
			direction = "up";
			break;
		case "right":
			direction = "left";
			break;
		case "left":
			direction = "right";
			break;
		}
	}
	public void update() {
		
		setAction();
		
		collisionOn = false;
		gp.cCheck.checkTile(this);
		gp.cCheck.checkObject(this, false);
		gp.cCheck.checkPlayer(this);
		
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
	
	public void draw(Graphics2D g2) {
		int screenX = worldX - gp.player.worldX +gp.player.screenX;
		int screenY = worldY - gp.player.worldY +gp.player.screenY;
		
		if (worldX + gp.tilesize > gp.player.worldX - gp.player.screenX &&
			    worldX - gp.tilesize < gp.player.worldX + gp.player.screenX &&
			    worldY + gp.tilesize > gp.player.worldY - gp.player.screenY &&
			    worldY - gp.tilesize < gp.player.worldY + gp.player.screenY) {
			
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
	
	public BufferedImage setup(String ImagePath) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage Image = null;
		try {
			Image = ImageIO.read(getClass().getResourceAsStream(ImagePath +".png"));
			Image = uTool.scaleImage(Image, gp.tilesize, gp.tilesize);
		}catch(IOException e) {
			e.getStackTrace();
		}
		return Image;
	}
}
