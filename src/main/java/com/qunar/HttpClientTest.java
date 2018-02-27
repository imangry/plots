package com.qunar;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Date: 18/01/03
 * User: lvshi
 */
public class HttpClientTest {
    public static void main(String[] args) throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet("http://myip.ipip.net");
        HttpResponse resp = httpClient.execute(httpGet);
        String result = EntityUtils.toString(resp.getEntity());
        System.out.println(result);

    }
}
