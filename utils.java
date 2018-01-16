package in.bits_pilani.touchanalyze;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.MotionEvent;
import android.view.View;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class utils {
    public static final String PREFS_NAME = "TouchAnalyzePrefsFile";
    private static CSVWriter writer;

    public static CSVWriter analyze(Context context, String article, View v) {

        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        String id = settings.getString("id", "default");

        File fol = new File(android.os.Environment.getExternalStorageDirectory(), "research");
        if (!fol.exists()) {
            fol.mkdirs();
        }

        String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"research";
        String fileName = id+"_"+article+".csv";
        String filePath = baseDir + File.separator + fileName;
        File f = new File(filePath);
        FileWriter mFileWriter;

        try {
            if (f.exists() && !f.isDirectory()) {
                mFileWriter = new FileWriter(filePath, true);
                writer = new CSVWriter(mFileWriter);
            } else {
                writer = new CSVWriter(new FileWriter(filePath));
            }


            v.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    final int actionPeformed = event.getAction();

                    switch(actionPeformed){

                        case MotionEvent.ACTION_MOVE:{
                            final float x = event.getX();
                            final float y = event.getY();
                            final float p = event.getEventTime();
                            final float s= event.getSize();

                            String[] data = {Float.toString(x),Float.toString(y), Float.toString(p),Float.toString(s)};

                            writer.writeNext(data);
                            break;
                        }
                    }
                    return true;
                }
            });
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return writer;
    }
}
