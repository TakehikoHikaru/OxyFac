package br.com.game.world;

import br.com.game.grafics.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    public static BufferedImage Grass_01 = new SpriteSheet("/Grass_01.png").getSprite(0,0,64,64);

    private BufferedImage sprite;
    private  int x,y;

    public Tile(BufferedImage sprite, int x, int y) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
    }

    public void render(Graphics g){
        g.drawImage(sprite,x,y,null);
    }

}
