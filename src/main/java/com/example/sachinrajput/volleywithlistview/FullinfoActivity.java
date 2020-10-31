package com.example.sachinrajput.volleywithlistview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FullinfoActivity extends AppCompatActivity {

    TextView firstname, lastname, emails, ids;
    String fname, lname, email, id, avatar;
    ImageView imageView;
     Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullinfo);
        firstname = (TextView) findViewById(R.id.fname);
        lastname = (TextView) findViewById(R.id.lastname);
        emails = (TextView) findViewById(R.id.emails);
        ids = (TextView) findViewById(R.id.ids);
        imageView = (ImageView) findViewById(R.id.imaes);


        fname = getIntent().getExtras().getString("firstname");
//        lname = getIntent().getExtras().getString("lastname");
//        email = getIntent().getExtras().getString("name");
//        id = getIntent().getExtras().getString("email");
      //  avatar = getIntent().getExtras().getString("avatar");
        //        Picasso.with(context).load(dataModelArrayList.get(position).getAvatar()).into(holder.iv);
        firstname.setText("First Name:-\t" + fname);
//        lastname.setText("Last Name:-\t"+lname);
//        emails.setText("Email:-\t"+email);
//        ids.setText("Id:-\t"+id);

       // Picasso.with(context).load(avatar).into(imageView);

    }
}
