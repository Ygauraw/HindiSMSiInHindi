package rshankar.smsbook.database;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import rshankar.hindismsinhindi.MainActivity;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper
{
private static String TAG = "TAG";

private static final String MESSAGE_TABLE = "messages";
private static final String CATEGORY_TABLE = "categories";



private static String DB_PATH = "/data/data/rshankar.hindismsinhindi/databases/";
private static String DB_NAME = "HindiSMS";
private static final int DB_VERSION =06;//this version with Market version 2.2, android:versionCode="4"
private SQLiteDatabase mDataBase; 
private final Context mContext;
public DataBaseHelper(Context context) 
{
    super(context, DB_NAME, null, DB_VERSION);
    DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
    this.mContext = context;

}   

public void createDataBase() throws IOException
{
    boolean mDataBaseExist = checkDataBase();
    if(!mDataBaseExist)
    {
        this.getReadableDatabase();
        try 
        {
            copyDataBase();
            Editor editor = MainActivity.sharedpreferences.edit();
        	editor.putInt("last_DB_version", DB_VERSION);
        	editor.commit();
        } 
        catch (IOException mIOException) 
        {
            throw new Error("ErrorCopyingDataBase");
        }
    }else{
    
    	if(DB_VERSION!=MainActivity.sharedpreferences.getInt("last_DB_version", 0)){
    		 copyDataBase();
             Editor editor = MainActivity.sharedpreferences.edit();
         	editor.putInt("last_DB_version", DB_VERSION);
         	editor.commit();
    	}
    	 
    	}
    }

private boolean checkDataBase()
{
    SQLiteDatabase mCheckDataBase = null;
    try
    {
        String myPath = DB_PATH + DB_NAME;
        mCheckDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
    }
    catch(SQLiteException mSQLiteException)
    {
        Log.e(TAG, "DatabaseNotFound " + mSQLiteException.toString());
    }

    if(mCheckDataBase != null)
    {
        mCheckDataBase.close();
    }
    return mCheckDataBase != null;
}

private void copyDataBase() throws IOException
{
    InputStream mInput = mContext.getAssets().open(DB_NAME);
    String outFileName = DB_PATH + DB_NAME;
    OutputStream mOutput = new FileOutputStream(outFileName);
    byte[] mBuffer = new byte[1024];
    int mLength;
    while ((mLength = mInput.read(mBuffer))>0)
    {
        mOutput.write(mBuffer, 0, mLength);
    }
    mOutput.flush();
    mOutput.close();
    mInput.close();
}

public boolean openDataBase() throws SQLException
{
    String mPath = DB_PATH + DB_NAME;
    mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
    return mDataBase != null;
}

@Override
public synchronized void close() 
{
    if(mDataBase != null)
        mDataBase.close();
    super.close();
}

@Override
public void onCreate(SQLiteDatabase db) 
{ 
	
}

@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
{
	
    // create fresh books table
//    this.onCreate(db);
    Log.v(TAG, "UpgradingDatabase, This will drop current database and will recreate it");
}
}