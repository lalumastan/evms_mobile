package sqltutorial.evmsmobile.data.api;

import static sqltutorial.evmsmobile.ui.login.LoginFragment.CURRENT_LOGIN_AUTHORIZATION;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HttpsURLConnection;

public class RestApiCall {

    private final static String BASE_API_URL = "https://evmsa.herokuapp.com/api/evms/";

    String urlString;
    String type;
    String response;
    String data;
    Integer code;

    public RestApiCall(String uriPostFix, String type) {
        this(uriPostFix, type, "");
    }

    public RestApiCall(String uriPostFix, String type, String data) {
        this.code = 500;
        this.response = null;
        this.urlString = BASE_API_URL + uriPostFix;
        this.type = type;
        this.data = data;
    }

    public void sendRequest() {
        OutputStream out = null;
        try {
            boolean isPostOrPut = "POST".equals(type) || "PUT".equals(type);
            URL url = new URL(urlString);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            if (CURRENT_LOGIN_AUTHORIZATION != null) {
                connection.setRequestProperty("Authorization", "Basic " + CURRENT_LOGIN_AUTHORIZATION);
            }
            connection.addRequestProperty("Accept-Encoding", "gzip");
            connection.setRequestMethod(type);
            if (isPostOrPut) {
                connection.setRequestProperty("Content-Type", "application/json; utf-8");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoInput(true);
                connection.setDoOutput(true);
            }
            connection.setDefaultUseCaches(false);
            connection.setConnectTimeout(5000);
            connection.connect();
            if (isPostOrPut) {
                out = connection.getOutputStream();
                out.write(data.getBytes());
                out.flush();
                out.close();
                int statusCode = connection.getResponseCode();
                if (statusCode != HttpURLConnection.HTTP_OK && statusCode != HttpURLConnection.HTTP_CREATED) {
                    throw new Exception("Unexpected status from post: " + statusCode);
                }
            }

            String responseEncoding = connection.getHeaderField("Content-Encoding");
            responseEncoding = (responseEncoding == null ? "" : responseEncoding.trim());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            try {
                in = connection.getInputStream();
                if ("gzip".equalsIgnoreCase(responseEncoding)) {
                    in = new GZIPInputStream(in);
                }
                in = new BufferedInputStream(in);
                byte[] buff = new byte[1024];
                int n;
                while ((n = in.read(buff)) > 0) {
                    bos.write(buff, 0, n);
                }
                bos.flush();
                bos.close();
            } finally {
                if (in != null) {
                    in.close();
                }
            }
            response = bos.toString();
            code = connection.getResponseCode();
            android.util.Log.d(this.getClass().getSimpleName(), "api request via http returned string " + response);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null)
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
