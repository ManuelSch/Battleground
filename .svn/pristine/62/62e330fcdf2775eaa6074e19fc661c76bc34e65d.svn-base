package ims.tuwien.ac.at.battleground.game.entity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.List;

import ims.tuwien.ac.at.battleground.game.util.Constants;
import ims.tuwien.ac.at.battleground.util.SpriteSheet;


/**
 * This class handles all drawable objects. It is used as an abstract super class for all
 * drawable objects in the overall game. Each drawable object has a position on the screen, an
 * angle it is facing at and a bitmap (including an hitbox rectangle). In addition it specifies
 * whether this object is animated or not and if yes how many sprites that are available.
 *
 * @author hoch
 * @author schueller
 */
public abstract class DrawableObject implements Constants
{
    /**
     * The x-coordinate of the object
     */
    private float x;
    /**
     * The y-coordinate of the object
     */
    private float y;
    /**
     * The angle that the object is facing
     */
    private float angle;
    /**
     * The x-coordinate of the origin of the bitmap
     */
    protected float xOrigin;
    /**
     * The y-coordinate of the origin of the bitmap
     */
    protected float yOrigin;
    /**
     * The ID of the used bitmap
     */
    protected int bitmapID;
    /**
     * The source {@link Rect} Rectangle of the bitmap
     */
    protected Rect srcRect;
    /**
     * The destination {@link Rect} Rectangle of the bitmap
     */
    protected Rect destRect;
    /**
     * The {@link Context} in which the object is created. This context
     * is used to load resources.
     */
    protected Context context;

    /**
     * Specifies the frame width that is used in the sprite
     */
    protected int spriteWidth;
    /**
     * Specifies the frame height that is used in the sprite
     */
    protected int spriteHeight;
    // Variables for sprite animation
    /**
     * Specifies whether the object should be animated or not
     */
    protected boolean aAnimate;
    /**
     * Specifies the number of frames in the spritesheet
     */
    protected int aFrameCount;
    /**
     * Specifies the current frame in the spritesheet
     */
    protected int aCurrentFrame;
    /**
     * Time of the last displayed frame
     */
    protected double aLastFrameChangeTime;  //Zeit, die das letzte Frame benoetigt hat
    /**
     * Specifies how long each frame should be displayed (in ms)
     */
    protected int aFrameLength;

    /**
     * The {@link ims.tuwien.ac.at.battleground.util.SpriteSheet} that represents
     * the drawable object
     */
    private SpriteSheet sprite;

    /**
     * The constructor of the DrawableObject
     *
     * @param x the x-coordinate of the object
     * @param y the y-coordinate of the object
     * @param bitmapId the id of the bitmap to be loaded
     */
    public DrawableObject(float x, float y, int bitmapId)
    {
        this.bitmapID = bitmapId;
        this.angle = 180;

        this.aAnimate = false;
        this.aCurrentFrame = 0;
        this.aLastFrameChangeTime = 0;
        this.aLastFrameChangeTime  = 25;

        loadBitmap(bitmapId);
        this.setX(x);
        this.setY(y);

        // We should not add the object here as we might create objects that are not part of the playingfield (for example the temporary objects during the building phase)
        // PF.addObject(this);     //add object to the playingField
    }

    /**
     * Sets the x and y origin of the sprite. In addition it creates
     * the {@link Rect} rectangles for the sprite.
     * @param bitmapId The id of the Bitmap to be loaded
     */
    private void loadBitmap(int bitmapId) {
        this.sprite = SPRITE_SHEET_MANAGER.getSpriteSheet(bitmapId);

        this.spriteHeight = sprite.getFrameHeight();
        this.spriteWidth = sprite.getFrameWidth();
        this.aFrameCount = sprite.getFrameCount();

        this.srcRect = new Rect(0, 0, this.getWidth(), this.getHeight());   //1. Frame auswaehlen
        this.destRect = new Rect((int)this.getX(), (int)this.getY(), this.getWidth() + (int)this.getX(), this.getHeight() + (int)this.getY());

        this.xOrigin = this.getWidth()/2;
        this.yOrigin = this.getHeight()/2;
    }

    /**
     * Updates the animation and specifies whether the animation should loop infinitely.
     * @param infiniteCycles True if the animation should repeat infinite, false if it should
     *                       only be animated once
     */
    public void updateAnimation(boolean infiniteCycles)
    {
        double currentTime = System.currentTimeMillis();
        if(aLastFrameChangeTime + aFrameLength < currentTime)
        {
            aLastFrameChangeTime = currentTime;
            aCurrentFrame = (aCurrentFrame + 1) % aFrameCount;

            //Animation nur 1x durchlaufen:
            if((infiniteCycles == false) && (aCurrentFrame >= aFrameCount-1))
            {
                aAnimate = false;
            }
        }

        //Frame-Ausschnitt updaten:
        srcRect.left = aCurrentFrame * spriteWidth;
        srcRect.right = aCurrentFrame * spriteWidth + spriteWidth;
    }

    /**
     * Updates the current object. It basically contains all steps that are executed
     * in each frame and is triggered by the {@link ims.tuwien.ac.at.battleground.game.GameLoop}.
     * @param tpf The timePerFrame that has passed
     */
    public abstract void update(float tpf);

    /**
     * Moves the object to the x and y coordinates that are passed to the method.
     * @param x The x-coordinate where the object should be moved
     * @param y The y-coordinate where the object should be moved
     */
    public void move(float x, float y)
    {
        setX(x + getX());
        setY(y + getY());

        this.destRect.left = (int)(this.getX() - xOrigin);
        this.destRect.right = (int)(this.getX() + this.getWidth() - xOrigin);
        this.destRect.top = (int)(this.getY() - yOrigin);
        this.destRect.bottom = (int)(this.getY() + this.getHeight() - yOrigin);
    }

    //Verschiebt das Objekt um distance[px] in Richtung direction[degrees]:

    /**
     * Moves the object in a direction by a specific distance
     * @param distance How far should the object be moved.
     * @param direction In which direction should the object be moved.
     */
    public void moveTowards(float distance, float direction)
    {
        float moveX = (float)Math.cos(Math.toRadians(direction)) * distance;
        float moveY = -(float)Math.sin(Math.toRadians(direction)) * distance;

        this.move(moveX, moveY);
    }


    /**
     * Kills the object and frees the resources.
     */
    public void die()
    {
        PF.addToDeadObjects(this);      //Objekt aus der entsprechenen PlayingField-ArrayList loeschen
    }

    //Winkel zw. Geraden(x,y, xDest,yDest) und x-Achse:

    /**
     * Returns the angle between the two points (x,y) and (xDest,yDest) that are passed to the
     * method
     *
     * @param x the x-coordinate of the object
     * @param y the y-coordinate of the object
     * @param xDest the x-coordinate of the destination
     * @param yDest the y-coordinate of the destination
     * @return The angle in between the two coordinates
     */
    public float calcAngle(float x, float y, float xDest, float yDest)
    {
        float deltaY = Math.abs(yDest - y);
        float deltaX = Math.abs(xDest - x);

        if(xDest >= x) {
            if (yDest >= y) {
                return (float)Math.toDegrees(2 * Math.PI - Math.atan(deltaY / deltaX));
            } else {
                return (float)Math.toDegrees(Math.atan(deltaY / deltaX));
            }
        }
        else {
            if (yDest >= y) {
                return (float)Math.toDegrees(Math.PI + Math.atan(deltaY / deltaX));
            } else {
                return (float)Math.toDegrees(Math.PI - Math.atan(deltaY / deltaX));
            }
        }
    }

    /**
     * Returns the x-coordinate of the object
     * @return The x-coordinate of the object
     */
    public float getX()
    {
        return x + xOrigin;
    }

    /**
     * Sets the x-coordinate of the object
     * @param x The x-coordinate of the object
     */
    public void setX(float x)
    {
        this.x = x - xOrigin;

        this.destRect.left = (int)(this.getX() - xOrigin);
        this.destRect.right = (int)(this.getX() + this.getWidth() - xOrigin);
    }

    /**
     * Returns the y-coordinate of the object
     * @return The y-coordinate of the object
     */
    public float getY()
    {
        return y + yOrigin;
    }

    /**
     * Sets the y-coordinate of the object
     * @param y The y-coordinate of the object
     */
    public void setY(float y)
    {
        this.y = y - yOrigin;

        this.destRect.top = (int)(this.getY() - yOrigin);
        this.destRect.bottom = (int)(this.getY() + this.getHeight() - yOrigin);
    }

    /**
     * Returns the origin x-coordinate of the object
     * @return The origin x-coordinate of the object
     */
    public float getXOrigin()
    {
        return xOrigin;
    }

    /**
     * Returns the origin y-coordinate of the object
     * @return The origin y-coordinate of the object
     */
    public float getYOrigin()
    {
        return yOrigin;
    }

    /**
     * Gets the angle that the object is facing
     * @return The angle that the object is facing
     */
    public float getAngle()
    {
        return angle;
    }

    /**
     * Sets the angle that the object is facing
     * @param angle The angle that the object is facing
     */
    public void setAngle(float angle)
    {
        this.angle = angle;
    }

    /**
     * Returns the width of the object
     * @return The width of the object
     */
    public int getWidth() {
        return this.spriteWidth;
    }

    /**
     * Returns the height of the object
     * @return The height of the object
     */
    public int getHeight(){
        return this.spriteHeight;
    }

    /**
     * Returns the source rectangle of the bitmap
     * @return The source rectangle of the bitmap
     */
    public Rect getSrcRect() {
        return srcRect;
    }

    /**
     * Returns the {@link Bitmap} bitmap of the object
     * @return The {@link Bitmap} bitmap of the object
     */
    public Bitmap getBitmap() {
        return this.sprite.getBitmap();
    }

    /**
     * Returns the destination rectangle of the bitmap
     * @return The destination rectangle of the bitmap
     */
    public Rect getDestRect() {
        return this.destRect;
    }

    /**
     * Returns true if the object is being animated, false otherwise
     * @return True if the object is animated, false otherwise
     */
    public boolean getAAnimate()
    {
        return this.aAnimate;
    }

    /**
     * Returns the number of frames in the spritesheet associated with this object
     * @return The number of frames in the spritesheet
     */
    public int getAFrameCount()
    {
        return this.aFrameCount;
    }

    /**
     * Returns a list with all subparts of the drawable object.
     * In case of the DrawableObject class it always returns null.
     * @return a list with all subparts of the drawable object
     */
    public List<DrawableObject> getSubParts() {
        return null;
    };
}
