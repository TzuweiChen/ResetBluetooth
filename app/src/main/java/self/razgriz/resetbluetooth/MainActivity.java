package self.razgriz.resetbluetooth;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

public class MainActivity extends Activity {

    private final int REQUEST_CODE_BLUETOOTH_SETTING = 9487;

    @SuppressWarnings("FieldCanBeLocal")
    private final String PACKAGE = "package";
    @SuppressWarnings("FieldCanBeLocal")
    private final String PACKAGE_NAME_ANDROID_BLUETOOTH = "com.android.bluetooth";
    @SuppressWarnings("FieldCanBeLocal")
    private final String PACKAGE_NAME_ANDROID_WEAR = "com.google.android.wearable.app";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts(PACKAGE, PACKAGE_NAME_ANDROID_BLUETOOTH, null));
        startActivityForResult(intent, REQUEST_CODE_BLUETOOTH_SETTING);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_BLUETOOTH_SETTING) {
            Intent intent = getPackageManager().getLaunchIntentForPackage(PACKAGE_NAME_ANDROID_WEAR);
            if (intent != null) {
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Android Wear application not found", Toast.LENGTH_SHORT).show();
            }
            MainActivity.this.finish();
        }
    }
}
