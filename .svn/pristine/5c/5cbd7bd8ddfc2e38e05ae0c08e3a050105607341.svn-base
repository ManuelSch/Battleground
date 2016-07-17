package ims.tuwien.ac.at.battleground.persistence;

import android.provider.BaseColumns;

/**
 * A contract class is a container for constants that define names for URIs, tables, and columns.
 */
public class ScoreContract {

    /**
     * Provides constants for an entry in Score table. BaseColumns interface declares _ID = "id" constant.
     */
    public static abstract class ScoreEntry implements BaseColumns {
        /**
         * The name of the Table
         */
        public static final String TABLE_NAME = "highscore";

        /**
         * The type of the primary key
         */
        public static final String COLUMN_TYPE_ID = "INTEGER PRIMARY KEY";

        /**
         * Column name for the user
         */
        public static final String COLUMN_NAME_USERNAME = "username";
        /**
         * Column type for the user
         */
        public static final String COLUMN_TYPE_USERNAME = "TEXT";

        /**
         * Column name for the score
         */
        public static final String COLUMN_NAME_SCORE = "score";
        /**
         * Column type for the scores
         */
        public static final String COLUMN_TYPE_SCORE = "INTEGER";
    }

}
