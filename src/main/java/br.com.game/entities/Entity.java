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

    private static int xMask;
    private static int yMask;
    private int mWidth;
    private int mHeight;


    protected BufferedImage sprite;

    public Entity(int x, int y, int width, int height, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = sprite;

        xMask =0;
        yMask =0;
        mWidth = width;
        mHeight = height;
    }

    public void setMask(int xMask,int yMask,int mWidth, int mHeight){
        this.yMask = yMask;
        this.xMask = xMask;
        this.mHeight = mHeight;
        this.mWidth = mWidth;
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

        //Debug
        g.setColor(Color.cyan);
        g.fillRect(this.getX() + xMask - Camera.getX(),this.getY() + yMask - Camera.getY(), mWidth,mHeight );

    }


    public static boolean isColidding(Entity e1,Entity e2){
        Rectangle e1Mask = new Rectangle(e1.getX() + e1.xMask,e1.getY() + yMask ,e1.mWidth,e1.mHeight);
        Rectangle e2Mask = new Rectangle(e2.getX() + e2.xMask,e2.getY() + yMask ,e2.mWidth,e2.mHeight);
    return e1Mask.intersects(e2Mask);
    }


}
