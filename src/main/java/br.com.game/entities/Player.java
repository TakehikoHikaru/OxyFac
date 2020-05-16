package br.com.game.entities;

import br.com.game.Game;
import br.com.game.ImageSize;
import br.com.game.world.Camera;
import br.com.game.world.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {


    public boolean rigth, left, up, down;
    public int stayRigth = 0, stayLeft = 1, stayUp = 2, stayDown = 3;
    public int stay = stayUp;
    public double speed = 4;

    private BufferedImage[] rigthPlayer;
    private BufferedImage[] leftPlayer;
    private BufferedImage[] upPlayer;
    private BufferedImage[] downPlayer;

    private boolean moved = false;
    private int frames = 0, maxFrames = 5, index = 0, maxIdex = 0;

    public Player(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
        rigthPlayer = new BufferedImage[1];
        leftPlayer = new BufferedImage[1];
        upPlayer = new BufferedImage[1];
        downPlayer = new BufferedImage[1];

        rigthPlayer[0] = Game.spriteSheet.getSprite(0, 0, 64, 64);
        upPlayer[0] = Game.spriteSheet.getSprite(0, 64, 64, 64);
        downPlayer[0] = Game.spriteSheet.getSprite(64, 0, 64, 64);
        leftPlayer[0] = Game.spriteSheet.getSprite(128, 0, 64, 64);
    }

    @Override
    public void tick() {
        moved = false;
        if (rigth && World.isFree((int)(this.getX() + speed), y)) {
            moved = true;
            stay = stayRigth;
            x += speed;
        } else if (left && World.isFree((int)(this.getX() - speed), y)) {
            moved = true;
            stay = stayLeft;
            x -= speed;
        }
        if (up && World.isFree(this.getX(), (int)(y - speed))) {
            moved = true;
            stay = stayUp;
            y -= speed;
        } else if (down && World.isFree(this.getX(), (int)(y + speed))) {
            moved = true;
            stay = stayDown;
            y += speed;
        }

        if (moved) {
            frames++;
            if (frames > maxFrames) {
                frames = 0;
                index++;
                if (index > maxIdex) {
                    index = 0;
                }
            }
        }

        Camera.setX(Camera.clamp(this.getX() - (Game.width / 2), 0, World.width * ImageSize.Size - Game.width));
        Camera.setY(Camera.clamp(this.getY() - (Game.height / 2), 0, World.heigth * ImageSize.Size - Game.height));
    }

    @Override
    public void render(Graphics g) {
        if (stay == stayRigth) {
            g.drawImage(rigthPlayer[index], this.getX() - Camera.getX(), this.getY() - Camera.getY(), null);
        } else if (stay == stayLeft) {
            g.drawImage(leftPlayer[index], this.getX() - Camera.getX(), this.getY() - Camera.getY(), null);
        }

        if (stay == stayUp) {
            g.drawImage(upPlayer[index], this.getX() - Camera.getX(), this.getY() - Camera.getY(), null);
        } else if (stay == stayDown) {
            g.drawImage(downPlayer[index], this.getX() - Camera.getX(), this.getY() - Camera.getY(), null);
        }
    }
}
