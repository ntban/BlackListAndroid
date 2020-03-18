package stupid.zon.BlackList;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;

/**
 * Created by Zon on 15/10/2015.
 */
public class ContactAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater layoutInflater;
    private ArrayList<ContactNumber> contactNumbers = new ArrayList<>();
    private MainActivity mainActivity;

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void setContactNumbers(ArrayList<ContactNumber> contactNumbers) {
        this.contactNumbers = contactNumbers;
    }

    public ContactAdapter(Context context) {
        this.mContext = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return contactNumbers.size();
    }

    @Override
    public ContactNumber getItem(int position) {
        return contactNumbers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder = null;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_listview, null);
            TextView txtName = (TextView) view.findViewById(R.id.txt_name);
            TextView txtNumbber = (TextView) view.findViewById(R.id.txt_number);
            Switch swtCancel = (Switch) view.findViewById(R.id.swt_cancel);
            holder = new ViewHolder(txtName, txtNumbber, swtCancel);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        ContactNumber contact = contactNumbers.get(position);
        holder.txtName.setText(contact.getName());
        holder.txtNumber.setText("+" + contact.getNumber());
        holder.swtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contact.isOn()) {
                    contact.setIsOn(false);
                }else{
                    contact.setIsOn(true);
                }
                mainActivity.updateListView(contact);
                notifyDataSetChanged();
            }
        });
        if (contact.isOn()) {
            holder.swtCancel.setChecked(true);
            holder.txtName.setTextColor(Color.RED);
        } else {
            holder.swtCancel.setChecked(false);
            holder.txtName.setTextColor(Color.BLUE);
        }
        return view;
    }


}
