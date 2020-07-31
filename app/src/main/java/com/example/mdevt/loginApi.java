import android.os.AsyncTask;

class loginApi implements AsyncTask {


        override fun doInBackground(vararg params: String?): String {
        var reader: BufferedReader? = null
        var connection: HttpURLConnection? = null

        try {
        val accessToken =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6ImpvdWxlbGFic0BzbWFydGpvdWxlcy5pbiIsIl9yb2xlIjoiSm91bGUgRmVsbG93cyIsIm5hbWUiOiJKb3VsZSBGZWxsb3ciLCJfc2l0ZSI6Imdrbm1oIiwiX2hfIjoiZ2tubWhfZGVjOTZhMWVlOTVkYTEyOGU0MTM2NzlkNmE2YWMyMGQiLCJ1bml0UHJlZiI6eyJ0ZW1wZXJhdHVyZSI6ImRlZ0MiLCJwcmVzc3VyZSI6ImtQYSIsImxlbmd0aCI6Im0iLCJkZWxUZW1wZXJhdHVyZSI6ImRlbEMifSwiaWF0IjoxNTgyNzEyNDkwLCJleHAiOjE1ODI4ODUyOTB9.q0HWDUPPfXa2cujHDGYhhXp0gqUUhtcemlrQvNZ4IGU"

        val url = URL(params[0])
        connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.setRequestProperty("Content-Type", "application/" + "json")
        connection.setRequestProperty("Authorization", "Bearer $accessToken")


        Log.e("DEVICE OBJECTS", reqDeviceJSONObject.toString())
        val jsonString = "{\n" +
        "  \"siteId\":\"mgch\",\n" +
        "  \"endTime\":\"2020-02-01 10:09:02\",\n" +
        "  \"startTime\":\"2020-01-01 10:09:02\",\n" +
        "  \"deviceObject\":[${reqDeviceJSONObject}]\n" +
        "}"

        connection.getOutputStream().use { os ->
        val charset = Charsets.UTF_8
        val input: ByteArray = jsonString.toByteArray(charset)
        os.write(input, 0, input.size)
        }

        connection.connect()

        val stream = connection.inputStream
        reader = BufferedReader(InputStreamReader(stream))

        val buffer = StringBuffer()

        var line = reader.readLine()

        while (line != null) {

        buffer.append(line)

        line = reader.readLine()

        }
        Log.e("BUFFER", buffer.toString())

        return buffer.toString()

        } catch (ex: MalformedURLException) {
        ex.printStackTrace()
        } catch (ex: IOException) {
        ex.printStackTrace()
        } finally {
        connection?.disconnect()
        try {
        reader?.close()
        } catch (ex: IOException) {
        ex.printStackTrace()
        }
        }
        return ""
        }

        /**
         * Works in continuity of the above method to give the required response
         * Creates JSONarray for the responses based on some conditions
         * @param [result] contain the response in string format
         */
        override fun onPostExecute(result: String?) {

        Log.e("Result",result!!)
        var deviceJSONObject: JSONObject? = null
        if (result.isNotEmpty()) {
        deviceJSONObject = JSONObject(result)
        } else {
        Toast.makeText(c, "Please restart the app", Toast.LENGTH_LONG).show()
        }

        val data = deviceJSONObject!!.getJSONArray("parameters")
        var name:JSONObject
        var reqDeviceObject:JSONObject
        var reqDeviceData:String

        for (i in 0 until data.length()) {

        name = data.getJSONObject(i)
        reqDeviceObject = name
        reqDeviceData = reqDeviceObject.getString("name")

        if (reqDeviceData.equals("S")) {

        SData = reqDeviceObject.getJSONArray("data")

        } else if (reqDeviceData.equals("Sth")) {

        SthData = reqDeviceObject.getJSONArray("data")

        } else if (reqDeviceData.equals("beta")) {

        betaData = reqDeviceObject.getJSONArray("data")

        } else if (reqDeviceData.equals("nc")) {

        ncData = reqDeviceObject.getJSONArray("data")

        }
        }

        }
        }



/*package com.example.mdevt;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class loginApi {
    String url="http://54.228.50.10/api/v1/rica_user/";
    String accesstoken="mqpquRxr3LaESuKG6gfpKMOLqgAh7Q";
    public void loginUser(final Context context, final String username, final String password, final String act) throws JSONException {
        final JSONObject jobj=new JSONObject();
        jobj.put("username",username);
        jobj.put("password",password);
        Log.e("username", username);
        Log.e("password", password);
        Log.e("jsonObject", jobj.toString());
        Log.e("accesstoken", act);
        StringRequest jsonObjectRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                Log.e("debug2", "Working");
                try {
                    JSONObject jsonObject2 = new JSONObject(response);
                    String res =  jsonObject2.getString("response");
                    if (res.equals("OK")){
                        context.startActivity(new Intent(context,loggedin.class));
                    }
                    else {
                        Toast.makeText(context,"wrong credentials",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) { //bsdk phone se connect kar -->>>jldiii
                    Log.e("hello world",e.getMessage());

                }

            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", "unexpected error occurred");
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type","application/json");
                headers.put("Authorization", "Bearer " + accesstoken);
                return headers;

            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return jobj.toString() == null ? null : jobj.toString().getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", jobj.toString(), "utf-8");
                    return null;
                }
            }
        };
        Volley.newRequestQueue(context).add(jsonObjectRequest);

    }


}


*/


