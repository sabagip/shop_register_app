package esshop_register.esy.tstmexico.shopregister;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import REST.Get;
import REST.PeticionesGet;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText txtUsuario, txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        txtPass = (EditText) findViewById(R.id.txtContrasena);
        btnLogin = (Button) findViewById(R.id.btnLogin);

    }

    public void login(View v){
        String usuario = txtUsuario.getText().toString();
        String pass = txtPass.getText().toString();
        //String url = Get.HOME + Get.LOGIN + "usuario/" + usuario + "/pass/" + pass;
        //Log.v("lectura", url);
        //new Login().execute(url );

        Get.limpiaListas();

        Get.datosUrl.add(Get.LOGIN_CODE);
        Get.datosParametros.add(usuario);
        Get.datosParametros.add(pass);
        new PeticionesGet().execute();
    }

    private class Login extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            String stream = null;
            String urlString = params[0];

            Get hh = new Get();
            stream = hh.GetHTTPData(urlString);
            //Log.v("lectura", stream);
            return stream;
        }

        protected void onPostExecute(String stream) {
            //TextView txtUsuario = (TextView) findViewById(R.id.txtUsuario);
            //..........Process JSON DATA................
            if (stream != null) {
                try {
                    // Get the full HTTP Data as JSONObject
                    JSONObject reader = new JSONObject(stream);
                    JSONObject response = reader.getJSONObject("response");
                    JSONArray result = response.getJSONArray("mensaje");
                    txtUsuario.setText(result.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

