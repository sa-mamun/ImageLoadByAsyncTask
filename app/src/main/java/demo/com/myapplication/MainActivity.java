package demo.com.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        String wallpaper = "https://i1.wp.com/www.iamabiker.com/wp-content/uploads/2017/01/Ducati-Scrambler-HD-wallpapers-1.jpg";
        new imageLoadingClass().execute(wallpaper);


    }

    public class imageLoadingClass extends AsyncTask<String, Void, Bitmap>
    {

        @Override
        protected Bitmap doInBackground(String... strings) {
            //background Thread

            String wallpaper = strings[0];

            try {
                URL url = new URL(wallpaper);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                httpURLConnection.connect();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            //main Thread
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            //main Thread

            imageView.setImageBitmap(bitmap);

            super.onPostExecute(bitmap);
        }
    }


}
