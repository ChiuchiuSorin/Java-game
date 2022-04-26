package game.items;

import game.graphics.*;
import game.RefLinks;
import game.items.typesEnums.ObjType;

import java.awt.*;
import java.awt.image.BufferedImage;

import static game.items.typesEnums.ObjType.*;

public class Permanent extends Item{
    private BufferedImage image;
    private BufferedImage[] images;
    private boolean isAnimated = false;
    private Animation anim;

    public Permanent(RefLinks refLink, float x, float y, int width, int height,
                     int hBound, int wBound, ObjType type){
        super(refLink, x, y, width, height);
        setBounds(hBound,wBound);
        if(type != null) {
            if (type == BED)
                image = Assets.bed;
            else if (type == TABLEANDCHAIRS)
                image = Assets.table_and_chairs;
            else if (type == FIRE)
                System.out.println();
            else if (type == POWERDECORATION)
                image = Assets.damagePowerDecoration;
            else if (type == ARROWTRAP)
                image = Assets.arrowTrap;
            else if (type == TABLE)
                image = Assets.table;
            else if (type == BONEPILE1)
                image = Assets.bonePiles[0];
            else if (type == BONEPILE2)
                image = Assets.bonePiles[1];
            else if (type == BLOODFOUNTAIN)
                image = Assets.bloodFountain;
            else if (type == BLOOD1)
                image = Assets.blood[0];
            else if (type == BLOOD2)
                image = Assets.blood[1];
            else if (type == BLOOD3)
                image = Assets.blood[2];
            else if (type == BLOOD4)
                image = Assets.blood[3];
            else if(type == SKELETON)
                image = Assets.skeleton;
            else if(type == LIFE)
                image = Assets.lifeSymbol;
        }
    }

    public Permanent(RefLinks refLink, float x, float y, int width, int height,
                     int hBound, int wBound, ObjType type, boolean isAnim){
        super(refLink, x, y, width, height);
        isAnimated = isAnim;
        setBounds(hBound,wBound);
        if(type == FIRE)
            images = Assets.fireplace;
        else if(type == CAULDRON)
            images = Assets.cauldron;
        anim = new Animation(7, images);
    }

    @Override
    public void Update() {
    }

    @Override
    public void Draw(Graphics g) {
        if (isAnimated){
            anim.runAnimation();
            anim.drawAnimation(g, (int) x, (int) y, width, height);
        }
        else
            g.drawImage(image,(int) x,(int) y, width,height,null);
    }
}
