package stupid.zon.BlackList;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener, View.OnKeyListener {
    private ListView lvContacts;
    private FrameLayout flAction, flContent, flSearch;
    private EditText edtSearch;
    private Button btnCancel;
    private ImageView ivSearch, ivDown;

    private boolean searching = false, downing = false;
    private DatabaseManager dbManager;
    private ContactAdapter adapter;
    private ArrayList<ContactNumber> contacts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    public void updateListView(ContactNumber contact) {
        String name = contact.getName();
        dbManager.editBlackList(name, contact);
        contacts = dbManager.getBlackContact("YES");
        contacts.addAll(dbManager.getBlackContact("NO"));
        adapter.setContactNumbers(contacts);
        adapter.notifyDataSetChanged();
    }

    private void initViews() {
        //for action bar on top
        flSearch = (FrameLayout) findViewById(R.id.fl_search);
        flAction = (FrameLayout) findViewById(R.id.fl_action);
        flContent = (FrameLayout) findViewById(R.id.fl_contain);
        flAction.setOnClickListener(this);
        btnCancel = (Button) findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(this);
        edtSearch = (EditText) findViewById(R.id.txt_search);
//        edtSearch.setOnKeyListener(this);
        ivSearch = (ImageView) findViewById(R.id.iv_search);
        ivDown = (ImageView) findViewById(R.id.iv_down);
        ivSearch.setOnClickListener(this);
        ivDown.setOnClickListener(this);

        //for list view
        dbManager = new DatabaseManager(this);
        adapter = new ContactAdapter(this);
        adapter.setMainActivity(this);
        contacts = dbManager.getBlackContact("YES");
        contacts.addAll(dbManager.getBlackContact("NO"));
        adapter.setContactNumbers(contacts);
        lvContacts = (ListView) findViewById(R.id.lv_contact);
        lvContacts.setAdapter(adapter);
    }

    private void searchOrNot() {
        searching = !searching;
        if (searching) {
            edtSearch.setText("");
            flSearch.setVisibility(View.VISIBLE);
            flContent.setVisibility(View.GONE);
        } else {
            flSearch.setVisibility(View.GONE);
            flContent.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                searchOrNot();
                break;
            case R.id.iv_down:
                downing = !downing;
                int downVisible = (downing) ? View.VISIBLE : View.GONE;
                flAction.setVisibility(downVisible);
                break;
            case R.id.iv_search:
                searchOrNot();
                break;
            case R.id.fl_action:
                //add
                break;
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_ENTER:
                searchOrNot();
        }
        return true;
    }
}
