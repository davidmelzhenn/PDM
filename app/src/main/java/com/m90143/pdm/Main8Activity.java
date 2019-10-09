package com.m90143.pdm;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main8Activity extends AppCompatActivity {

    SimpleAdapter adapter;
    private ImageView imageView;
    private String mImageFileLocation = "";
    private ListView listView;
    private List<Map<String,Object>> lista;
    private Bitmap photo;
    private EditText txtMatricula;
    private EditText txtNome;
    private EditText txtEmail;
    private Spinner spnEstado;
    private Spinner spnCidade;
    String [] cidades_RS = {"Venâncio Aires", "Santa Cruz do Sul", "Lajeado"};
    String [] cidades_SC = {"Blumenau", "Florianópolis", "Joinville"};
    String [] cidades_PR = {"Curitiba", "Francisco Beltrão", "Pato Branco"};
    ArrayAdapter<CharSequence> adapter_sp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        this.imageView = (ImageView) this.findViewById(R.id.imagem);
        Button btnCamera = (Button) this.findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
                } else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                    startActivityForResult(cameraIntent, 1);
                }
            }
        });

        spnEstado = findViewById(R.id.spnEstado);
        spnCidade = findViewById(R.id.spnCidade);

        adapter_sp = ArrayAdapter.createFromResource(this, R.array.estados,android.R.layout.simple_spinner_dropdown_item);
        spnEstado.setAdapter(adapter_sp);

        spnEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                AtualizaList(selectedItem);

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        txtMatricula = findViewById(R.id.txtMatricula);
        txtNome = findViewById(R.id.txtNome);
        txtEmail = findViewById(R.id.txtEmail);

        listView = findViewById(R.id.listView);
        lista = new ArrayList<>();
        adapter = new MeuAdapter2(this, lista, R.layout.minha_linha2,
            new String[]{"img", "matricula", "nome", "email", "estado", "cidade"}, new int[]{R.id.img, R.id.txtMatricula, R.id.txtNome, R.id.txtEmail, R.id.txtEstado, R.id.txtCidade});

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final TextView tv_1 = (TextView) view.findViewById(R.id.txtMatricula);
                String txt1 = tv_1.getText().toString();

                final TextView tv_2 = (TextView) view.findViewById(R.id.txtNome);
                String txt2 = tv_2.getText().toString();
                final TextView tv_3 = (TextView) view.findViewById(R.id.txtEmail);
                String txt3 = tv_3.getText().toString();
                final TextView tv_4 = (TextView) view.findViewById(R.id.txtEstado);
                String txt4 = tv_4.getText().toString();
                final TextView tv_5 = (TextView) view.findViewById(R.id.txtCidade);
                String txt5 = tv_5.getText().toString();

                CarregaTela(txt1,txt2, txt3, txt4, txt5);
            }
        });

    }

    private void CarregaTela(String matricula, String nome, String email, String estado, String cidade){
        Intent intent = new Intent(this, CadastroActivity.class);
        intent.putExtra("matricula", matricula);
        intent.putExtra("nome", nome);
        intent.putExtra("email", email);
        intent.putExtra("estado", estado);
        intent.putExtra("cidade", cidade);
        startActivity(intent);
    }

    private void AtualizaList(String item){
        if(item.equals("RS"))
        {
            ArrayAdapter<String> adapter_lst = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Arrays.asList(cidades_RS));
            spnCidade.setAdapter(adapter_lst);
        }
        else if(item.equals("SC"))
        {
            ArrayAdapter<String> adapter_lst = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Arrays.asList(cidades_SC));
            spnCidade.setAdapter(adapter_lst);
        }
        if(item.equals("PR"))
        {
            ArrayAdapter<String> adapter_lst = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Arrays.asList(cidades_PR));
            spnCidade.setAdapter(adapter_lst);
        }
    }

    public void addClick(View v ){


        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        Map<String,Object> map = new HashMap<>();

        map.put("img", bitmap);
        map.put("matricula", txtMatricula.getText().toString());
        map.put("nome", txtNome.getText().toString());
        map.put("email", txtEmail.getText().toString());
        map.put("estado", spnEstado.getItemAtPosition(0).toString());
        map.put("cidade", spnCidade.getItemAtPosition(0).toString());

        lista.add(map);

        txtMatricula.setText("");
        txtNome.setText("");
        txtEmail.setText("");

        adapter.notifyDataSetChanged();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permissão da Câmera Concedida", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 1);
            } else {
                Toast.makeText(this, "Permissão da Câmera Negada", Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {

            photo = (Bitmap) data.getExtras().get("data");

            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            photo = Bitmap.createBitmap(photo, 0, 0, photo.getWidth(), photo.getHeight(), matrix, true);

            imageView.setImageBitmap(photo);


        }
    }

}
