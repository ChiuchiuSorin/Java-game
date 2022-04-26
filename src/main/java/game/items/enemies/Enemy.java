package game.items.enemies;

import game.RefLinks;
import game.graphics.Animation;
import game.graphics.Assets;
import game.items.Character;
import game.items.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy extends Character {
    private int count = 0;
    protected BufferedImage[][] moveImages;
    protected BufferedImage[][] attackImages;
    public boolean isDead = false;
    public boolean attacking = false;
    public boolean hitting = true;
    protected int damage;

    public Enemy(RefLinks refLink, float x, float y) {
        super(refLink, x, y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
        life = 2;
        moveImages = Assets.enemy1_movement;
        attackImages = Assets.enemy1_attack_dead;
        anim = new Animation(5, moveImages[0]);
        normalBounds.x = 16;
        normalBounds.y = 16;
        normalBounds.width = 16;
        normalBounds.height = 32;
        speed = 2;
        attackBounds.x = 0;
        attackBounds.y = 0;
        attackBounds.width = 90;
        attackBounds.height = 38;
        anim.updateImage(moveImages[0], 15);
        count = 0;
        damage = 1;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void verifyBounds() {
        movement();
        bounds.x = (int) x + (int) xMove;
        bounds.y = (int) yMove + (int) y;
    }

    @Override
    public void Update() {
        if (!isDead && !attacking) {
            verifyBounds();
            enemyMove();
            //hitting = true;
        } else if (attacking && !isDead) {
            if (Player.playerX < x) {
                anim.updateImage(attackImages[0], 8);
            }
            if (Player.playerX > x) {
                anim.updateImage(attackImages[1], 8);
            }
            if (hitting) {
                Player.playerLife -= damage;
                System.out.println("player hit");
                hitting = false;
            }
            delay();
        }
        anim.runAnimation();
    }

    public void delay() {
        count++;
        if (count > 80) {
            count = 0;
            hitting = true;
        }
    }

    private void enemyMove() {
        x += xMove;
        y += yMove;
        attackBounds.y = (int) y;
        attackBounds.x = (int) x - 30;
    }

    private void movement() {
        float diffX = Player.playerX - x;
        float diffY = Player.playerY - y;

        float angle = (float) Math.atan2(diffY, diffX);

        xMove = (float) (speed * Math.cos(angle));
        yMove = (float) (speed * Math.sin(angle));
        if (2 * Math.cos(angle) < 0) {
            anim.updateImage(moveImages[0], 5);
        } else {
            anim.updateImage(moveImages[1], 5);
        }
    }

    @Override
    public void Draw(Graphics g) {
        if (isDead) {
            g.drawImage(attackImages[2][5], (int) x, (int) y, width, height, null);
        } else
            anim.drawAnimation(g, (int) x, (int) y, width, height);
    }
}
