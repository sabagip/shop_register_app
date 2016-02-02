package esshop_register.esy.tstmexico.shopregister;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import REST.Get;
import helpers.Errors;
import helpers.Methods;

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


        String url = Get.HOME + Get.LOGIN + "usuario/" + usuario + "/pass/" + pass;
        Get.limpiaListas();
        new Login().execute(url );
    }

    private void errorLogin(){
        Toast.makeText(getApplicationContext(), R.string.error_login, Toast.LENGTH_LONG).show();
    }

    private void errorServidor(){
        Toast.makeText(LoginActivity.this, R.string.error_servidor, Toast.LENGTH_LONG).show();
    }

    private class Login extends AsyncTask<String, Void, String>{
        ProgressDialog pd;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(LoginActivity.this);
            pd.setMessage("Iniciando Sesion");
            pd.setIndeterminate(false);
            pd.setCancelable(false);
            pd.show();
        }

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
            //Se oculta el cuadro de dialogo
            pd.dismiss();
            Methods helper = new Methods();
            //..........Process JSON DATA................
            if (stream != null) {
                try {
                    // Get the full HTTP Data as JSONObject
                    JSONObject reader = new JSONObject(stream);
                    JSONObject response = reader.getJSONObject("response");
                    //Si el login no fue concretado
                    if(response.get("mensaje").toString().equals(Errors.ERROR_LOGIN))
                        errorLogin();
                    //Login exitoso
                    else {
                        JSONArray result = response.getJSONArray("mensaje");
                        txtUsuario.setText(result.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    errorServidor();
                }
            }
        }
    }
}

