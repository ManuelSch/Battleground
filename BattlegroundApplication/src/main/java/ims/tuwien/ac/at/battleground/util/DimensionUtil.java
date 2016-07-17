package ims.tuwien.ac.at.battleground.util;

/**
 * This class is used to convert pixels to units and otherwise
 * @author Hoch
 */
public class DimensionUtil {

    /**
     * The dimension on the x-coordinate
     */
    public static int DIMENSION_X_PIXEL;
    /**
     * The dimension on the y-coordinate
     */
    public static int DIMENSION_Y_PIXEL;

    /**
     * The conversion rate from pixels to game units
     */
    public static float CONVERSION_RATE = 0.05f;

    /**
     * Returns the units in the game corresponding to the pixels
     * @param pixel the number of pixels
     * @return the units
     */
    public static int convertPixelToUnit(int pixel) {
        return (int)(pixel / CONVERSION_RATE);
    }

    /**
     * Returns the pixels corresponding to the in game units
     * @param unit the number of units
     * @return the number of pixels
     */
    public static int convertUnitToPixel(int unit) {
        return (int)(unit * CONVERSION_RATE);
    }

    /**
     * Scales values on the x-coordinate
     * @param x the number of pixels
     * @return the number of pixels scaled on the x-coordinate
     */
    public static float SCALE_X(float x) {
        return DIMENSION_X_PIXEL * x;
    }

    /**
     * Scales values on the y-coordinate
     * @param y the number of pixels
     * @return the number of pixels scaled on the y-coordinate
     */
    public static float SCALE_Y(float y) {
        return DIMENSION_Y_PIXEL * y;
    }

}
