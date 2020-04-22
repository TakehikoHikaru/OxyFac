package br.com.game.entities;

import br.com.game.Game;
import br.com.game.world.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public static BufferedImage life = Game.spriteSheet.getSprite(64*3,64,64,64);
    public static BufferedImage enemy= Game.spriteSheet.getSprite(0,64*2,64,64);

    protected int x;
    protected int y;
    protected int width;
    protected int height;

    private BufferedImage sprite;

    public Entity(int x, int y, int width, int height, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    public void tick(){

    }

    public void render(Graphics g){
        g.drawImage(sprite,this.getX() - Camera.getX(),this.getY() - Camera.getY(),null);
    }


}
