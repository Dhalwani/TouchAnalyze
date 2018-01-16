package in.bits_pilani.touchanalyze;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends RuntimePermissionsActivity {

    private static final int REQUEST_PERMISSIONS = 20;
    public static final String PREFS_NAME = "TouchAnalyzePrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivity.super.requestAppPermissions(new
                        String[]{Manifest.permission.INTERNET,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        R.string.runtime_permissions_txt, REQUEST_PERMISSIONS);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean hasLoggedIn = settings.getBoolean("hasLoggedIn", false);

        if(hasLoggedIn)
        {
            forward();
        }
        else {

            setContentView(R.layout.activity_main);
            final SharedPreferences.Editor editor = settings.edit();

            final EditText name = (EditText)findViewById(R.id.nameText);
            final EditText id = (EditText)findViewById(R.id.idText);
            Button submit = (Button)findViewById(R.id.submit);

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.putBoolean("hasLoggedIn", true);
                    editor.putString("name", name.getText().toString());
                    editor.putString("id", id.getText().toString());
                    editor.commit();

                    forward();
                }
            });

        }

    }

    @Override
    public void onPermissionsGranted(final int requestCode) {
    }

    public void forward() {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, ArticleView.class);
        startActivity(intent);
        this.finish();
    }
}
