package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class DOOR extends SuperObject {
	GamePanel gp;
	public DOOR(GamePanel gp) {
		this.gp = gp;
		Name = "door";
		try {
			image1 = ImageIO.read(getClass().getResourceAsStream("/Ob/door.png"));
			uTool.scaleImage(image1, gp.tilesize, gp.tilesize);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}
}