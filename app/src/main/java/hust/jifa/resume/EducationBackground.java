package hust.jifa.resume;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by jfxu on 16/10/2.
 */
public class EducationBackground extends Activity {

    File f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edubg);
        shareDrawable();
    }

    public void onSpecialClick(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("学的更多，进度更快，更长的实习时间")
                .setPositiveButton("详情", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(EducationBackground.this, WebActivity.class);
                        intent.putExtra(IntentConstant.URL_EXTRA, "http://baike.baidu.com/link?url=9HKbYcOZgd-vBjUb3Zlg6QGfruGvCwiCtsloR9z8CXb2Dqyt1zAroPzKndbrdycBizJXkbjnCh0QZmuUiG3HpSiweCY4k3sdgQxtbNY6E1S27biUnYzA51tTV3UFxoF_MR3VUoYfx4jYhX1KiVOJfzMtayfytJG8DNkzBg__5hTZbNFFUz7SCnzZrbOIvJAZ9Lrf_RTmAtyQxzZYmH9jmFX5JFlJ3bPhMdxCfVIGpmlTAzCA9y24I0kWiUATVxlS4k81Y1kb05PjXZvcEqWRqqBF0ELXWNwHp4sQ5b6YR2-z_9Kz5nL4_oXd-HZasjqR");
                        startActivity(intent);
                    }
                }).setNegativeButton("关闭", null);
        builder.show();
    }

    public void onGradeClick(View view) {

        Uri path = Uri.fromFile(f);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(path, "image/*");
        startActivity(intent);

    }
    public void shareDrawable() {
        //convert drawable resource to bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.edu);

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, bytes);

        f = new File(Environment.getExternalStorageDirectory()
                + File.separator + "grade.jpg");
        try {
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
