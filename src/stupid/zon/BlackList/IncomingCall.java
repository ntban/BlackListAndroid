package stupid.zon.BlackList;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

/**
 * Created by Zon on 20/10/2015.
 */
public class IncomingCall extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String phone = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

    }
}
