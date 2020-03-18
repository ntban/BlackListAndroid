package stupid.zon.BlackList;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Zon on 20/10/2015.
 */
public class CustomDialog extends Dialog implements View.OnClickListener {
    private TextView txtConfirm;
    private Button btnDelete, btnCancel;

    public void setTxtConfirm(String txt) {
        txtConfirm.setText(txt);
    }

    public CustomDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_custom);
        initViews();
    }

    private void initViews() {
        txtConfirm = (TextView) findViewById(R.id.txt_confirm);
        btnCancel = (Button)findViewById(R.id.btn_cancel);
        btnDelete = (Button)findViewById(R.id.btn_detele);
        btnCancel.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    private boolean delete = false;

    public boolean isDelete() {
        return delete;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_cancel:
                delete = false;
                this.dismiss();
                break;
            case R.id.btn_detele:
                delete = true;
                this.dismiss();
                break;
        }
    }
}
