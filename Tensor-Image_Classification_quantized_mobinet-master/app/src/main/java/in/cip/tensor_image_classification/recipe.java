package in.cip.tensor_image_classification;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class recipe extends AppCompatActivity {

    private TextView mTextViewResult;
    private RequestQueue mQueue;
    private String finalresult = "Hello";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Intent myIntent = getIntent();
        String Ing = myIntent.getStringExtra("Ingredients");

        //mTextViewResult = findViewById(R.id.results);
        //mTextViewResult.setText(Ing);

        //String o = "poda";
        final TextView textView = findViewById(R.id.results);
        //textView.setText(Ing);

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.nutritionix.com/v1_1/search/";
        url += Ing;
        url += "?results=0:1&fields=*&appId=397e47bc&appKey=60f186b4b9666c63e3accb45bd6c4cd1";

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            response = response.replace("[","");
                            response = response.replace("]","");
                            response = response.replace("{","");
                            response = response.replace("}","");
                            response = response.replace("\"","");
                            String[] results = response.split(",");
                            response = "";
                            int[] needed = new int[]{8, 14, 16, 37, 38, 39, 17, 18, 19, 20, 21, 22, 23, 24,
                                    25, 26, 27, 28, 29, 30, 31, 32, 33, 34};
                            for(int i : needed)
                                response += results[i] + "\n";
                            textView.setText(response);
                        } catch (SecurityException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("OOPS");
            }
        });

        queue.add(request);
    }



        //textView.setText(url);
// Request a string response from the provided URL.
       /* JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("hits");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject JO = jsonArray.getJSONObject(i);
                                String s ="Item id:" + JO.getString("item_id") + "\n" +
                                        "Name:" + JO.getString("item_name") + "\n" +
                                        "Water content (in grams):" + JO.getInt("nf_water_grams") + "\n" +
                                        "Calories:" + JO.getInt("nf_calories") + "\n";
                                finalresult += s;
                            }
                            textView.setText(finalresult);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        });


         */


        /*JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject JO = response.getJSONObject(i);
                                String s = "Item id:" + JO.getString("item_id") + "\n" +
                                        "Name:" + JO.getString("item_name") + "\n" +
                                        "Water content (in grams):" + JO.getInt("nf_water_grams") + "\n" +
                                        "Calories:" + JO.getInt("nf_calories") + "\n";
                                finalresult += s;
                            }
                            textView.setText(finalresult);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        });
*/
  /*  protected void Parse(JSONObject res) throws JSONException {
        String s = "";
        JSONArray JA = new JSONArray(res);
        for (int i = 0; i < JA.length(); i++) {
            JSONObject JO = (JSONObject) JA.get(i);
            s = "Item id:" + JO.get("item_id") + "\n" +
                    "Name:" + JO.get("item_name") + "\n" +
                    "Water content (in grams):" + JO.get("nf_water_grams") + "\n" +
                    "Calories:" + JO.get("nf_calories") + "\n";
            finalresult += s;
        }
    }
   */
}
