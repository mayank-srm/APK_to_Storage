package com.project.apk_to_storage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void copyAsset(String filename){
        String  dirPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/MyFiles";
        File dir = new File(dirPath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        AssetManager assetManager = getAssets();
        InputStream in = null;
        OutputStream out = null;
        try{
            in = assetManager.open(filename);
            File outFile = new File(dirPath,filename);
            out = new FileOutputStream(outFile);
            copyFile(in, out);
            Toast.makeText(getApplicationContext(),"saved",Toast.LENGTH_LONG).show();
        }
        catch (IOException e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"saved"+e.getMessage(),Toast.LENGTH_LONG).show();

        }
        finally {
            if(in != null){
                try {
                    in.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(out != null){
                try {
                    out.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException{
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer))!= -1){
            out.write(buffer,0,read);
        }
    }

    public void load(View view) {
    }
}
