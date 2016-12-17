package com.globaledgesoft.eventmanagerges;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Config.Register;

public class LoginActivity extends Activity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private Button btn_login;

    private Button btn_signup;

    private DatabaseReference DatabaseRef;

    private ValueEventListener ValueRef;

    private EditText editText_user_name;

    private EditText editText_password;

    private String txt_userName;

    private String txt_password;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = (Button)findViewById(R.id.btn_login);

        btn_signup = (Button)findViewById(R.id.btn_signup);

        DatabaseRef = FirebaseDatabase.getInstance().getReference().child("register");

        editText_user_name = (EditText)findViewById(R.id.input_email);

        editText_password = (EditText)findViewById(R.id.input_password);

        progressDialog = new ProgressDialog(this);

        progressDialog.setMessage("Authenticationg ...");

        progressDialog.setCancelable(false);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgressDialog();

                txt_userName = editText_user_name.getText().toString();

                txt_password = editText_password.getText().toString();



                ValueRef = DatabaseRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                            Register register = postSnapshot.getValue(Register.class);

                            if(txt_userName.equals(register.getEmailID().toString()) && txt_password.equals(register.getPass().toString()))
                            {
                                Toast.makeText(LoginActivity.this,"Login Successfull", Toast.LENGTH_SHORT).show();
                                hideProgressDialog();
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this,"Invalid Login Credential", Toast.LENGTH_SHORT).show();
                                hideProgressDialog();
                            }

                            Log.d(TAG,"User Name : "+register.getEmailID() + "\n "+"Password :"+register.getPass());
                        }



                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



            }
        });


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent signIntent = new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(signIntent);

            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        DatabaseRef.removeEventListener(ValueRef);

    }


    private void showProgressDialog()
    {
        if(!progressDialog.isShowing())
        {
            progressDialog.show();
        }
    }

    private void hideProgressDialog()
    {
        if (progressDialog.isShowing())
        {
            progressDialog.hide();
        }

    }
}
