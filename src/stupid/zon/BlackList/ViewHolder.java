package stupid.zon.BlackList;

import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by Zon on 15/10/2015.
 */
public class ViewHolder {
    public TextView txtName;
    public TextView txtNumber;
    public Switch swtCancel;

    public ViewHolder(TextView txtName, TextView txtNumber, Switch swtCancel) {
        this.txtName = txtName;
        this.txtNumber = txtNumber;
        this.swtCancel = swtCancel;
    }
}
