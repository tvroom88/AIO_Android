package AndroidBasic.DataBase.SQLiteDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "Student.db";
    public static final String TABLE_NAME = "stu";
    public static int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table if not exists "
                +  TABLE_NAME
                + "(id integer PRIMARY KEY autoincrement, "
                + " name text, "
                + " age integer, "
                + " major text)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > 1) {
            db.execSQL("DROP TABLE IF EXISTS emp");
            onCreate(db);
        }
    }

    public boolean insert(String name, int age, String major) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("name", name);
        contentValue.put("age", age);
        contentValue.put("major", major);
        long result = db.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public int delete(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(TABLE_NAME,  "name = ?", new String[]{name});
        return i;
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL("delete from "+ TABLE_NAME);
        db.delete(TABLE_NAME, null, null);
    }

    public int update(String name, int age, String major) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("name", name);
        contentValue.put("age", age);
        contentValue.put("major", major);
        int i = db.update(TABLE_NAME, contentValue, "name = ?", new String[]{name});
        return i;
    }


    public StringBuffer getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " +TABLE_NAME, null);

        StringBuffer sb = new StringBuffer();

        if(cursor.getCount() == 0){
            sb.append("아무 자료도 없습니다");
        }else{
            while(cursor.moveToNext()){
                sb.append("id    : ").append(cursor.getString(0)).append("\n");
                sb.append("name  : ").append(cursor.getString(1)).append("\n");
                sb.append("age   : ").append(cursor.getString(2)).append("\n");
                sb.append("major : ").append(cursor.getString(3)).append("\n\n");
            }
        }
        return sb;
    }

}
