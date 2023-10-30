package co.edu.iudigital.parqueadero.utils;

public class UtilString {
    private static final String EMAIL_REGEX="^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";
    private UtilString() {
    }

    public static boolean isEmailValid(String email){
        if(stringIsEmptyOrNull(email)) return false;
        email=email.trim();
        return email.matches(EMAIL_REGEX);
    }

    public static boolean isStringEmpty(String cadena){
        cadena = cadena.trim();
        return cadena.isEmpty() || cadena.isBlank();
    }
    public static boolean stringIsEmptyOrNull(String cadena){
        return cadena == null || isStringEmpty(cadena);
    }
    public static String rodearDePorcentaje(String cadena){
        return "%"+cadena+"%";
    }
}
