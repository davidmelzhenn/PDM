package com.m90143.pdm;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestActivity extends AppCompatActivity {

    private EditText txtCEP;
    private EditText txtLogradouro;
    private EditText txtComplemento;
    private EditText txtBairro;
    private EditText txtLocalidade;
    private EditText txtUf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);

        txtCEP = (EditText) findViewById(R.id.txtCEP);
        txtLogradouro = (EditText) findViewById(R.id.txtLogradouro);
        txtComplemento = (EditText) findViewById(R.id.txtComplemento);
        txtBairro = (EditText) findViewById(R.id.txtBairro);
        txtLocalidade = (EditText) findViewById(R.id.txtLocalidade);
        txtUf = (EditText) findViewById(R.id.txtUF);

    }

    public void onClickBuscar(View view) {
        String cep = txtCEP.getText().toString();
        new HttpAsyncTask().execute(cep);
    }

    class HttpAsyncTask extends AsyncTask<String, Void, String> {

        ProgressDialog dialog;

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL("https://viacep.com.br/ws/" + params[0] + "/json/");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                int status = urlConnection.getResponseCode();

                if (status == 200) {
                    InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                    StringBuilder builder = new StringBuilder();

                    String inputString;
                    while ((inputString = bufferedReader.readLine()) != null){
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
        public void onPostExecute(String result){
            dialog.dismiss();
            if (result != null){
                try{
                    JSONObject obj = new JSONObject(result);
                    String logradouro = obj.getString("logradouro");
                    String complemento = obj.getString("complemento");
                    String bairro = obj.getString("bairro");
                    String localidade = obj.getString("localidade");
                    String uf = obj.getString("uf");

                    txtLogradouro.setText(logradouro);
                    txtComplemento.setText(complemento);
                    txtBairro.setText(bairro);
                    txtLocalidade.setText(localidade);
                    txtUf.setText(uf);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        protected void onPreExecute(){
            dialog = new ProgressDialog(RestActivity.this);
            dialog.show();
        }

    }
}