package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class CHEST extends SuperObject {
	
	GamePanel gp;
	public CHEST(GamePanel gp) {
		this.gp = gp;
		Name = "chest";
		try {
			image1 = ImageIO.read(getClass().getResourceAsStream("/Ob/chest.png"));
			uTool.scaleImage(image1, gp.tilesize, gp.tilesize);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}