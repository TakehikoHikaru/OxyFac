package br.com.game.grafics;

import br.com.game.entities.Player;

import java.awt.*;

public class UI {

    public void render(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(20,20,Player.maxLife,20);
        g.setColor(Color.BLUE);
        g.fillRect(20,20,(int)((Player.life/Player.maxLife)*100),20);
        g.setColor(Color.WHITE);
        g.drawString("Life: "+(int)(Player.life)+"/"+Player.maxLife,20,20);
    }
}
