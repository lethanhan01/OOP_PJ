package main;

import java.awt.Rectangle;

public class EventHandler {
	GamePanel gp;
	Rectangle eventRect;
	int eventRectDefaultX, eventRectDefaultY;
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new Rectangle();
		eventRect.x = 23;
		eventRect.y = 23;
		eventRect.height = 2;
		eventRect.width = 2;
		eventRectDefaultX = eventRect.x;
		eventRectDefaultY = eventRect.y;
	}
	
	public void checkEvent() {
	    if (hit(25, 21, "right") == true) {
	    	takeDmg(gp.dialogState);
	    }
	}

	public boolean hit(int eventCol, int eventRow, String reqDirection) { // toa do x,y, kieu va cham

	    boolean hit = false;

	    gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
	    gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
	    eventRect.x = eventCol * gp.tilesize + eventRect.x;
	    eventRect.y = eventRow * gp.tilesize + eventRect.y;

	    if (gp.player.solidArea.intersects(eventRect)) {
	        if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
	            hit = true;
	        }
	    }

	    gp.player.solidArea.x = gp.player.solidAreaDefaultX;
	    gp.player.solidArea.y = gp.player.solidAreaDefaultY;
	    eventRect.x = eventRectDefaultX;
	    eventRect.y = eventRectDefaultY;

	    return hit;
	}
	public void takeDmg(int gameState) {
		gp.gameState = gameState;
		gp.player.Life --;
	}
}
