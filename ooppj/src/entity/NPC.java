package entity;

import java.util.Random;

import main.GamePanel;


public class NPC extends Entity {
	public NPC(GamePanel gp) {
		super (gp);
		
		direction = "down";
		speed = 1;
		getNPCImage();
		setDialog();
	}
	
	public void getNPCImage() {
		up1 = setup("/npc/oldman_up_1");
		up2 = setup("/npc/oldman_up_2");
		down1 = setup("/npc/oldman_down_1");
		down2 = setup("/npc/oldman_down_2");
		left1 = setup("/npc/oldman_left_1");
		left2 = setup("/npc/oldman_left_2");
		right1 = setup("/npc/oldman_right_1");
		right2 = setup("/npc/oldman_right_2");
	}
	public void setDialog() {
		Dialog[0] = "Hello";
		Dialog[1] = "This is a test for project";
		Dialog[2] = "a test";
		Dialog[3] = "for project";
	}
	
	public void setAction() {
		
		if (gp.gameState == gp.playState) {
			actionlockCount ++;
			if (actionlockCount == 120) {
			Random random = new Random();
			int i = random.nextInt(100)+1; // i nhan gia tri tu 1- 100
			
			if ( i <= 25)
				direction = "up";
			if ( i > 25 && i <= 50)
				direction = "down";
			if ( i > 50 && i <= 75)
				direction = "left";
			if ( i > 75 && i <= 100)
				direction = "right";
			actionlockCount = 0;
			}
		}
	}
	
	public void speak() {
		super.speak();
	}
}
