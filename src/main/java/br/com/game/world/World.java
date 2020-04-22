package br.com.game.world;

import br.com.game.Game;
import br.com.game.entities.Enemy;
import br.com.game.entities.Entity;
import br.com.game.entities.LifePack;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class World {

    private Tile[] tiles;

    private static int width,heigth;

    public World(String path) {
        try {
            BufferedImage map = ImageIO.read(getClass().getResource(path));
            int[] pixels = new int[map.getWidth() * map.getHeight()];

            tiles = new Tile[map.getWidth() * map.getHeight()];

            width = map.getWidth();
            heigth = map.getHeight();

            map.getRGB(0,0,map.getWidth(),map.getHeight(),pixels,0,map.getWidth());

            //lê os pixels
            for (int xx = 0; xx < map.getWidth() ; xx++) {
                for (int yy = 0; yy < map.getHeight() ; yy++) {

                    int pixel = pixels[xx + (yy * map.getWidth())] ;

                    tiles[xx + (yy* width)] = new FloorTile(Tile.Grass_01,xx*64,yy*64);
                    if (pixel == 0xFF000000){
                        tiles[xx + (yy* width)] = new FloorTile(Tile.Grass_01,xx*64,yy*64);
                    }else if(pixel == 0xFFFFFFFF){
                        tiles[xx + (yy* width)] = new WallTile(Tile.Wall_01,xx*64,yy*64);
                    }else if(pixel == 0xFF0400FF){
                    //Player
                        Game.player.setX(xx*64);
                        Game.player.setY(yy*64);
                    }else if(pixel == 0xFFFF0000){
                        //Enemy
                        Game.entityList.add(new Enemy(xx*64,yy*64,64,64, Entity.enemy));
                    }else if(pixel == 0xFF0094FF){
                        //LifePack
                        Game.entityList.add(new LifePack(xx*64,yy*64,64,64, Entity.life));
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Render(Graphics g){
        for (int xx = 0; xx < width ; xx++) {
            for (int yy = 0; yy < heigth; yy++) {
                Tile tile = tiles[xx +(yy*width)];
                tile.render(g);
            }
        }
    }




}
