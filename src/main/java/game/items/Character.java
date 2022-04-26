package game.items;

import game.RefLinks;
import game.graphics.Animation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;

@Getter
@Setter
public abstract class Character extends Item {
    public static final int DEFAULT_LIFE = 10;   /*!< Valoarea implicita a vietii unui caracter.*/
    public static final float DEFAULT_SPEED = 3.0f; /*!< Viteza implicita a unu caracter.*/
    public static final int DEFAULT_CREATURE_WIDTH = 76;   /*!< Latimea implicita a imaginii caracterului.*/
    public static final int DEFAULT_CREATURE_HEIGHT = 76;   /*!< Inaltimea implicita a imaginii caracterului.*/

    protected int life;     /*!< Retine viata caracterului.*/
    protected float speed;  /*!< Retine viteza de deplasare caracterului.*/
    protected float xMove;  /*!< Retine noua pozitie a caracterului pe axa X.*/
    protected float yMove;  /*!< Retine noua pozitie a caracterului pe axa Y.*/
    public Rectangle attackBounds;
    public BufferedImage[] image;
    public Animation anim;

    public Character(RefLinks refLink, float x, float y, int width, int height) {
        super(refLink, x, y, width, height);
        life = DEFAULT_LIFE;
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
        attackBounds = new Rectangle(0, 0, width, height);

    }

    public void Move() {
        MoveX();
        MoveY();
    }

    public void MoveX() {
        x += xMove;
        Player.xRoom += xMove;
        Player.playerX = x;
    }

    public void MoveY() {
        y += yMove;
        Player.playerY = y;
        Player.yRoom += yMove;
    }

    public abstract void verifyBounds();

    public void setImage(BufferedImage[] img) {
        image = img;
    }

    public int GetLife() {
        return life;
    }

    public float GetSpeed() {
        return speed;
    }

    public void SetLife(int life) {
        this.life = life;
    }

    public void SetSpeed(float speed) {
        this.speed = speed;
    }

    public float GetXMove() {
        return xMove;
    }

    public float GetYMove() {
        return yMove;
    }

    public void SetXMove(float xMove) {
        this.xMove = xMove;
    }

    public void SetYMove(float yMove) {
        this.yMove = yMove;
    }

    public void SetAttackMode() {
        bounds = attackBounds;
    }
}
