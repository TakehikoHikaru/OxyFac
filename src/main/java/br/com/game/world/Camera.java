package br.com.game.world;

public class Camera {


    public static int x;
    public static int y;

    public static int getX() {
        return x;
    }

    public static void setX(int x) {
        Camera.x = x;
    }

    public static int getY() {
        return y;
    }

    public static void setY(int y) {
        Camera.y = y;
    }

    public  static int clamp(int value, int min , int max){
        if(value <= min){
            value = min;
        }
        if(value >= max){
            value = max;
        }
        return value;
    }
}
