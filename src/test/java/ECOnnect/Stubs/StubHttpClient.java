package ECOnnect.Stubs;

import java.util.Map;

import ECOnnect.API.HttpClient.HttpClient;

public class StubHttpClient implements HttpClient {
    private static final String EXPECTED_DOMAIN = "https://pes-econnect.herokuapp.com"; 
    
    @Override
    public String get(String path, Map<String, String> params) {
        path = checkDomain(path);
        checkNullParams(params);
        
        switch (path) {
            case "/admin/login":
                expectParamsExclusive(params, "name", "password");
                if (params.get("name").equals("okUsername") && params.get("password").equals("okPassword")) {
                    return "{\"token\":\"okToken\"}";
                }
                else {
                    return "{\"ERROR\":\"ERROR_USER_NOT_FOUND\"}";
                }
                
            default:
                throw new RuntimeException("Invalid path: " + path);
        }
    }

    @Override
    public String post(String path, Map<String, String> params, String json) {
        return null;
    }
    
    
    // CHECKS
    
    // Throw an exception if the path doesn't start with the expected domain
    private String checkDomain(String path) {
        if (!path.startsWith(EXPECTED_DOMAIN)) {
            throw new IllegalArgumentException("Path must start with " + EXPECTED_DOMAIN);
        }
        return path.substring(EXPECTED_DOMAIN.length());
    }
    
    // Throw an exception if any of the params is null
    private void checkNullParams(Map<String, String> params) {
        for (String param : params.keySet()) {
            if (params.get(param) == null) {
                throw new IllegalArgumentException("Parameter " + param + " cannot be null");
            }
        }
    }
    
    // Throw an exception if params doesn't contain all of the expected params
    private void expectParams(Map<String, String> params, String... expected) {
        for (String param : expected) {
            if (!params.containsKey(param)) {
                throw new IllegalArgumentException("Missing parameter: " + param);
            }
        }
    }
    
    // Throw an exception if params does't contain EXACTLY all of the expected params
    private void expectParamsExclusive(Map<String, String> params, String... expected) {
        expectParams(params, expected);
        if (params.size() != expected.length) {
            throw new IllegalArgumentException("Too many parameters passed. Got: " + params.keySet() + ", expected: " + expected);
        }
    }
}