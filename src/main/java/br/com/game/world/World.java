package br.com.game.world;

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

                    if (pixel == 0xFF000000){
                        tiles[xx + (yy* width)] = new FloorTile(Tile.Grass_01,xx*64,yy*64);
                    }else if(pixel == 0xFFFFFFFF){
                        tiles[xx + (yy* width)] = new WallTile(Tile.Wall_01,xx*64,yy*64);
                    }else if(pixel == 0xFF0400FF){
                        tiles[xx + (yy* width)] = new FloorTile(Tile.Grass_01,xx*64,yy*64);
                    }
                    else{
                        tiles[xx + (yy* width)] = new FloorTile(Tile.Grass_01,xx*64,yy*64);
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
