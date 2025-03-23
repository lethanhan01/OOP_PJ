package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class BOOT extends SuperObject{
	GamePanel gp;
	public BOOT(GamePanel gp) {
		this.gp = gp;
		Name = "boot";
		try {
			image1 = ImageIO.read(getClass().getResourceAsStream("/Ob/boots.png"));
			uTool.scaleImage(image1, gp.tilesize, gp.tilesize);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
