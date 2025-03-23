package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
	public boolean up, down, left , right, enter;
	GamePanel gp;
	public KeyHandler (GamePanel gp) {
		this.gp = gp;
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode(); // code = gia tri nguyen cua key da nhap
		
		if (gp.gameState == gp.titleState) {
			if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
				gp.ui.commandNum --;
				if (gp.ui.commandNum < 0 ) {
					gp.ui.commandNum = 2;
				}
			}
			if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
				gp.ui.commandNum ++;
				if (gp.ui.commandNum > 2 ) {
					gp.ui.commandNum = 0;
				}
			}
			if (code == KeyEvent.VK_ENTER) {
				switch (gp.ui.commandNum) {
				case 0:
					gp.gameState = gp.playState;
					break;
				case 1:
					//
					break;
				case 2:
					System.exit(0);
					break;
				}
			}
		
		}
		
		if (gp.gameState == gp.playState) {
			if( code == KeyEvent.VK_W ||code == KeyEvent.VK_UP) {
				up = true;
			}
			if( code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
				left = true;
			}
			if( code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
				right = true;
			}
			if( code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
				down = true;
			}
			
			if( code == KeyEvent.VK_ESCAPE) {
				gp.gameState = gp.pauseState;
			}
			if( code == KeyEvent.VK_ENTER) {
				enter = true;
			}
		}
		else if (gp.gameState == gp.pauseState) {
			if( code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_ENTER) {
				gp.gameState = gp.playState;
			}
		}
		
		else if (gp.gameState == gp.dialogState) {
			if( code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_ENTER) {
				gp.gameState = gp.playState;
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode(); // code = gia tri nguyen cua key da nhap
		
		if( code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			up = false;
		}
		if( code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			left = false;
		}
		if( code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			right = false;
		}
		if( code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			down = false;
		}
		
	}

}
