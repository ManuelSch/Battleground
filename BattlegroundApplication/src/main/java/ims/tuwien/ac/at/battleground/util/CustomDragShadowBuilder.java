package ims.tuwien.ac.at.battleground.util;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Creates a custom Shadow for drag events
 * @author hoch
 */
public class CustomDragShadowBuilder extends View.DragShadowBuilder {

    private static Drawable shadow;

    public CustomDragShadowBuilder(View v) {
        super(v);
        shadow = new ColorDrawable(Color.RED);
    }


    @Override
    public void onProvideShadowMetrics (Point size, Point touch) {
        super.onProvideShadowMetrics(size, touch);
    }

    // Defines a callback that draws the drag shadow in a Canvas that the system constructs
    // from the dimensions passed in onProvideShadowMetrics().
    @Override
    public void onDrawShadow(Canvas canvas) {
        shadow.draw(canvas);
    }
}