
import br.com.game.entities.Entity;
import br.com.game.entities.Player;
import br.com.game.grafics.SpriteSheet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Game extends Canvas implements Runnable, KeyListener {

    public double FPS = 60;

    public JFrame frame;


    private boolean isRunning;
    private Thread thread;

    private final int width = 1360;
    private final int height = 768;
    private final int scale = 4;

    private BufferedImage image;

    public List<Entity> entityList;
    public SpriteSheet spriteSheet;
    public Player player;

    public Game() {
        addKeyListener(this);
        this.setPreferredSize(new Dimension(width, height));
        initFrame();
        image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        entityList = new ArrayList<Entity>();
        spriteSheet = new SpriteSheet("/SpritesSheets.png");

        player = new Player(0,0,16,16,spriteSheet.getSprite(0,0,64,64));
        entityList.add(player);
    }

    public void initFrame(){
        frame = new JFrame("Game");
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        Game game = new Game();
        game.start();
    }

    public synchronized void start() {
        thread = new Thread(this);
        isRunning = true;
        thread.start();
    }

    public synchronized void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        double ns = 1000000000 / FPS;
        double delta = 0;

        double frames = 0;
        double timer = System.currentTimeMillis();

        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                tick();
                render();
                delta--;
                frames++;
            }

            if (System.currentTimeMillis() - timer >= 1000) {
                System.out.println("FPS: " + frames);
                frames = 0;
                timer += 1000;
            }

        }
        stop();
    }

    public void tick() {
        for (int i = 0; i < entityList.size(); i++) {
            Entity entity = entityList.get(i);
            entity.tick();
        }

    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = image.getGraphics();
        g.setColor(Color.red);
        g.fillRect(0,0,width,height);
        for (int i = 0; i < entityList.size(); i++) {
            Entity entity = entityList.get(i);
            entity.render(g);
        }
        g.dispose();
        g = bs.getDrawGraphics();
        g.drawImage(image,0,0,width,height,null);
        bs.show();
    }


    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
            System.out.println("up");
            player.up = true;
        }else if(e.getKeyCode() == KeyEvent.VK_S){
            player.down = true;
        }

        if(e.getKeyCode() == KeyEvent.VK_A){
            player.left = true;
        }else if(e.getKeyCode() == KeyEvent.VK_D){
            player.rigth = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
            System.out.println("up");
            player.up = false;
        }else if(e.getKeyCode() == KeyEvent.VK_S){
            player.down = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_A){
            player.left = false;
        }else if(e.getKeyCode() == KeyEvent.VK_D){
            player.rigth = false;
        }
    }
}
