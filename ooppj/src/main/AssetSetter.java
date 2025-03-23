package main;

import object.CHEST;
import object.DOOR;
import object.KEY;
import entity.NPC;
import object.BOOT;

public class AssetSetter {
	GamePanel gp;
	
	public AssetSetter (GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		
		
	}
	
	public void setNPC() {
		gp.NPC[1] = new NPC(gp);
		gp.NPC[1].worldX = 19 * gp.tilesize;
		gp.NPC[1].worldY = 21 * gp.tilesize;
	}
}
