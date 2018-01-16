package in.bits_pilani.touchanalyze;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.opencsv.CSVWriter;

import java.io.IOException;

public class Article1 extends AppCompatActivity {

    private TextView tv1;
    private CSVWriter writer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article1);

        tv1=(TextView) findViewById(R.id.textView1);
        tv1.setText(R.string.article_1);

    }

    @Override
    public void onResume(){
        writer = utils.analyze(this.getApplicationContext(), "Article1", tv1);
        super.onResume();
    }


    @Override
    protected void onPause() {
        try {
            writer.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        super.onPause();
    }
}
