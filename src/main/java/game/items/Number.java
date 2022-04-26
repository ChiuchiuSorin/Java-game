package game.items;

import game.RefLinks;
import game.graphics.Assets;
import game.items.typesEnums.ObjType;

import java.awt.*;
import java.awt.image.BufferedImage;

import static game.items.typesEnums.ObjType.*;

public class Number extends Item {
    private BufferedImage image;
    private ObjType type;
    public Number(RefLinks refLink, float x, float y, int width, int height, ObjType type) {
        super(refLink, x, y, width, height);
        this.type = type;
        image = Assets.numbers[0];
        if(this.type == LIFE){
            image = Assets.numbers[3];
        }
        else if( this.type == SCORE){
            image = Assets.numbers[0];
        }
    }

    @Override
    public void Update() {
        if(type == KEY) {
            if (Player.NOKeys == 0) {
                image = Assets.numbers[0];
            }
            if (Player.NOKeys == 1) {
                image = Assets.numbers[1];
            }
            if (Player.NOKeys == 2) {
                image = Assets.numbers[2];
            }
        }
        if(type == LIFE){
            setScore(Player.playerLife);
        }
        if(type == SCORE){
            setScore(Player.score);
        }
    }

    private void setScore(int score) {
        if (score == 0) {
            image = Assets.numbers[0];
        }
        if (score == 1) {
            image = Assets.numbers[1];
        }
        if (score == 2) {
            image = Assets.numbers[2];
        }
        if (score == 3) {
            image = Assets.numbers[3];
        }
        if (score == 4) {
            image = Assets.numbers[4];
        }
        if (score == 5) {
            image = Assets.numbers[5];
        }
        if (score == 6) {
            image = Assets.numbers[6];
        }
        if (score == 7) {
            image = Assets.numbers[7];
        }
        if (score == 7) {
            image = Assets.numbers[7];
        }
        if (score == 8) {
            image = Assets.numbers[8];
        }
        if (score == 9) {
            image = Assets.numbers[9];
        }
    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(image,(int) x,(int) y, width,height,null);
    }
}
