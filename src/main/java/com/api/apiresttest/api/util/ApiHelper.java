package com.api.apiresttest.api.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.http.HttpStatus;
import com.api.apiresttest.api.errors.ApiRestException;

import org.json.JSONArray;
import org.json.JSONObject;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiHelper {
    public static final String G267 = "g267";
    public static final String G268 = "g268";
    public static final String G100 = "g100";

    public static final String G100_MESSAGE = "Error interno del servidor";

    private static final Logger log = LoggerFactory.getLogger(ApiHelper.class);

    private ApiHelper() {}

    public static JSONObject parseStringToJsonObject(String jsonString) throws ApiRestException {
        try {
            return new JSONObject(jsonString);
        } catch (Exception e) {
            throw new ApiRestException(G100, "Formato de json no es valido para la cadena → " + jsonString, e);
        }
    }

    public static String getDataFromJsonObject ( JSONObject object, String key ) {
        try {
            String dataStr = String.valueOf(object.get(key));
            if ( dataStr == null) dataStr = "";

            return dataStr;

        } catch (Exception ignored)  {/*Ignore exception*/}

        return "";
    }

    public static JSONArray parseStringToJsonArray(String value) {
        try {
            return new JSONArray(value);
        } catch (Exception e) {
            log.error("Error in parseStringToJsonArray: ", e);
            return new JSONArray();
        }
    }

    public static byte[] getImageFromNetByUrl(String urlString) throws Exception {  
        try {  
            URL url = new URL(urlString);  
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
            conn.setRequestMethod("GET");  
            conn.setConnectTimeout(300000);

            InputStream inStream = conn.getInputStream();
            return readInputStream(inStream);
        } catch (Exception e) {  
            throw new Exception("No se pudo obtener imagen de la ruta " + urlString);
        }  
    }

    public static String getContentTypeFromNetByUrl(String urlString) throws Exception {
        try {
            URL url = new URL(urlString);  
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
            conn.setRequestMethod("GET");  
            conn.setConnectTimeout(300000);
            return conn.getContentType();
        } catch (Exception e) {
            throw new Exception("No se pudo obtener ContentType de la ruta " + urlString);
        }
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        byte[] buffer = new byte[10240];  
        int len = 0;  
        while ((len = inStream.read(buffer)) != -1) {  
            outStream.write(buffer, 0, len);  
        }  
        inStream.close();  
        return outStream.toByteArray();  
    }

    public static HttpStatus getHttpCode(String errorCode) {
        switch(errorCode) {
            case G267:
                return HttpStatus.NOT_FOUND;
            case G268:
                return HttpStatus.BAD_REQUEST;
            default:
                return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
    
}