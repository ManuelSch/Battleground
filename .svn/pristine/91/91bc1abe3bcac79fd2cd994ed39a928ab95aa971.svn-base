package ims.tuwien.ac.at.battleground.util;

import android.content.ClipData;
import android.view.View;
import android.widget.ImageView;

import ims.tuwien.ac.at.battleground.R;

/**
 * @author hoch
 */
public class CustomLongClickListener implements View.OnLongClickListener {
    private int bitmapID;

    public CustomLongClickListener(int bitmapID) {
        this.bitmapID = bitmapID;
    }

    @Override
    public boolean onLongClick(View v) {
        String id = String.valueOf(bitmapID);
        ClipData.Item item = new ClipData.Item(v.getTag().toString());
        ClipData dragData = new ClipData(id, new String[]{ClipData.newPlainText(id, id).toString()}, item);

        //CustomDragShadowBuilder myShadow = new CustomDragShadowBuilder(v);
        View.DragShadowBuilder myShadow = new View.DragShadowBuilder(v);

        v.startDrag(dragData, myShadow, v, 0);
        return true;
    }
}
