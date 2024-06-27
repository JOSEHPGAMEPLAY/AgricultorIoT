package iser.apiOrion.constant;

public class messageConstant {

    /**
     * Metodo que construye un mensaje de respuesta
     * @param message mensaje a enviar
     * @return mensaje en formato JSON
     */
    public static String buildMessage(String message){
        return "{\"message\":\" "+message+"  \"}";
    }

}
