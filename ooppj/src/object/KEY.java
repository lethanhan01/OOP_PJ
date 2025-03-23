package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class KEY extends SuperObject {
	
	GamePanel gp;
	public KEY( GamePanel gp) {
		this.gp = gp;
		Name = "key";
		try {
			image1 = ImageIO.read(getClass().getResourceAsStream("/Ob/key.png"));
			uTool.scaleImage(image1, gp.tilesize, gp.tilesize);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}