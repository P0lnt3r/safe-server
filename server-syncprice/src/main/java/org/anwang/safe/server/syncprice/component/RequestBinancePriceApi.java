package org.anwang.safe.server.syncprice.component;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.util.List;

public class RequestBinancePriceApi {

    private static final Logger log = LoggerFactory.getLogger(RequestBinancePriceApi.class);

    private OkHttpClient httpClient;

    private static final String BASE_URL = "https://api.binance.com/api/v3/ticker/price?symbols=";

    public RequestBinancePriceApi(){
        httpClient = new OkHttpClient.Builder()
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory(), SSLSocketClient.getX509TrustManager())
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                .build();
    }

    public static void main(String[] args) throws Exception {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory(), SSLSocketClient.getX509TrustManager())
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                .build();
        String GET = "https://safewallet.anwang.com/v1/gate/mainnet";
        Request request = new Request.Builder().url(GET).build();
        Response response = okHttpClient.newCall(request).execute();
        String data = response.body().string();
        System.out.println(data);
    }

    public List<BinancePriceData> getPrice(String...pairs) throws Exception{
        JSONArray arr = new JSONArray();
        for( String pair : pairs ){
            arr.add( URLEncoder.encode("\"" + pair + "\"" , "UTF-8") );
        }
        String symbols = arr.toString().replace("\"","");
        String GET = BASE_URL + symbols;
        log.info("Request to:{}" , GET);
        Request request = new Request.Builder().url(GET).build();
        Response response = httpClient.newCall(request).execute();
        String data = response.body().string();
//        String data = "[{\"symbol\":\"ETHUSDT\",\"price\":\"1679.69000000\"},{\"symbol\":\"BNBUSDT\",\"price\":\"312.90000000\"},{\"symbol\":\"MATICUSDT\",\"price\":\"1.42130000\"}]";
        return JSONUtil.toList(JSONUtil.parseArray(data),BinancePriceData.class);
    }


}
