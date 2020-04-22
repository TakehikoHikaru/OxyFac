package br.com.game.world;

import br.com.game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    public static BufferedImage Grass_01 = Game.spriteSheet.getSprite(64,64,64,64);
    public static BufferedImage Wall_01 = Game.spriteSheet.getSprite(128,64,64,64);;

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