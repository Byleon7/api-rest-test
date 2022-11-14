package com.api.apiresttest.api.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import com.api.apiresttest.api.dtos.ResponseDto;
import com.api.apiresttest.api.errors.ApiRestException;
import com.api.apiresttest.api.util.ApiHelper;
import com.api.apiresttest.api.util.StringHelper;


import java.util.List;
import java.util.ArrayList;
import java.util.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service("ApiRestService")
public class ApiRestServiceImpl implements ApiRestService {
    private static final Logger log = LoggerFactory.getLogger(ApiRestServiceImpl.class);

    @Override
    public List<ResponseDto> getData(String searchData, boolean returnPhoto) throws Exception {

        if (StringHelper.isEmpty(searchData)) {
            throw new ApiRestException(ApiHelper.G268, "Parámetros inválidos", null);
        }

        JSONArray responseJsonArray = getDataFromAbc(searchData);
        
        if (responseJsonArray.length() == 0) {
            throw new ApiRestException(ApiHelper.G267, "No se encuentran noticias para el texto: "+searchData, null);
        }

        return getPostFromData(responseJsonArray, returnPhoto);
    }

    private List<ResponseDto> getPostFromData(JSONArray responseJsonArray, boolean returnPhoto) throws ApiRestException {
        try {

            List<ResponseDto> responseList = new ArrayList<>();
            ResponseDto responseDto;
            String data;
            for (int i = 0; i < responseJsonArray.length(); i++) {
                data = responseJsonArray.getString(i);
                responseDto = getResponseDto(data, returnPhoto);
                if (responseDto != null) {
                    responseList.add(responseDto);
                }
            }
            return responseList;

        } catch (Exception ex) {
            log.debug("Error in getPostFromData: ", ex);

            throw new ApiRestException(ApiHelper.G100, ApiHelper.G100_MESSAGE + ex.getMessage(), ex);
        }
    }

    private ResponseDto getResponseDto(String data, boolean returnPhoto) {
        try {

            JSONObject responseObject = ApiHelper.parseStringToJsonObject(data);
            JSONObject titleJson = ApiHelper.parseStringToJsonObject(responseObject.getString("headlines"));
            JSONObject summaryJson = ApiHelper.parseStringToJsonObject(responseObject.getString("subheadlines"));
            JSONObject promoItemsJson = ApiHelper.parseStringToJsonObject(responseObject.getString("promo_items"));
            JSONObject photoJson = ApiHelper.parseStringToJsonObject(promoItemsJson.getString("basic"));

            ResponseDto responseDto = new ResponseDto();
            responseDto.setDate(ApiHelper.getDataFromJsonObject(responseObject, "publish_date"));
            responseDto.setUrl(ApiHelper.getDataFromJsonObject(responseObject, "_website_urls"));
            responseDto.setTitle(ApiHelper.getDataFromJsonObject(titleJson, "basic"));
            responseDto.setSummary(ApiHelper.getDataFromJsonObject(summaryJson, "basic"));
            responseDto.setUrlPhoto(ApiHelper.getDataFromJsonObject(photoJson, "url"));
            responseDto.setPhotoContent(getPhotoContentText(responseDto.getUrlPhoto(), returnPhoto));
            responseDto.setPhotoContentType(getPostImageContentType(responseDto.getUrlPhoto(), returnPhoto));
            return responseDto;

        } catch (Exception e) {
            log.debug("Error in getResponseDto: ", e);
            log.error("No se pudo obtener datos del objecto → {}", data);
        }
        return null;
    }

    private String getPhotoContentText(String url, boolean returnPhoto) {
        try {
            if (!returnPhoto ) return "";
            
            if (StringHelper.isEmpty(url)) {
                throw new Exception("URL de imagen nulo o vacio");
            }

            byte[] imagaBytes = ApiHelper.getImageFromNetByUrl(url);

            return Base64.getEncoder().encodeToString(imagaBytes);

        } catch (Exception ex) {
            log.error("Error in getPhotoContentText: ", ex);
        }
        return "";
    }

    private String getPostImageContentType(String urlString, boolean returnContentType) {
        try {
            /*
             * El tipo de contenido se retorna solo bajo petición,
             * si returnContentType es igual a TRUE
             */
            if ( !returnContentType ) return "";
            
            if (urlString == null || urlString.isEmpty()) {
                throw new Exception("URL de imagen nulo o vacio");
            }

            return ApiHelper.getContentTypeFromNetByUrl(urlString);

        } catch (Exception ex) {
            log.error("Error in getPostImage: ", ex);
        }
        return "";
    }


    private JSONArray getDataFromAbc(String search) throws Exception {
        BufferedReader br = null;
        try {

            String urlWS = "https://www.abc.com.py/buscar/" + search;

            URL url = new URL ( urlWS );
            HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
            httpConn.setConnectTimeout(300000);
            httpConn.setRequestProperty("Accept", "application/json");
            httpConn.setRequestProperty("Content-Type", "application/json; utf-8");
            httpConn.setRequestMethod("GET");
            httpConn.setDoOutput(true);
  
            br = new BufferedReader(new InputStreamReader(
                httpConn.getInputStream(), StandardCharsets.UTF_8));
  
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line.trim());
            }
            String jsonString = parseHtmlStringToJson(sb.toString());

            return ApiHelper.parseStringToJsonArray(jsonString);
        }catch (Exception e){
            log.debug("Error in getDataFromAbc: ", e);
            throw new ApiRestException(ApiHelper.G100, "Error interno: " + e.getMessage(), e);
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }
 
    private String parseHtmlStringToJson(String htmlString) throws Exception {

        if (htmlString == null) {
            throw new Exception("error in parseHtmlStringToJson - htmlString is null");
        }
        log.debug("htmlString: "+htmlString);
        try {
            String parseValueStart = "Fusion.globalContent={\"data\":";
            String parseValueEnd= "}}}]";

            String arrayString = htmlString.substring(
                    htmlString.indexOf(parseValueStart) + parseValueStart.length(),
                    htmlString.indexOf(parseValueEnd) + parseValueEnd.length()
                );
            
            log.debug("Array string data: {}", arrayString);
            return arrayString;

        } catch (Exception e) {
            log.debug("Error in getResponseTextParsed: ", e);
            throw new Exception("error in getResponseTextParsed - " + e.getMessage());
        }

    }
    
}
