package TestCloseResources;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import Util.QHRecyleUtils;

/**
 * Created by yuanwei3-iri on 2017/5/18.
 */

public class TestAndroidRule{


    public void test_01_BUG(){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("stu.db", null);
        String stu_table="create table usertable(_id integer primary key autoincrement,sname text,snumber text)";
        db.execSQL(stu_table);
        String stu_sql="insert into stu_table(sname,snumber) values('xiaoming','01005')";
        //执行SQL语句
        db.execSQL(stu_sql);
    }

    public void test_02_NOBUG(){
        SQLiteDatabase db = null;
        try{
            db = SQLiteDatabase.openOrCreateDatabase("stu.db", null);
            String stu_table="create table usertable(_id integer primary key autoincrement,sname text,snumber text)";
            db.execSQL(stu_table);
            String stu_sql="insert into stu_table(sname,snumber) values('xiaoming','01005')";
            db.execSQL(stu_sql);
        } catch (SQLiteException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }finally{
            QHRecyleUtils.safeClose(db);
        }
    }
    public void test_03_BUG(){
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try{
            db = SQLiteDatabase.openOrCreateDatabase("stu.db", null);
            cursor = db.rawQuery("select * from usertable",null,null);
        } catch (SQLiteException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } finally{

        }
    }

}
