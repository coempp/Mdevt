package com.example.mdevt;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class signUpApi{
    String url="http://54.228.50.10/api/v1/rica_user/";
    public void signupUser(final Context context, String username, String password, final String accesstoken) throws JSONException {
        JSONObject jobj = new JSONObject();
        jobj.put("mobile_number", username);
        jobj.put("password", password);
        jobj.put("name","");
        jobj.put("surname", "");
        jobj.put("id_passport", "");
        jobj.put("selfie_photo", "");
        jobj.put("id_photo", "");
        jobj.put("proof_of_address", "False");
        jobj.put("address", "");
        jobj.put("id_passport_expiry_date", "2020-07-31");
        jobj.put("area_code", "");
        jobj.put("dailing_number", "");
        jobj.put("suburb", "");
        jobj.put("postal_code", "");
        jobj.put("city_town", "");
        jobj.put("country", "");
        jobj.put("is_agent", "False");


        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.POST, url, jobj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String status=response.getString("status");
                    if(status.equals("success")){
                        context.startActivity(new Intent(context, loggedin.class));

                    }
                    else {
                        Toast.makeText(context,"user already exist",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type","application/json");
                headers.put("Authorization", "Bearer " + accesstoken);
                return headers;
            }
        };
        Volley.newRequestQueue(context).add(jsonReq);

    }
}
