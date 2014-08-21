package rshankar.smsbook.database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rshankar.hindismsinhindi.dataclasses.Category;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.util.Log;

public class MessageDBAdapter 
{
protected static final String TAG = "MessageDBAdapter";

private final Context mContext;
private SQLiteDatabase mDb;
private DataBaseHelper mDbHelper;

private static final String MESSAGE_TABLE = "messages";
private static final String CATEGORY_TABLE = "categories";
private static final String LANGUAGE_TABLE = "language";
private static final String MASTER_MESSAGE_TABLE = "message_master";
private static final String MASTER_CATEGORY_TABLE = "caterory_master";

private static final String CATEGORY_ID = "categoryId";
private static final String CATEGORY_NAME = "categoryName";

private static final String MESSAGE_ID = "messageId";
private static final String MESSAGE = "message";

private static final String MESSAGE_MASTER_ID = "mess_mas_id";
private static final String CATEGORY_MASTER_NAME = "cat_mas_name";
private Cursor mCoursor;
SharedPreferences sharedPrefs;
public MessageDBAdapter(Context context) 
{
    this.mContext = context;
    mDbHelper = new DataBaseHelper(mContext);
    sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.mContext);
}

public MessageDBAdapter createDatabase() throws SQLException 
{
    try 
    {
        mDbHelper.createDataBase();
    } 
    catch (IOException mIOException) 
    {
        Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
        throw new Error("UnableToCreateDatabase");
    }
    return this;
}

public MessageDBAdapter open() throws SQLException 
{
    try 
    {
        mDbHelper.openDataBase();
        mDbHelper.close();
        mDb = mDbHelper.getReadableDatabase();
    } 
    catch (SQLException mSQLException) 
    {
        Log.e(TAG, mSQLException.toString());
        throw mSQLException;
    }
    return this;
}

public void closargCatArre() 
{
    mDbHelper.close();
}

public int getMessageCount() 
{
	mCoursor = mDb.query(LANGUAGE_TABLE, new String[] {}, null, null, null, null, null);
    int mMessageCount = mCoursor.getCount();
    mCoursor.close();
    return mMessageCount;
}


//	public String retriveLanguagById(int mPosition)
//	{
//		Cursor mCursor = mDb.query(LANGUAGE_TABLE, new String[] {LANGUAGE_NAME}, LANGUAGE_ID+"="+mPosition , null, null, null, null);
//		  mCursor.moveToFirst();
//		  String mReturn = mCursor.getString(mCursor.getColumnIndex(LANGUAGE_NAME));
//		  mCursor.close();
//		  return mReturn;
//	}
//	public List<Language> retriveAllLanguage(){
//		List<Language> tempLanguages=new ArrayList<Language>();
//		Language tempLanguage;
//		  Cursor mCursor = mDb.query(LANGUAGE_TABLE, new String[] {}, null , null, null, null, null);
//		  String mReturn[]=new String[mCursor.getCount()];
//		  mCursor.moveToFirst();
//		  for(int i=0;i<mCursor.getCount();i++){
//			  
//			  tempLanguage=new Language(mCursor.getInt(mCursor.getColumnIndex(LANGUAGE_ID)),mCursor.getString(mCursor.getColumnIndex(LANGUAGE_NAME)));
//			  tempLanguages.add(tempLanguage);
//		  mCursor.moveToNext();
//		  }
//		  mCursor.close();
//		  return tempLanguages;
//	}

	
	public long insertCategory(String category_name){
		ContentValues initialValues = new ContentValues();
		initialValues.put(CATEGORY_NAME, category_name);
		return mDb.insert(CATEGORY_TABLE, null, initialValues);
	}
	public boolean updateCategory(int mPosition, String updated_category_name) 
	{
	  ContentValues initialValues = new ContentValues();
	  initialValues.put(CATEGORY_ID ,mPosition);
	  initialValues.put(CATEGORY_NAME, updated_category_name);
	  return mDb.update(CATEGORY_TABLE, initialValues, CATEGORY_ID+"="+mPosition, null) > 0;
	}
	public String retriveCategoryById(int mPosition)
	{
		Cursor mCursor = mDb.query(CATEGORY_TABLE, new String[] {CATEGORY_NAME}, CATEGORY_ID+"="+mPosition , null, null, null, null);
		  mCursor.moveToFirst();
		  String mReturn = mCursor.getString(mCursor.getColumnIndex(CATEGORY_NAME));
		  mCursor.close();
		  return mReturn;
	}
	public List<Category> retriveAllCategory(){
		List<Category> tempCategories=new ArrayList<Category>();
		Category tempCategory;
		Cursor mCursor = mDb.query(CATEGORY_TABLE, new String[] {}, null , null, null, null, null);
		 
		  mCursor.moveToFirst();
		  for(int i=0;i<mCursor.getCount();i++){
			  tempCategory=new Category(mCursor.getInt(mCursor.getColumnIndex(CATEGORY_ID)),mCursor.getString(mCursor.getColumnIndex(CATEGORY_NAME)));
			  tempCategories.add(tempCategory);
		  mCursor.moveToNext();
		  }
		  mCursor.close();
		  return tempCategories;
	}
//	public List<Category> retriveAllCategoryByLanguageId(int language_id){
//		List<Category> tempCategories=new ArrayList<Category>();
//		Category tempCategory;
//		  Cursor mCursor = mDb.query(MASTER_CATEGORY_TABLE, new String[] {}, LANGUAGE_ID+"="+language_id  , null, null, null, null);
//		  int tempCount=mCursor.getCount();
//		  mCursor.moveToFirst();
//		  for(int i=0;i<mCursor.getCount();i++){
//			  tempCategory=new Category(mCursor.getInt(mCursor.getColumnIndex("caterory_id")),mCursor.getString(mCursor.getColumnIndex(CATEGORY_MASTER_NAME)));
//			  tempCategories.add(tempCategory);
//		  mCursor.moveToNext();
//		  }
//		  mCursor.close();
//		  return tempCategories;
//	}


	public long insertMessage(int language_id,int category_id,String message,int fevorite){
		ContentValues initialValues = new ContentValues();
		initialValues.put(CATEGORY_ID, category_id);
		initialValues.put(MESSAGE, message);
		return mDb.insert(MESSAGE_TABLE, null, initialValues);
	}
	public boolean updateMessageFevorite(int mPosition, int updated_fevorite) 
	{
	  ContentValues initialValues = new ContentValues();
	  initialValues.put(MESSAGE_ID ,mPosition);
	  return mDb.update(MESSAGE_TABLE, initialValues, MESSAGE_ID+"="+mPosition, null) > 0;
	}
	public String retriveMessageById(int mPosition)
	{
		Cursor mCursor = mDb.query(MESSAGE_TABLE, new String[] {MESSAGE}, MESSAGE_ID+"="+mPosition , null, null, null, null);
		  mCursor.moveToFirst();
		  String mReturn = mCursor.getString(mCursor.getColumnIndex(MESSAGE));
		  mCursor.close();
		  return mReturn;
	}
	public Cursor retriveMessagesByLanguaIdAndCategoryId(int lang_id,int cat_id)
	{
	  Cursor mCursor = mDb.query(MESSAGE_TABLE, new String[] {},MESSAGE_MASTER_ID+"="+retriveMasterMessageId(lang_id, cat_id), null, null, null, "favorite"+" DESC");
	  return mCursor;
	}
	
	public int retriveMasterMessageId(int lang_id,int cat_id)
	{
	  Cursor mCursor = mDb.query(MASTER_MESSAGE_TABLE, new String[] {MESSAGE_MASTER_ID},"language_id=" + lang_id +" AND category_id="+cat_id, null, null, null, null);
	  mCursor.moveToFirst();
	  int master_id=mCursor.getInt(mCursor.getColumnIndex(MESSAGE_MASTER_ID));
	  return master_id;
	}
	
	public String[] retriveAllMessage(int categoryId){
		
		  Cursor mCursor = mDb.query(MESSAGE_TABLE, new String[] {}, CATEGORY_ID+"="+categoryId , null, null, null, null);
		  String mReturn[]=new String[mCursor.getCount()];
		  mCursor.moveToFirst();
		  for(int i=0;i<mCursor.getCount();i++){
			  mReturn[i] = mCursor.getString(mCursor.getColumnIndex(MESSAGE));
		  mCursor.moveToNext();
		  }
		  mCursor.close();
		  return mReturn;
	}
	
	public String getCategoryName(int categoryId,int languageId){
		
		  Cursor mCursor = mDb.query(MASTER_CATEGORY_TABLE, new String[] {}, "caterory_id="+categoryId+" AND language_id="+languageId, null, null, null, null);
		  String mReturn=new String();
		  mCursor.moveToFirst();
	
			  mReturn = mCursor.getString(mCursor.getColumnIndex(CATEGORY_MASTER_NAME));
		  mCursor.moveToNext();
		  mCursor.close();  
		
		  return mReturn;
	}
	public String getMessageOFTheDay(){
		
		 Cursor mCursor = mDb.query(MESSAGE_TABLE, new String[] {},MESSAGE_MASTER_ID+"="+retriveMasterMessageId(Integer.parseInt(sharedPrefs.getString("language_list","1")),Integer.parseInt(sharedPrefs.getString("category_list","1"))), null, null, null, null);
		int position=new Random().nextInt(mCursor.getCount()-1);
		 mCursor.moveToPosition(position);

		 String temp=mCursor.getString(mCursor.getColumnIndex("message_text"));
		  return temp;
	}

}