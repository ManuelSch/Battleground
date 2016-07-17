package ims.tuwien.ac.at.battleground.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ims.tuwien.ac.at.battleground.R;
import ims.tuwien.ac.at.battleground.game.entity.GameObjectFactory;
import ims.tuwien.ac.at.battleground.game.entity.building.Tower;
import ims.tuwien.ac.at.battleground.game.entity.building.TowerGun;
import ims.tuwien.ac.at.battleground.game.entity.enemy.Enemy;
import ims.tuwien.ac.at.battleground.game.util.Constants;
import ims.tuwien.ac.at.battleground.game.entity.DrawableObject;
import ims.tuwien.ac.at.battleground.game.entity.HittableObject;
import ims.tuwien.ac.at.battleground.game.entity.building.Building;
import ims.tuwien.ac.at.battleground.game.util.GameState;
import ims.tuwien.ac.at.battleground.util.DimensionUtil;
import ims.tuwien.ac.at.battleground.util.SpriteSheet;

/**
 * This class presents the view that is used to display our playing field. It uses a canvas
 * to draw the objects on and creates our {@link GameLoop}.
 *
 * @author hoch
 * @author schueller
 */
public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Constants, View.OnDragListener {

    /**
     * Where we want to draw
     */
    Paint p;
    /**
     * Paint with alpha value
     */
    Paint pAlpha;

    /**
     * The sidelength of the playing field
     */
    static int GRID_SIDELENGTH;

    /**
     * Holds a reference to the currently selected and temporary created building
     */
    Building temporaryBuilding;

    /**
     * The constructor of the GameSurfaceView
     * @param context The context in which this View is created
     * @param attrSet The attributes that are passed along
     */
    public GameSurfaceView(Context context, AttributeSet attrSet) {
        super(context, attrSet);
        getHolder().addCallback(this);
        setFocusable(true);
        setOnDragListener(this);
        p = new Paint();
        pAlpha = new Paint();
        pAlpha.setAlpha(127);
        pAlpha.setStyle(Paint.Style.STROKE);
    }

    /**
     * Is called after the surface has been created. The {@link GameLoop} is
     * initialized here.
     * @param holder the holder of the canvas
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        DimensionUtil.DIMENSION_X_PIXEL = this.getWidth();
        DimensionUtil.DIMENSION_Y_PIXEL = this.getHeight();
        GRID_SIDELENGTH = this.getWidth()/10;

        /* PF.addObject(new FlakTower(DimensionUtil.SCALE_X(0.7f), DimensionUtil.SCALE_Y(0.2f)));
        PF.addObject(new FlakTower(DimensionUtil.SCALE_X(0.7f), DimensionUtil.SCALE_Y(0.4f)));
        PF.addObject(new FlakTower(DimensionUtil.SCALE_X(0.7f), DimensionUtil.SCALE_Y(0.6f)));
        PF.addObject(new FlakTower(DimensionUtil.SCALE_X(0.7f), DimensionUtil.SCALE_Y(0.8f)));

        PF.addObject(new RocketTower(DimensionUtil.SCALE_X(0.9f), DimensionUtil.SCALE_Y(0.1f)));
        PF.addObject(new Turret(DimensionUtil.SCALE_X(0.9f), DimensionUtil.SCALE_Y(0.2f)));
        PF.addObject(new RocketTower(DimensionUtil.SCALE_X(0.9f), DimensionUtil.SCALE_Y(0.3f)));
        PF.addObject(new Turret(DimensionUtil.SCALE_X(0.9f), DimensionUtil.SCALE_Y(0.4f)));
        PF.addObject(new RocketTower(DimensionUtil.SCALE_X(0.9f), DimensionUtil.SCALE_Y(0.5f)));
        PF.addObject(new Turret(DimensionUtil.SCALE_X(0.9f), DimensionUtil.SCALE_Y(0.6f)));
        PF.addObject(new RocketTower(DimensionUtil.SCALE_X(0.9f), DimensionUtil.SCALE_Y(0.7f)));
        PF.addObject(new Turret(DimensionUtil.SCALE_X(0.9f), DimensionUtil.SCALE_Y(0.8f)));
        PF.addObject(new RocketTower(DimensionUtil.SCALE_X(0.9f), DimensionUtil.SCALE_Y(0.9f)));*/

        GAME.setGameSurfaceView(this, holder);
    }

    /**
     * Returns the sidelength of the playing field
     * @return the sidelength of the playing field
     */
    public static int getGRID_SIDELENGTH()
    {
        return GRID_SIDELENGTH;
    }

    /**
     * Changes to the surface are handled here
     * @param holder The holder of the surface
     * @param format The format of the surface
     * @param width The width of the surface
     * @param height The height of the surface
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    /**
     * When the surface is destroyed the game is also ended
     * @param holder The holder of the surface
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        GAME.endGame();
    }

    /**
     * Handles onTouch events. Depending on the currently selected building it is checked
     * if the touched tile of the grid is available or not. If it is available the building is
     * created or, if it is the first selection, temporary placed there.
     * @param e The touch event being processed
     * @return True if the event can be handled
     */
    @Override
    public boolean onTouchEvent(MotionEvent e)
    {
        if(Game.GET_INSTANCE().getCurrentSelectedBuilding() == null) {
            return true;
        }
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            if (Game.GET_INSTANCE().getGameState().equals(GameState.BUILDING_PHASE)) {

                int xGrid = (int) e.getX() - (int) e.getX() % GRID_SIDELENGTH + GRID_SIDELENGTH / 2;
                int yGrid = (int) e.getY() - (int) e.getY() % GRID_SIDELENGTH + GRID_SIDELENGTH / 2;

                //Ist bereits ein tempor. Gebaeude am Spielfeld?
                if (temporaryBuilding != null) {
                    synchronized (temporaryBuilding) {
                        //Hat der Spieler auf das tempor. Gebaeude geklickt?
                        if (temporaryBuilding.getDestRect().contains((int) e.getX(), (int) e.getY())) {
                            //Tempor. Gebaeude durch eigentliches Gebaeude ersetzen:
                            Game.GET_INSTANCE().createBuilding(temporaryBuilding.getBuildingType(), xGrid, yGrid);
                            temporaryBuilding = null;
                        } else {
                            //Tempor. Gebaeude an diese Stelle verschieben:
                            temporaryBuilding = GameObjectFactory.getBuilding(Game.GET_INSTANCE().getCurrentSelectedBuilding(), xGrid, yGrid);
                            //temporaryBuilding = GameObjectFactory.getBuilding(Game.GET_INSTANCE().getCurrentSelectedBuilding(), (int) e.getX(), (int) e.getY());
                        }
                    }
                } else {
                    //Neues tempor. Gebaeude an dieser Stelle erstellen:
                    temporaryBuilding = GameObjectFactory.getBuilding(Game.GET_INSTANCE().getCurrentSelectedBuilding(), xGrid, yGrid);
                }

                //Ist die Position des tempor. Gebaeudes schon von einem anderen Gebaeude verbaut?
                if (temporaryBuilding != null) {
                    synchronized (temporaryBuilding) {
                        for (Building building : PF.getAllBuildings()) {
                            if (Rect.intersects(temporaryBuilding.getDestRect(), building.getDestRect())) {
                                temporaryBuilding = null;
                                break;
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    @Override
    public boolean onDrag(View v, DragEvent e) {
        return true;
    }

    /**
     * Draws the changes on a canvas. This basically draws a single frame. Here all elements
     * of the playing field are processed and, depending on their state, drawn on screen.
     * @param canvas The canvas that is used for drawing
     */
    @Override
    public void onDraw(Canvas canvas)
    {
        canvas.drawColor(Color.LTGRAY);
        this.drawBackground(canvas);

        //Alle DrawableObjects zeichnen:
        ArrayList<DrawableObject> allDrawableObjects = PF.getAllDrawableObjects();

        if(allDrawableObjects.size() != 0) {
            for (DrawableObject obj : allDrawableObjects)
            {
                if(obj instanceof Enemy) {
                    if(obj.getX() > this.getWidth() + this.getGRID_SIDELENGTH()/2) {
                        PF.addToDeadObjects(obj);
                        Game.GET_INSTANCE().getPlayer().loseLife();
                        if(Game.GET_INSTANCE().getPlayer().getLives()<=0)
                        {
                            Game.GET_INSTANCE().lostRound();
                            return;
                        }
                    }
                }

                this.drawObject(obj, canvas);

                List<DrawableObject> subParts = obj.getSubParts();
                if(subParts != null)
                {
                    for(DrawableObject subObj : obj.getSubParts()) {
                        if(subObj != null) {
                            this.drawObject(subObj, canvas);
                        }
                    }
                }
            }
        }

        if(temporaryBuilding != null && Game.GET_INSTANCE().getGameState().equals(GameState.BUILDING_PHASE)) {
            canvas.drawBitmap(temporaryBuilding.getBitmap(), temporaryBuilding.getSrcRect(), temporaryBuilding.getDestRect(), pAlpha);
            if(temporaryBuilding != null && temporaryBuilding.getSubParts() != null)
            {
                for(DrawableObject subObj : temporaryBuilding.getSubParts()) {
                    canvas.drawBitmap(subObj.getBitmap(), subObj.getSrcRect(), subObj.getDestRect(), pAlpha);
                    if(subObj instanceof TowerGun)
                    {
                        canvas.drawCircle(subObj.getX(), subObj.getY(), (float) ((TowerGun) subObj).getRange(), pAlpha);
                    }
                }
            }
        }

    }

    /**
     * Draws an object on the canvas. If the objects is drawn at an angle the canvas
     * is rotated.
     * @param obj The object to draw
     * @param canvas The canvas to be drawn on
     */
    private void drawObject(DrawableObject obj, Canvas canvas) {
        //Muss das aktuelle Objekt mit gedrehter Bitmap gezeichnet werden?
        if(obj.getAngle() != 0)
        {
            canvas.save(Canvas.MATRIX_SAVE_FLAG);           //Canvas-Status zwischenspeichern
            canvas.rotate(-obj.getAngle(), obj.getX(), obj.getY());
            canvas.drawBitmap(obj.getBitmap(), obj.getSrcRect(), obj.getDestRect(), p);
            canvas.restore();                               //Canvas-Status wiederholen
        }
        else {
            canvas.drawBitmap(obj.getBitmap(), obj.getSrcRect(), obj.getDestRect(), p);
        }

        //HealthBar fue jedes HittableObject zeichnen:
        if(obj instanceof HittableObject) {
            ((HittableObject) obj).drawHealthBar(canvas);
        }
    }

    /**
     * Draws the background on the canvas. The background is a grid layout of a single bitmap
     * that is drawn in this method.
     * @param canvas The canvas to be drawn on
     */
    public void drawBackground(Canvas canvas) {
        SpriteSheet kachel = SPRITE_SHEET_MANAGER.getSpriteSheet(R.drawable.kachel);

        Rect srcRect = new Rect(0, 0, kachel.getBitmap().getWidth(), kachel.getBitmap().getHeight());   //1. Frame auswaehlen
        int x = 0;
        int y = 0;
        int xDest = 0;
        int yDest = 0;
        while(x < this.getWidth()) {
            while(y < this.getHeight()) {
                xDest = x + kachel.getBitmap().getWidth();
                yDest = y + kachel.getBitmap().getHeight();
                Rect destRect = new Rect(x, y, xDest, yDest);
                y = yDest;
                canvas.drawBitmap(kachel.getBitmap(), srcRect, destRect, p);
            }
            y = 0;
            x = xDest;
        }
    }
}
