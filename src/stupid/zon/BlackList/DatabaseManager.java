package stupid.zon.BlackList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Zon on 15/10/2015.
 */
public class DatabaseManager {
    private static final String DATA_PATH =
            Environment.getDataDirectory().getPath() + "/data/stupid.zon.BlackList/databases";
    private static final String DATA_NAME = "black_list.sqlite", TABLE_NAME = "tblBlack";
    private static final String TAG_DB = "database_manager_tab:";
    private static final String COL_NAME = "name",
            COL_TYPE = "type", COL_NUMBER = "number";
    private Context mContext;
    private SQLiteDatabase mSQLite;

    public DatabaseManager(Context context) {
        this.mContext = context;
        copyDatabase();
    }

    private void copyDatabase() {
        try {
            new File(DATA_PATH).mkdirs();
            File file = new File((DATA_PATH + File.separator + DATA_NAME));
            if (file.exists()) {
                Log.i(TAG_DB, "The file has exist");
                return;
            }
            file.createNewFile();
            InputStream input = mContext.getAssets().open(DATA_NAME);
            OutputStream output = new FileOutputStream(file);
            int length;
            byte buffer[] = new byte[2048];
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            input.close();
            output.close();
            Log.i(TAG_DB, "The file has created");
        } catch (Exception e) {
            Log.i(TAG_DB, e.toString());
        }
    }

    private void openDatabase() {
        if (mSQLite == null || !mSQLite.isOpen()) {
            mSQLite = SQLiteDatabase.openDatabase(DATA_PATH + File.separator
                    + DATA_NAME, null, SQLiteDatabase.OPEN_READWRITE);
        }
    }

    private void closeDatabase() {
        if (mSQLite != null && mSQLite.isOpen()) {
            mSQLite.close();
        }
    }

    public ArrayList<ContactNumber> getBlackContact(String type) {
        openDatabase();
        ArrayList<ContactNumber> contacts = new ArrayList<>();
        String sql = "SELECT * FROM tblBlack WHERE type = ?";
        Cursor cursor = mSQLite.rawQuery(sql, new String[]{type});
        if (cursor == null) {
            Toast.makeText(mContext, "No contacts", Toast.LENGTH_SHORT).show();
            return contacts;
        }
        cursor.moveToFirst();
        int colName = cursor.getColumnIndex(COL_NAME),
                colType = cursor.getColumnIndex(COL_TYPE),
                colNumber = cursor.getColumnIndex(COL_NUMBER);
        while (!cursor.isAfterLast()) {
            contacts.add(new ContactNumber(cursor.getString(colName), cursor.getString(colNumber),
                    cursor.getString(colType)));
            cursor.moveToNext();
        }
        cursor.close();
        Log.i(TAG_DB, "Size: " + contacts.size());
        return contacts;
    }

    public void addBlackList(ContactNumber contact) {
        openDatabase();
        try {
            ContentValues content = new ContentValues();
            content.put(COL_NAME, contact.getName());
            content.put(COL_NUMBER, contact.getNumber());
            content.put(COL_TYPE, contact.getType());
            if (mSQLite.insert(TABLE_NAME, null, content) > 0) {
                Toast.makeText(mContext, "Insert success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mContext, "Insert fail", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(mContext, "Has error", Toast.LENGTH_SHORT).show();
            Log.i(TAG_DB, e.toString());
        }
    }

    public void editBlackList(String name, ContactNumber contact) {
        openDatabase();
        try {
            ContentValues content = new ContentValues();
            content.put(COL_NAME, contact.getName());
            content.put(COL_NUMBER, contact.getNumber());
            content.put(COL_TYPE, contact.getType());
            if (mSQLite.update(TABLE_NAME, content, " name = '" + name + "'", null) > 0) {
                Log.i(TAG_DB, "Success " + name);
                Toast.makeText(mContext, "Update success", Toast.LENGTH_SHORT).show();
            } else {
                Log.i(TAG_DB, "Fail " + name);
                Toast.makeText(mContext, "Update fail", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(mContext, "Has error", Toast.LENGTH_SHORT).show();
            Log.i(TAG_DB, e.toString());
        }
    }

    public void removeBlackList(ContactNumber contact) {
        try {
            openDatabase();
            mSQLite.delete(TABLE_NAME, COL_NAME + " = " + contact.getName(), null);
        } catch (Exception e) {
            Toast.makeText(mContext, "Has error", Toast.LENGTH_SHORT).show();
            Log.i(TAG_DB, e.toString());
        }
    }
}
