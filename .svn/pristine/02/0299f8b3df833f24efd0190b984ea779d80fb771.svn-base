package ims.tuwien.ac.at.battleground.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import ims.tuwien.ac.at.battleground.persistence.ScoreContract.ScoreEntry;

/**
 * Helper class for SQLite Database. It specifies the structure of the database and how
 * it is created, updated or destroyed.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    /**
     * Specifies the version of the database schema. If the database schema
     * is changed, the database version must be incremented.
     */
    public static final int DATABASE_VERSION = 1;
    /**
     * Specifies the name of the database
     */
    public static final String DATABASE_NAME = "ScoreGame.db";

    /**
     * The create table statement.
     */
    private final String SQL_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS " + ScoreEntry.TABLE_NAME +
                                " (" + ScoreEntry._ID + " " + ScoreEntry.COLUMN_TYPE_ID + " autoincrement," +
                                ScoreEntry.COLUMN_NAME_USERNAME + " " + ScoreEntry.COLUMN_TYPE_USERNAME + "," +
                                ScoreEntry.COLUMN_NAME_SCORE + " " + ScoreEntry.COLUMN_TYPE_SCORE + ");";

    /**
     * The drop table statement.
     */
    private final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + ScoreEntry.TABLE_NAME;

    /**
     * The cunstructor of the MySQLiteHelper class
     * @param context The context in which this Helper is created
     */
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /**
     * This method is called upon the first access to the database
     * @param db The Database that should be created
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(MySQLiteHelper.class.getName(), "Creating tables in DB");
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    /**
     * Upgrades the database to a new version
     * @param db The database that should be updated
     * @param oldVersion The version of the old database
     * @param newVersion The version of the new database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(), "Upgrading database from version " +
                oldVersion + " to " + newVersion);
        dropTables(db);
        onCreate(db);
    }

    /**
     * Drops the tables in the database
     * @param db The database where the tables should be dropped
     */
    private void dropTables(SQLiteDatabase db) {
        Log.d(MySQLiteHelper.class.getName(), "Dropping all tables");
        db.execSQL(SQL_DELETE_ENTRIES);
    }
}
