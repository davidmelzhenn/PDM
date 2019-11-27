package com.m90143.pdm;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rest2Activity extends AppCompatActivity {

    private TextView txtMediaTemperatura;
    private TextView txtMediaUmidade;
    private TextView txtMediaOrvalho;
    private TextView txtMediaPressao;
    private List<Map<String, Object>> dados;
    private ListView lista;
    String[] de = {"temperature", "humidity", "dewpoint", "pressure", "speed", "direction", "datetime"};
    int[] para = {R.id.txtTemperature, R.id.txtHumidity, R.id.txtDewpoint, R.id.txtPressure, R.id.txtSpeed, R.id.txtDirection, R.id.txtDatetime};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest2);

        txtMediaTemperatura = findViewById(R.id.txtMediaTemperatura);
        txtMediaUmidade = findViewById(R.id.txtMediaUmidade);
        txtMediaOrvalho = findViewById(R.id.txtMediaOrvalho);
        txtMediaPressao = findViewById(R.id.txtMediaPressao);

        lista = findViewById(R.id.listView);


        new HttpAsyncTask().execute();
    }

    class HttpAsyncTask extends AsyncTask<String, Void, String> {

        ProgressDialog dialog;

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL("http://ghelfer.net/la/weather.json");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                int status = urlConnection.getResponseCode();

                if (status == 200) {
                    InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                    StringBuilder builder = new StringBuilder();

                    String inputString;
                    while ((inputString = bufferedReader.readLine()) != null) {
                        builder.append(inputString);
                    }
                    urlConnection.disconnect();
                    return builder.toString();

                }
            } catch (Exception ex) {
                Log.e("URL", ex.toString());
            }
            return null;
        }

        @Override
        public void onPostExecute(String result) {
            dialog.dismiss();
            if (result != null) {
                try {
                    JSONObject res = new JSONObject(result);
                    JSONArray array = res.getJSONArray("weather");

                    dados = new ArrayList<Map<String, Object>>();
                    float mTemperatura = 0, mUmidade = 0, mOrvalho = 0, mPressao = 0;

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject json = array.getJSONObject(i);
                        String temperature = json.getString("temperature");
                        String humidity = json.getString("humidity");
                        String dewpoint = json.getString("dewpoint");
                        String pressure = json.getString("pressure");
                        String speed = json.getString("speed");
                        String direction = json.getString("direction");
                        String datetime = json.getString("datetime");

                        mTemperatura += Float.parseFloat(temperature);
                        mUmidade += Float.parseFloat(humidity);
                        mOrvalho += Float.parseFloat(dewpoint);
                        mPressao += Float.parseFloat(pressure);

                        Map<String, Object> item = new HashMap<String, Object>();
                        item.put("temperature", "Temperatura: " + temperature);
                        item.put("humidity", "Humidade: " + humidity);
                        item.put("dewpoint", "Orvalho: " + dewpoint);
                        item.put("pressure", "Pressão: " + pressure);
                        item.put("speed", "Velocidade: " + speed);
                        item.put("direction", "Direção: " + direction);
                        item.put("datetime", "Data/Hora: " + datetime);

                        dados.add(item);

                    }

                    txtMediaTemperatura.setText(String.format("Temperatura: %.2f", mTemperatura / array.length()));
                    txtMediaUmidade.setText(String.format("Umidade: %.2f", mUmidade / array.length()));
                    txtMediaOrvalho.setText(String.format("Orvalho: %.2f", mOrvalho / array.length()));
                    txtMediaPressao.setText(String.format("Pressao: %.2f", mPressao / array.length()));

                    SimpleAdapter adapter = new SimpleAdapter(Rest2Activity.this, dados, R.layout.minha_linha_rest2, de, para);
                    lista.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(Rest2Activity.this);
            dialog.show();
        }

    }
}