package br.com.game.entities;

import br.com.game.Game;
import br.com.game.world.World;

import java.awt.image.BufferedImage;

public class Enemy extends Entity {

    private double speed = 1;

    public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
    }

    @Override
    public void tick() {
        if(Game.player.getX() < this.getX() && World.isFree((int)(getX()-speed),getY())){
            x-=(int)(speed);
        }
        if(Game.player.getX() > this.getX() && World.isFree((int)(getX()+speed),getY())){
            x += (int)(speed);
        }
        if(Game.player.getY() < this.getY() && World.isFree(getX(),(int)(getY()-speed))){
            y-=(int)(speed);
        }
        if(Game.player.getY() > this.getY()&& World.isFree(getX(),(int)(getY()+speed))){
            y += (int)(speed);
        }
    }
}
