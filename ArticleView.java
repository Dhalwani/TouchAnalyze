package in.bits_pilani.touchanalyze;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ArticleView extends AppCompatActivity {
    public static final String PREFS_NAME = "TouchAnalyzePrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_view);

        TextView article1 = (TextView) findViewById(R.id.article1);
        TextView article2 = (TextView) findViewById(R.id.article2);
        Button logout = (Button) findViewById(R.id.logoutBtn);

        article1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ArticleView.this, Article1.class);
                startActivity(intent);
            }
        });

        article2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ArticleView.this, Article2.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("hasLoggedIn", false);
                editor.commit();

                Intent intent = new Intent();
                intent.setClass(ArticleView.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
