package ims.tuwien.ac.at.battleground.game.entity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import ims.tuwien.ac.at.battleground.game.Game;
import ims.tuwien.ac.at.battleground.game.entity.building.Tower;


/**
 * This class handles all hittable objects. It is used as an abstract super class for all
 * hittable objects in the overall game. Each hittable object has a specification of its hitpoints.
 *
 * @author hoch
 * @author schueller
 */
public abstract class HittableObject extends DrawableObject
{
    /**
     * Specifies the current hitPoints
     */
    private double hitPoints;
    /**
     * Specifies the overall maximum hitPoints
     */
    private double hitPointsMax;

    /**
     * The constructor of the HittableObject
     *
     * @param x the x-coordinate of the object
     * @param y the y-coordinate of the object
     * @param bitmapId the id of the bitmap to be loaded
     */
    public HittableObject(float x, float y, int bitmapId)
    {
        super(x, y, bitmapId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(float tpf)
    {
        if(this.getHitPoints() <= 0) {
            this.die();
            PF.addObject(new Explosion(this.getX(),this.getY()));
        }
    }

    /**
     * Draws a health bar for the hittable object on the canvas underneath the bitmap
     *
     * @param canvas The canvas that the health bar is drawn on
     */
    public void drawHealthBar(Canvas canvas)
    {
        final int border = 2;

        int x1 = (int)(this.getX() - this.getWidth()/3);
        int y1 = (int)(this.getY() + this.getHeight()/3 + 2);
        int x2 = (int)(this.getX() + this.getWidth()/3);
        int y2 = (int)(this.getY() + this.getHeight()/3 + 10);

        // width of the health bar
        int health = (int)Math.ceil((this.getWidth()*2/3 - border*2) * this.getHitPointsPerc());

        Rect backRect = new Rect(x1, y1, x2, y2);
        Rect backRect2 = new Rect(x1+border, y1+border, x2-border, y2-border);
        Rect frontRect = new Rect(x1+border, y1+border, x1+border+health, y2-border);

        Paint backCol = new Paint();
        backCol.setColor(Color.GRAY);
        Paint backCol2 = new Paint();
        backCol.setColor(Color.DKGRAY);
        Paint frontCol = new Paint();
        if(this.getHitPointsPerc() > 0.25) {
            frontCol.setColor(Color.GREEN);
        }
        else {
            frontCol.setColor(Color.RED);
        }


        //Alternative (schoener Uebergang zw. Gruen und Rot):
        float[] clr = new float[]{(float)getHitPointsPerc()*120,1,1};
        frontCol.setColor(Color.HSVToColor(clr));

        canvas.drawRect(backRect, backCol);
        canvas.drawRect(backRect2, backCol2);
        canvas.drawRect(frontRect, frontCol);

        //canvas.drawText(""+this.getHitPoints()+"  "+this.getHitPointsPerc()+"   "+health, this.getX()-50,this.getY()+this.getHeight()/2+20, backCol);
    }

    /**
     * Returns the current hitPoints in percent (0..1, for the health bar)
     * @return Current hitpoints in percent
     */
    public double getHitPointsPerc()
    {
        return hitPoints/hitPointsMax;
    }

    /**
     * Returns the current hitPoints.
     * @return The current hitPoints
     */
    public double getHitPoints()
    {
        return hitPoints;
    }

    /**
     * Sets the current hitPoints
     * @param hitPoints The current hitPoints
     */
    public void setHitPoints(double hitPoints)
    {
        this.hitPoints = hitPoints;
    }

    /**
     * Returns the maximum hitPoints
     * @return The maximum hitPoints
     */
    public double getHitPointsMax()
    {
        return hitPointsMax;
    }

    /**
     * Sets the maximum hitPoints
     * @param hitPointsMax The maximum hitPoints
     */
    public void setHitPointsMax(double hitPointsMax)
    {
        this.hitPointsMax = hitPointsMax;
    }
}
