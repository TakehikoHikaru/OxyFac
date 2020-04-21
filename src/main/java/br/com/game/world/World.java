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
            for (int i = 0; i < map.getWidth() ; i++) {
                for (int j = 0; j < map.getHeight() ; j++) {
                    int pixel = pixels[i + (j * map.getWidth())] ;
                    if (pixel == 0xFF000000){
                        //Chão
                        tiles[i + (j * map.getWidth())] = new FloorTile(Tile.Grass_01,i,j);
                    }else if(pixel == 0xFFFFFFFF){
                        tiles[i + (j * map.getWidth())] = new FloorTile(Tile.Grass_01,i,j);
                    }else if(pixel == 0xFF3300FF){
                        tiles[i + (j * map.getWidth())] = new FloorTile(Tile.Grass_01,i,j);
                    }
                    else{
                        tiles[i + (j * map.getWidth())] = new FloorTile(Tile.Grass_01,i,j);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Render(Graphics g){
        for (int i = 0; i < width ; i++) {
            for (int j = 0; j < heigth; j++) {
                Tile tile = tiles[i +(j*width)];
                tile.render(g);
            }
        }
    }




}
