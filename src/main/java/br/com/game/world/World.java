package br.com.game.world;

import br.com.game.Game;
import br.com.game.ImageSize;
import br.com.game.entities.Enemy;
import br.com.game.entities.Entity;
import br.com.game.entities.LifePack;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class World {

    public static Tile[] tiles;

    public static int width, heigth;

    public World(String path) {
        try {
            BufferedImage map = ImageIO.read(getClass().getResource(path));
            int[] pixels = new int[map.getWidth() * map.getHeight()];

            tiles = new Tile[map.getWidth() * map.getHeight()];

            width = map.getWidth();
            heigth = map.getHeight();

            map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());

            //lê os pixels
            for (int xx = 0; xx < map.getWidth(); xx++) {
                for (int yy = 0; yy < map.getHeight(); yy++) {

                    int pixel = pixels[xx + (yy * map.getWidth())];

                    tiles[xx + (yy * width)] = new FloorTile(Tile.Grass_01, xx * ImageSize.Size, yy * ImageSize.Size);
                    if (pixel == 0xFF000000) {
                        tiles[xx + (yy * width)] = new FloorTile(Tile.Grass_01, xx * ImageSize.Size, yy * ImageSize.Size);
                    } else if (pixel == 0xFFFFFFFF) {
                        tiles[xx + (yy * width)] = new WallTile(Tile.Wall_01, xx * ImageSize.Size, yy * ImageSize.Size);
                    } else if (pixel == 0xFF0400FF) {
                        //Player
                        Game.player.setX(xx * ImageSize.Size);
                        Game.player.setY(yy * ImageSize.Size);
                    } else if (pixel == 0xFFFF0000) {
                        //Enemy
                        Enemy enemy = (new Enemy(xx * ImageSize.Size, yy * ImageSize.Size, ImageSize.Size, ImageSize.Size, Entity.enemy));
                        Game.entityList.add(enemy);
                        Game.enemyList.add (enemy);
                    } else if (pixel == 0xFF0094FF) {
                        //LifePack
                        Game.entityList.add(new LifePack(xx * ImageSize.Size, yy * ImageSize.Size, ImageSize.Size, ImageSize.Size, Entity.life));
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Render(Graphics g) {
        int xCameraStart = Camera.getX() / ImageSize.Size;
        int yCameraStart = Camera.getY() / ImageSize.Size;

        int xCameraFinal = (xCameraStart + (Game.width / ImageSize.Size)) + 5;
        int yCameraFinal = (yCameraStart + (Game.height / ImageSize.Size)) + 5;

        for (int xx = xCameraStart; xx <= xCameraFinal; xx++) {
            for (int yy = yCameraStart; yy <= yCameraFinal; yy++) {
                if (xx < 0 || yy < 0 || xx >= width || yy >= heigth) {
                    continue;
                }
                Tile tile = tiles[xx + (yy * width)];
                tile.render(g);
            }
        }
    }

    public static boolean isFree(int nextX, int nextY){
        int x1 = nextX / ImageSize.Size;
        int y1 = nextY / ImageSize.Size;

        int x2 = (nextX + ImageSize.Size + 1 )/ ImageSize.Size;
        int y2 = (nextY + ImageSize.Size)/ ImageSize.Size;

        int x3 = (nextX + ImageSize.Size)/ ImageSize.Size;
        int y3 = (nextY + ImageSize.Size + 1 )/ ImageSize.Size;

        int x4 = (nextX + ImageSize.Size + 1 )/ ImageSize.Size;
        int y4 = (nextY + ImageSize.Size + 1 )/ ImageSize.Size;

        return !(tiles[x1  + (y1*World.width)] instanceof WallTile ||
                tiles[x2  + (y2*World.width)] instanceof WallTile||
                tiles[x3  + (y3*World.width)] instanceof WallTile||
                tiles[x4  + (y4*World.width)] instanceof WallTile);
    }

}
