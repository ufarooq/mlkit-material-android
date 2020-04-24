package com.google.firebase.ml.md.java.ext;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.ml.md.R;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

public class ImageDetectionUtils {
  public static void saveBitmap(Context context, View view, Bitmap bitmap) {

    Snackbar snackbar =
        Snackbar.make(view, "Save Image?", Snackbar.LENGTH_LONG)
            .setAction(
                "Dismiss",
                v -> {
                  // Respond to the click,
                })
            .setAction(
                "Save",
                v -> {
                  String root = Environment.getExternalStorageDirectory().toString();
                  File myDir = new File(root + "/detected_images");
                  if (!myDir.exists()) {
                    myDir.mkdirs();
                  }

                  long n = Calendar.getInstance().getTimeInMillis();
                  String fname = "Image-" + n + ".jpg";
                  File file = new File(myDir, fname);
                  if (file.exists()) file.delete();
                  try {
                    FileOutputStream out = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                    out.flush();
                    out.close();

                  } catch (Exception e) {
                    e.printStackTrace();
                  }
                  Toast.makeText(context, "Image Saved", Toast.LENGTH_LONG).show();
                });
    snackbar.setBackgroundTint(ContextCompat.getColor(context, R.color.black));
    snackbar.setTextColor(
        ContextCompat.getColor(context, R.color.common_google_signin_btn_text_dark));
    // snackbar.setActionTextColor(ContextCompat.getColor(context,
    // R.color.common_google_signin_btn_tint));
    snackbar.setActionTextColor(Color.MAGENTA);
    snackbar.show();
  }
}
