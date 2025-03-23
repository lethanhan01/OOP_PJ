package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class HEART extends SuperObject{
	GamePanel gp;
	public HEART(GamePanel gp) {
		this.gp = gp;
		Name = "heart";
		try {
			image1 = ImageIO.read(getClass().getResourceAsStream("/Ob/heart_full.png"));
			image1 = uTool.scaleImage(image1, gp.tilesize, gp.tilesize);
			image2 = ImageIO.read(getClass().getResourceAsStream("/Ob/heart_half.png"));
			image2 = uTool.scaleImage(image2, gp.tilesize, gp.tilesize);
			image3 = ImageIO.read(getClass().getResourceAsStream("/Ob/heart.png"));
			image3 = uTool.scaleImage(image3, gp.tilesize, gp.tilesize);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
