package br.com.game.world;

import br.com.game.Game;
import br.com.game.ImageSize;
import br.com.game.grafics.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    public static SpriteSheet grama =  new SpriteSheet("/Grass_01.png");
    public static BufferedImage Grass_01 = grama.getSprite(0,0, ImageSize.Size, ImageSize.Size);
    public static BufferedImage Wall_01 = Game.spriteSheet.getSprite(128,64,ImageSize.Size, ImageSize.Size);

    private BufferedImage sprite;
    private  int x,y;

    public Tile(BufferedImage sprite, int x, int y) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
    }

    public void render(Graphics g){
        g.drawImage(sprite,x - Camera.getX(),y - Camera.getY(),null);
    }

}
