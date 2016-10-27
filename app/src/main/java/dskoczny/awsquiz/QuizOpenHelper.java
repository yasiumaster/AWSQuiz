package dskoczny.awsquiz;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

public class QuizOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String QUESTION_TABLE_NAME = "questions";
    private static final String DATABASE_PATH = "/data/data/dskoczny.awsquiz/databases/";
    private static final String DATABASE_NAME = "awsquiz_db";
    private static QuizOpenHelper instance;
    private SQLiteDatabase dataBase;
    private final Context dbContext;

    private QuizOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.dbContext = context;
            try {
                this.getReadableDatabase();
                copyDataBase();
                this.close();
                openDataBase();

            } catch (IOException e) {
                throw new Error("Error copying database");
            }
            Toast.makeText(context, "Initial database is created", Toast.LENGTH_LONG).show();
    }

    public String getQuestion(int questionId) {
        Cursor cursor = dataBase.rawQuery("SELECT question FROM " + QUESTION_TABLE_NAME + " WHERE _id = " + questionId, null);
        cursor.moveToFirst();
        return cursor.getString(0);

    }

    public String getRandomQuestion() {
        Random r = new Random();
        Cursor cursor = dataBase.rawQuery("SELECT COUNT(*) FROM " + QUESTION_TABLE_NAME, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        int id;
//            Toast toast = Toast.makeText(dbContext, String.valueOf(count), Toast.LENGTH_LONG);
//            toast.show();
        do {
            id = r.nextInt(cursor.getInt(0)+1);
        } while(id==0);
        cursor = dataBase.rawQuery("SELECT question FROM " + QUESTION_TABLE_NAME + " WHERE _id = " + id, null);
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        boolean exist = false;
        try {
            String dbPath = DATABASE_PATH + DATABASE_NAME;
            checkDB = SQLiteDatabase.openDatabase(dbPath, null,
                    SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            Log.v("db log", "database does't exist");
        }

        if (checkDB != null) {
            Toast.makeText(dbContext, "DB Exists", Toast.LENGTH_LONG).show();
            exist = true;
            checkDB.close();
        }
        return exist;
    }

    public void openDataBase() throws SQLException {
        String dbPath = DATABASE_PATH + DATABASE_NAME;
        dataBase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    private void copyDataBase() throws IOException{
        InputStream myInput = dbContext.getAssets().open(DATABASE_NAME);
        String outFileName = DATABASE_PATH + DATABASE_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }


    public static QuizOpenHelper getInstance(Context context) {
        if(instance == null) {
          instance = new QuizOpenHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}