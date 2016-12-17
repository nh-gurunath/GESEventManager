package com.globaledgesoft.eventmanagerges;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Config.Const;
import Config.Register;

public class RegistrationActivity extends Activity implements AdapterView.OnItemSelectedListener{

    private EditText editText_full_name;

    private EditText editText_email_id;

    private EditText editText_password;

    private EditText editText_phone;

    private EditText editText_org_name;

    private EditText editText_pan_number;

    private Spinner spinner;

    private String txt_full_name;

    private String txt_email_id;

    private String txt_password;

    private String txt_phone;

    private String txt_org_name;

    private String txt_pan_number;

    private String txt_role;

    private int role;

    private Button btn_signup;

    private ProgressDialog progressDialog;

    private DatabaseReference mFirebaseDatabase;

    private FirebaseDatabase mFirebaseInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editText_full_name = (EditText)findViewById(R.id.input_fname);

        editText_email_id = (EditText)findViewById(R.id.input_email);

        editText_password = (EditText)findViewById(R.id.input_pass);

        editText_phone = (EditText)findViewById(R.id.input_phone);

        editText_org_name = (EditText)findViewById(R.id.input_org);

        editText_pan_number = (EditText)findViewById(R.id.input_pan);

        btn_signup = (Button)findViewById(R.id.btn_signup);

        spinner = (Spinner)findViewById(R.id.spinner);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,getResources().getStringArray(R.array.role_arrays));

        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(this);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        mFirebaseDatabase = mFirebaseInstance.getReference("register");


        progressDialog =new ProgressDialog(this);

        progressDialog.setMessage("Saving Data ...");

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showProgressDialog();


                txt_full_name = editText_full_name.getText().toString();

                txt_email_id = editText_email_id.getText().toString();

                txt_password = editText_password.getText().toString();

                txt_phone = editText_phone.getText().toString();

                txt_org_name = editText_org_name.getText().toString();

                txt_pan_number = editText_pan_number.getText().toString();



                Register register = new Register();

                register.setFullName(txt_full_name.toLowerCase());
                register.setEmailID(txt_email_id.toLowerCase());
                register.setPass(txt_password.toLowerCase());
                register.setPhoneNumber(txt_phone);
                register.setOrgName(txt_org_name.toLowerCase());
                register.setPanNumber(txt_pan_number.toUpperCase());
                register.setRole(role);


                mFirebaseDatabase.child("registerInfo").setValue(register);




                editText_full_name.setText("");

                editText_email_id.setText("");

                editText_password.setText("");

                editText_phone.setText("");

                editText_org_name.setText("");

                editText_pan_number.setText("");

                hideProgressDialog();

            }
        });



    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        String items = adapterView.getItemAtPosition(i).toString();

        if(items == "User")
        {
            role = 0;
        }
        else if(items == "Event Organizer")
        {
            role = 1;

        }
        else if(items == "Vendor" )
        {
            role = 2;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {

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
