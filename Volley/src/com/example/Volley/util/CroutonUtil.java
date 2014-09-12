package com.example.Volley.util;

import android.app.Activity;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;


public class CroutonUtil {

    public static void showInfoMessage(Activity activity,String message){

        Crouton.makeText(activity, message, Style.INFO).show();
    }

    public static void showConfirmMessage(Activity activity,String message){

        Crouton.makeText(activity, message, Style.CONFIRM).show();
    }

    public static void showErrorMessage(Activity activity,String message){

        Crouton.makeText(activity, message, Style.ALERT).show();
    }

    public static void clear(){

        Crouton.cancelAllCroutons();
    }

}
