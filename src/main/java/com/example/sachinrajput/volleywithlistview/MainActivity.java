package com.example.sachinrajput.volleywithlistview;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sachinrajput.volleywithlistview.Adapter.ListAdapter;
import com.example.sachinrajput.volleywithlistview.pojo.DataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String URLstring = "https://reqres.in/api/users?page=2";
    private static ProgressDialog mProgressDialog;
    private ListView listView;
    ArrayList<DataModel> dataModelArrayList;
    private ListAdapter listAdapter;
    String name, email, firstname, lastname, avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lv);

        retrieveJSON();

    }

    private void retrieveJSON() {

        showSimpleProgressDialog(this, "Loading...", "Fetching Json", false);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {
                            JSONObject obj = new JSONObject(response);
                            //  if(obj.optString("page").equals("2"))
                            if (response != null) {
                                dataModelArrayList = new ArrayList<>();
                                JSONArray dataArray = obj.getJSONArray("data");

                                for (int i = 0; i < dataArray.length(); i++) {

                                    DataModel dataModel = new DataModel();
                                    JSONObject dataobj = dataArray.getJSONObject(i);

                                    dataModel.setId(dataobj.getString("id"));
                                    dataModel.setEmail(dataobj.getString("email"));
                                    dataModel.setFirst_name(dataobj.getString("first_name"));
                                    dataModel.setLast_name(dataobj.getString("last_name"));
                                    dataModel.setAvatar(dataobj.getString("avatar"));
                                    dataModelArrayList.add(dataModel);

                                    name = dataobj.getString("id");
                                    email = dataobj.getString("email");
                                    firstname = dataobj.getString("first_name");
                                    lastname = dataobj.getString("last_name");
                                    avatar = dataobj.getString("avatar");
                                }

                                setupListview();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void setupListview() {
        removeSimpleProgressDialog();  //will remove progress dialog
        listAdapter = new ListAdapter(this, dataModelArrayList);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                firstname = dataModelArrayList.get(position).getFirst_name();
                lastname = dataModelArrayList.get(position).getLast_name();
                name=dataModelArrayList.get(position).getId();
                email=dataModelArrayList.get(position).getEmail();
                avatar=dataModelArrayList.get(position).getAvatar();
                Intent i=new Intent(MainActivity.this,FullinfoActivity.class);
               // Bundle b=new Bundle();
//                b.putString("firstname",firstname);
//                b.putString("lastname",lastname);
//                i.putExtras(b);
                i.putExtra("firstname",firstname);
//                i.putExtra("lastname",lastname);
//                i.putExtra("name",name);
//                i.putExtra("email",email);
               // i.putExtra("avatar",avatar);
                startActivity(i);

            }
        });
    }

    public static void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();

        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void showSimpleProgressDialog(Context context, String title,
                                                String msg, boolean isCancelable) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, title, msg);
                mProgressDialog.setCancelable(isCancelable);
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }

        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}