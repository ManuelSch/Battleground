package ims.tuwien.ac.at.battleground.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

/**
 * Handles a single Bitmap that represents a SpriteSheet
 */
public class SpriteSheet {

    /**
     * Specifies the frame width that is used in the sprite
     */
    private int frameWidth;
    /**
     * Specifies the frame height that is used in the sprite
     */
    private int frameHeight;
    /**
     * Specifies the number of frames in the spritesheet
     */
    private int frameCount;

    /**
     * The {@link Bitmap} of the object
     */
    private Bitmap bitmap;

    /**
     * The ID of the bimap
     */
    private int bitmapID;


    /**
     * The constructor of the Spritesheet
     * @param context The context of the application
     * @param bitmapID The ID of the bitmap to be loaded
     * @param frameCount The number of frames in the spritesheet
     */
    public SpriteSheet(Context context, int bitmapID, int frameCount) {
        this.bitmapID = bitmapID;
        this.frameCount = frameCount;

        this.loadBitmap(context);
    }

    /**
     * Loads an bitmap according to its ID.
     *
     * @param context The context for the resources to load
     */
    public void loadBitmap(Context context) {
        this.bitmap = BitmapFactory.decodeResource(context.getResources(), this.bitmapID);
        this.frameHeight = this.bitmap.getHeight();
        this.frameWidth = this.bitmap.getWidth()/frameCount;
    }

    /**
     * Returns the ID of the Bitmap of this SpriteSheet
     * @return the ID of the Bitmap of this SpriteSheet
     */
    public int getBitmapID() {
        return bitmapID;
    }

    /**
      * Returns the height of one frame
      * @return the height of one frame
     */
    public int getFrameHeight() {
        return frameHeight;
    }

    /**
     * Returns the width of one frame
     * @return the width of one frame
     */
    public int getFrameWidth() {
        return frameWidth;
    }

    /**
     * Returns the number of frames
     * @return the number of frames
     */
    public int getFrameCount() {
        return frameCount;
    }

    /**
     * Returns the Bitmap
     * @return the Bitmap
     */
    public Bitmap getBitmap() {
        return bitmap;
    }

}
