import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.JFrame;

public class cobra  extends Canvas implements Runnable,KeyListener{
	
	public node[] nodeSnake = new node[30];
	
	public boolean left, right, up, down;
	
	public int score = 0;
	
	public int macax = 0, macay = 0;
	
	public int spd = 1;
	
	public cobra() {
		this.setPreferredSize(new Dimension(360, 360));
		for(int i = 0; i < nodeSnake.length; i++) {
			nodeSnake[i] = new node(0, 0);
		}
		this.addKeyListener(this);
	}
	
	public void tick() {
		for(int i = nodeSnake.length - 1; i > 0; i--) {
			nodeSnake[i].x = nodeSnake[i-1].x;
			nodeSnake[i].y = nodeSnake[i-1].y;
		}
		
		if(nodeSnake[0].x + 10 < 0) {
			nodeSnake[0].x = 360;
		}else if(nodeSnake[0].x + 10 > 360) {
			nodeSnake[0].x = -10;
		}
		if(nodeSnake[0].y + 10 < 0) {
			nodeSnake[0].y = 360;
		}else if(nodeSnake[0].y + 10 > 360) {
			nodeSnake[0].y = -10;
		}
		
		
		if(right) {
			nodeSnake[0].x+=spd;
		}else if(up) {
			nodeSnake[0].y-=spd;
		}else if(down) {
			nodeSnake[0].y+=spd;
		}else if(left) {
			nodeSnake[0].x-=spd;
		}
		
		if(new Rectangle(nodeSnake[0].x, nodeSnake[0].y,10,10).intersects(new Rectangle(macax, macay, 10, 10))) {
			macax = new Random().nextInt(360-10);
			macay = new Random().nextInt(360-10);
			score++;
			spd++;
			System.out.println("pontos: "+ score);
		}
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, 360, 360);
		for(int i = 0; i < nodeSnake.length;i++) {
			g.setColor(Color.blue);
			g.fillRect(nodeSnake[i].x, nodeSnake[i].y, 10, 10);
			
		}
		g.setColor(Color.red);
		g.fillRect(macax, macay, 10, 10);
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String args[]) {
		cobra snake = new cobra();
		JFrame frame = new JFrame("cobra");
		frame.add(snake);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.pack();
		
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		new Thread(snake).start();
	}
	
	@Override
	public void run(){
		
		while (true) {
			tick();
			render();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = true;
			left = false;
			up = false;
			down = false;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = true;
			up = false;
			down = false;
			right = false;
		}else if(e.getKeyCode() == KeyEvent.VK_UP) {
			up = true;
			right = false;
			left = false;
			down = false;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = true;
			right = false;
			left = false;
			up = false;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}