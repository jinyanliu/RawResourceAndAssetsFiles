package se.sugarest.jane.rawresourceandassetsfiles;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readRaw();
        readRawScanner();

        readAsset();
        readAssetScanner();
    }

    // Read line by line
    private void readRaw() {
        try {
            InputStream inputStream = this.getResources().openRawResource(R.raw.myraw);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = bufferedReader.readLine();
            while (line != null) {
                Log.i("My raw line: ", line);
                line = bufferedReader.readLine();
            }

            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();

        } catch (Exception e) {
            Log.e("Test", e.getMessage());
        }
    }

    // Read whole txt
    private void readRawScanner() {
        try {
            InputStream inputStream = this.getResources().openRawResource(R.raw.myraw);
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                Log.i("My raw whole file: ", scanner.next());
            } else {
                Log.e("RawScanner: ", "nothing");
            }
            inputStream.close();
        } catch (IOException e) {
            Log.e("RawScanner: ", "IO");
        }
    }

    // Read line by line
    private void readAsset() {
        try {
            InputStream inputStream = this.getAssets().open("myasset.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = bufferedReader.readLine();
            while (line != null) {
                Log.i("My asset line: ", line);
                line = bufferedReader.readLine();
            }

            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        } catch (IOException e) {
            Log.e("Test", e.getMessage());
        }
    }

    // Read whole txt
    // Parse JSON
    private void readAssetScanner() {
        try {
            InputStream inputStream = this.getAssets().open("myasset.txt");
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                // You can only call scanner.next() once inside (hasInput)
                String jsonString = scanner.next();
                Log.i("My asset whole file: ", jsonString);
                JSONObject baseJsonResponse = new JSONObject(jsonString);
            } else {
                Log.e("assetScanner: ", "nothing");
            }
            inputStream.close();
        } catch (IOException | JSONException e) {
            Log.e("assetScanner: ", "IO");
        }
    }
}
