package helpers;

import android.widget.EditText;

import java.text.Normalizer;

/**
 * Created by sabagip on 28/12/15.
 */
public class Methods {

    /**
     *
     * @param txtEdit Caja de texto a evaluar si su contenido es nulo
     * @return
     */
    public boolean validaNull(String cadena){
        boolean response = false;
        //String cadena = txtEdit.getText().toString();
        if(!cadena.equals("")){
            response = true;
            return response;
        }
        return response;
    }
}
