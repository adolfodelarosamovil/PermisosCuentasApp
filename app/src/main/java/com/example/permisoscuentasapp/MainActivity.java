package com.example.permisoscuentasapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String[] PERMISOS = {
            Manifest.permission.GET_ACCOUNTS,
            Manifest.permission.CAMERA
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, PERMISOS, 100);

    }

    //Callback que se dispara para los permisos
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
            Log.d("MIAPP", "PERMISOS CONCEDIDOS");
            AccountManager accountManager = (AccountManager) getSystemService(ACCOUNT_SERVICE);
            Account[] accounts = accountManager.getAccounts();

            for(Account cuenta: accounts){
                Log.d("MiAPP", "TIPO " + cuenta.type);
                Log.d("MiAPP", "NOMBRE " + cuenta.name);
            }


        }else{
            Log.d("MIAPP", "PERMISOS DENEGADOS");
            Toast toast = Toast.makeText(this, "Necesitamos que aceptes", Toast.LENGTH_LONG);
            toast.show();
            finish();
        }
    }
}
