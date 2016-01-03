package REST;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sabagip on 3/01/16.
 */
public class PeticionesGet extends AsyncTask<Void, Void, String> {

    int codigo = 0;

    @Override
    public void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    public String doInBackground(Void... params) {

        String respuesta = "";

        codigo = Get.LOGIN_CODE;
        List<String> parametros = Get.datosParametros;
        Get hh = new Get();
        switch (codigo){
            case Get.LOGIN_CODE:
                String url = Get.HOME + Get.LOGIN + "usuario/" + parametros.get(0) + "/pass/" + parametros.get(1);
                respuesta = hh.GetHTTPData(url);

                break;
        }
        return respuesta;
    }

    @Override
    public void onPostExecute(String s) {
        super.onPostExecute(s);

        if(s == null){
        }

        JSONObject reader = null;
        try {
            reader = new JSONObject(s);
            codigo = reader.getInt("codigo");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            switch (codigo) {
                case Get.LOGIN_CODE:
                    //JSONObject reader = new JSONObject(s);
                    JSONObject response = reader.getJSONObject("response");
                    Log.e("Respuesta", response.toString());
                    //JSONArray result = response.getJSONArray("mensaje");

                    //txtUsuario.setText(result.toString());
                    break;
            }
        }
        catch (JSONException e){

        }
    }


}
