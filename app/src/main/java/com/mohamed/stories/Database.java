package com.mohamed.stories;

public class Database extends android.database.sqlite.SQLiteOpenHelper {

    public static final String DB_NAME = "stories.db";
    public static final int DB_VER = 1;
    public static final String DB_LOCATION = android.os.Environment.getDataDirectory() + "/data/data/com.mohamed.stories/databases/";
    private android.content.Context mContext;
    private android.database.sqlite.SQLiteDatabase mDatabase;

    public Database(android.content.Context context) {
        super(context, DB_NAME, null, DB_VER);
        this.mContext = context;
    }

    @Override
    public void onCreate(android.database.sqlite.SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DB_NAME).getPath();
        if (mDatabase != null && mDatabase.isOpen()) {
            return;
        } else {
            mDatabase = android.database.sqlite.SQLiteDatabase.openDatabase(dbPath, null, android.database.sqlite.SQLiteDatabase.OPEN_READWRITE);
        }
    }

    public void closeDatabase() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    public java.util.ArrayList get_All_Titles() {


        java.util.ArrayList arrayList = new java.util.ArrayList();
        openDatabase();
        android.database.Cursor res = mDatabase.rawQuery("select * from elanbyaa", null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            arrayList.add(res.getString(res.getColumnIndex("title")));
            res.moveToNext();
        }
        res.close();
        closeDatabase();
        return arrayList;

    }

    public String get_Full_Story(String title) {
        String full_Story;
        openDatabase();
        android.database.Cursor res = mDatabase.rawQuery("select * from elanbyaa where title like '" + title + "'", null);
        res.moveToFirst();
        full_Story = res.getString(res.getColumnIndex("story"));
        res.close();
        closeDatabase();
        return full_Story;
    }



}
