package game.items;

import game.RefLinks;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
@Getter
@Setter
public abstract class Item {
    protected float x;
    protected float y;
    protected int width;
    protected int height;
    protected Rectangle bounds;
    protected Rectangle normalBounds;
    protected Rectangle intractableBounds;
    protected RefLinks refLink;

    public Item(RefLinks refLink, float x, float y, int width, int height){
        this.x = x;             /*!< Retine coordonata pe axa X.*/
        this.y = y;             /*!< Retine coordonata pe axa Y.*/
        this.width = width;     /*!< Retine latimea imaginii.*/
        this.height = height;   /*!< Retine inaltimea imaginii.*/
        this.refLink = refLink; /*!< Retine the "shortcut".*/

        normalBounds = new Rectangle((int)x-32,(int)y-20,width,height);
        bounds = normalBounds;
        intractableBounds = new Rectangle((int)x-40,(int)y-30,width+40,height+60);
        System.out.println();
    }
    public Rectangle getBounds(){
        return bounds;
    }
    public void setBounds(int hi, int wi){
        bounds.height = hi;
        bounds.width = wi;
    }
    public Rectangle getIntractableBounds(){
        return intractableBounds;
    }

    public abstract void Update();
    public abstract void Draw(Graphics g);
    public float GetWidth(){return width;}
    public float GetHeight(){return height;}
    public void SetX(float x)
    {
        this.x = x;
    }

    public void SetY(float y)
    {
        this.y = y;
    }
    public void SetWidth(int width)
    {
        this.width = width;
    }
    public void SetHeight(int height)
    {
        this.height = height;
    }
    public void SetNormalMode()
    {
        bounds = normalBounds;
    }


}
