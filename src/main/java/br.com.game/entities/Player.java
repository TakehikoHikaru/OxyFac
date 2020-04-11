package br.com.game.entities;

import java.awt.image.BufferedImage;

public class Player extends Entity {

    public boolean     rigth,left,up,down;
    public double speed = 4;

    public Player(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
    }

    @Override
    public void tick() {
        if(rigth){
            x+=speed;
        }else if(left){
            x-=speed;
        }
        if(up){
            y-=speed;
        }else if(down){
            y+=speed;
        }
    }
}
