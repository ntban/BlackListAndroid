package stupid.zon.BlackList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Zon on 20/10/2015.
 */
public class CustomToast {
    private Toast myToast;
    private Context mContext;
    private ImageView ivAgree, ivEject;

    private LayoutInflater inflater;

    public CustomToast(Context mContext) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
        initViews();
    }

    public void show(boolean block) {
        if (block) {
            myToast.setView(ivEject);
        } else {
            myToast.setView(ivAgree);
        }
        myToast.show();
    }

    private void initViews() {
        myToast = new Toast(mContext);
        myToast.setDuration(Toast.LENGTH_SHORT);
        View view = inflater.inflate(R.layout.toast_custom, null);
        ivAgree = (ImageView) view.findViewById(R.id.iv_agree);
        ivEject = (ImageView) view.findViewById(R.id.iv_eject);
    }
}
