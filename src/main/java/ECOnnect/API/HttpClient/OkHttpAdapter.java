package ECOnnect.API.HttpClient;


import java.io.IOException;
import java.util.Map;

import okhttp3.*;

public class OkHttpAdapter implements HttpClient {
    static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private OkHttpClient client = new OkHttpClient();
    
    public String get(String url, Map<String,String> params) throws IOException {
        // By default, the request method is GET
        Request request = parseUrl(url, params).build();
        return getResponse(request);
    }
    
    public String post(String url, Map<String,String> params, String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = parseUrl(url, params).post(body).build();
        return getResponse(request);
    }
    
    
    // Create Builder and add parameters
    private Request.Builder parseUrl(String url, Map<String,String> params) {
        // Parse build URL with parameters
        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
        if (params != null) {
            for (var param : params.entrySet()) {
                httpBuilder.addQueryParameter(param.getKey(), param.getValue());
            }
        }
        // Build request
        return new Request.Builder().url(httpBuilder.build());
    }
    
    // Execute the request and return the response body as a string
    private String getResponse(Request request) throws IOException {
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response.body().string();
            }
            String url_no_params = request.url().toString().split("\\?")[0];
            throw new RuntimeException("Performing request on " + url_no_params + " resulted in error code "
                + response.code() + ": " + response.message());
        }
    }
}
