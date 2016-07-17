package ims.tuwien.ac.at.battleground.util;

import android.view.View;

import ims.tuwien.ac.at.battleground.game.Game;
import ims.tuwien.ac.at.battleground.game.util.BuildingType;

/**
 * This ClickListener is used for the Menu-Buttons. OnClick it sets the currently selected
 * building accordingly.
 * @author hoch
 */
public class CustomClickListener implements View.OnClickListener {
    private BuildingType towerType;

    /**
     * Constructor of the CustomClickListener
     * @param towerType The type of the Tower
     */
    public CustomClickListener(BuildingType towerType) {
        this.towerType = towerType;
    }

    /**
     * On selection (click) the corresponding TowerType is set
     * @param v The view of the event
     */
    @Override
    public void onClick(View v) {
        Game.GET_INSTANCE().setCurrentSelectedBuilding(this.towerType);
    }

}
