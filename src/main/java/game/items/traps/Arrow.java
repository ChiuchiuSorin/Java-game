package game.items.traps;

import game.RefLinks;
import game.collision.Collision;
import game.graphics.Assets;
import game.items.Character;
import game.items.Item;
import game.items.Player;
import game.maps.MapManager;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;

@Setter
@Getter
public class Arrow extends Character {
    private boolean hit = false;
    private float xAux;
    private float yAux;
    private BufferedImage ArrowImage;
    private int count;

    public Arrow(RefLinks refLink, float x, float y, int width, int height) {
        super(refLink, x, y, width, height);
        ArrowImage = Assets.arrow;
        xAux = this.x;
        yAux = this.y;
        bounds.x = (int) x-20;
        bounds.y = (int) y - 30;
        bounds.width = 20;
        bounds.height = 24;
        count = 0;
    }

    @Override
    public void verifyBounds() {
        bounds.y = (int) y-20;
    }

    @Override
    public void Update() {
        if(!hit)
            y += -4;
        if(hit){
            x = -300;
        }
        delay();
    }

    public void reset(){
        x = xAux;
        y = yAux;
        count = 0;
        hit = false;
    }
    public void delay(){
        count++;
        if(count > 200)
            reset();
    }
    public void arrowCollision(Player chr, MapManager m) {
        this.verifyBounds();
        chr.verifyBounds();
        m.mapColl.x = 0;
        m.mapColl.y = 0;

        int[][] maplayer2 = MapManager.getCurrentRoom();
        for (int i = 0; i < MapManager.getHeight(); i++) {
            for (int j = 0; j < MapManager.getWidth(); j++) {
                m.mapColl.x = j * 31 - 8;
                m.mapColl.y = i * 30 - 20;
                switch (maplayer2[i][j]) {
                    case 12:
                        if (Collision.AABB(this.getBounds(), m.mapColl) && !this.isHit()) {
                            System.out.println("coliziune");
                            this.setHit(true);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        if (Collision.AABB(chr.getBounds(), this.getBounds()) && !this.isHit()) {
            System.out.println("arrowHit");
            Player.playerLife--;
            this.setHit(true);
        }
    }
    @Override
    public void Draw(Graphics g) {
        if(!isHit())
            g.drawImage(ArrowImage,(int) x,(int) y, width,height,null);
    }
}
