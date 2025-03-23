package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	ArrayList<String> fileNames = new ArrayList<>();
	ArrayList<String> collisionStatus = new ArrayList<>();
	
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		
		
		tile = new Tile[50];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		loadMap("/map/test2.txt");
	}
	
	public void getTileImage() {
		setup( 0, "000", false);
		setup( 1, "001", false);
		setup( 2, "002", false);
		setup( 3, "003", false);
		setup( 4, "004", false);
		setup( 5, "005", false);
		setup( 6, "006", false);
		setup( 7, "007", false);
		setup( 8, "008", false);
		setup( 9, "009", false);
		
		setup( 10, "010", false);					
		setup( 11, "011", false);
		setup( 12, "012", false);
		setup( 13, "013", false);
		setup( 14, "014", false);
		setup( 15, "015", false);
		setup( 16, "016", true);
		setup( 17, "017", true);
		setup( 18, "018", true);
		setup( 19, "019", true);
		setup( 20, "020", true);
		setup( 21, "021", true);
		setup( 22, "022", true);
		setup( 23, "023", true);
		setup( 24, "024", true);
		setup( 25, "025", true);
		setup( 26, "026", false);
		setup( 27, "027", false);
		setup( 28, "028", false);
		setup( 29, "029", false);
		setup( 30, "030", false);
		setup( 31, "031", false);
		setup( 32, "032", false);
		setup( 33, "033", false);
		setup( 34, "034", false);
		setup( 35, "035", false);
		setup( 36, "036", false);
		setup( 37, "037", false);
		setup( 38, "018", false);

	}
	public void setup (int index, String imageName, boolean collision) {
		UtilityTool uTool = new UtilityTool();
		try{
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tile/" + imageName + ".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tilesize, gp.tilesize);
			tile[index].collision = collision;
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void loadMap(String filepath) {
		
		try {
			InputStream is = getClass().getResourceAsStream(filepath); // import map file
			BufferedReader br = new BufferedReader(new InputStreamReader(is)); // read file "is"
			
			int col = 0;
			int row = 0;
			
			// quet file map tung hang mot, moi hang chia ra tung cot r gan gia tri vao mapTileNum
			
			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();
				while (col < gp.maxWorldCol) {
					String number[] = line.split(" "); // loai bo dau cach trong 1 hang cua file map
					int num = Integer.parseInt(number[col]); // doi chuoi ky tu sang chuoi so nguyen + moi hang chia ra tung cot
					
					mapTileNum[col][row] = num;
					col ++;
				}
				if (col == gp.maxWorldCol) {
					col = 0;
					row ++;
				}
			}
			br.close();
			
		}catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		int col = 0;
		int row = 0;
		//int x = 0;
		//int y = 0;
		
		while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[col][row];
					// tile can ve o dau tren map?
			int worldX = col * gp.tilesize;
			int worldY = row * gp.tilesize;
					// tile can ve o dau tren screen?
			int screenX = worldX - gp.player.worldX +gp.player.screenX;
			int screenY = worldY - gp.player.worldY +gp.player.screenY;
			
			if (worldX + gp.tilesize > gp.player.worldX - gp.player.screenX &&
				    worldX - gp.tilesize < gp.player.worldX + gp.player.screenX &&
				    worldY + gp.tilesize > gp.player.worldY - gp.player.screenY &&
				    worldY - gp.tilesize < gp.player.worldY + gp.player.screenY) {
				
					g2.drawImage(tile[tileNum].image, screenX, screenY, null);
			}
			col ++;
			
			if(col == gp.maxWorldCol) {
				col = 0;
				row ++;
				
			}
		}
	}

}
