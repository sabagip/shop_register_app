package helpers;

import android.widget.EditText;

/**
 * Created by sabagip on 28/12/15.
 */
public class Methods {

    /**
     *
     * @param txtEdit Caja de texto a evaluar si su contenido es nulo
     * @return
     */
    public boolean validaNull(EditText txtEdit){
        boolean response = false;
        String cadena = txtEdit.getText().toString();
        if(!cadena.equals("")){
            response = true;
            return response;
        }
        return response;

    }
}
