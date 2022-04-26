package game.items;

import game.RefLinks;
import game.collision.Collision;
import game.graphics.Assets;
import game.items.typesEnums.ObjType;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;

import static game.items.typesEnums.ObjType.*;

@Getter
@Setter
public class Interactable extends Item {
    private BufferedImage image;
    private boolean used = false;
    private boolean disappearingObj;
    private boolean oneUse = true;
    private ObjType type;
    private Health[] pickUpHealth = new Health[2];

    public Interactable(RefLinks refLink, float x, float y, int width, int height, boolean disappear, ObjType t) {
        super(refLink, x + 4, y + 4, width, height);
        setBounds(54, 60);
        disappearingObj = disappear;
        type = t;
        if (type == CHEST)
            image = Assets.chest[0];
        else if (type == POWERUP)
            image = Assets.damagePower;
    }

    public Interactable(RefLinks refLink, float x, float y, int width, int height, int hi, int wi, boolean disappear, ObjType t) {
        super(refLink, x + 4, y + 4, width, height);
        type = t;
        if (type == CHEST)
            image = Assets.chest[0];
        else if (type == POWERUP)
            image = Assets.damagePower;
        else if (type == KEY)
            image = Assets.key;
        setBounds(hi, wi);
        disappearingObj = disappear;
        pickUpHealth[0] = new Health(refLink,0,0,0,0);
    }

    public void actionUsed(Player player) {
        if (refLink.getKeyManager().action && Collision.AABB(player.getBounds(), this.getIntractableBounds()) && !used) {
            used = true;
            System.out.println("interact");
        }
        if(pickUpHealth[0].spawn){
            pickUpHealth[0].pickedUp(player);
            pickUpHealth[1].pickedUp(player);
        }
    }

    @Override
    public void Update() {
        if (used && type == CHEST) {
            image = Assets.chest[1];
            if(!pickUpHealth[0].spawn){
                pickUpHealth[0] = new Health(refLink, x, y-60,32,32);
                pickUpHealth[1] = new Health(refLink, x+60, y,32,32);
                pickUpHealth[0].spawn = true;
                Player.score++;
            }
        }
        if(used && type == POWERUP && oneUse){
            Player.damage = 10;
            oneUse = false;
        }
        if (used && type == KEY && oneUse) {
            Player.NOKeys++;
            System.out.println(Player.NOKeys);
            oneUse = false;
        }
    }

    @Override
    public void Draw(Graphics g) {
        if (!used || !disappearingObj)
            g.drawImage(image, (int) x, (int) y, width, height, null);
        if(pickUpHealth[0].spawn){
            pickUpHealth[0].Draw(g);
            pickUpHealth[1].Draw(g);
        }
    }
}