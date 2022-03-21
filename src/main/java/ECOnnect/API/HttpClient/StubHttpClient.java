package ECOnnect.API.HttpClient;

import java.util.Map;
import java.util.TreeMap;

public class StubHttpClient implements HttpClient {
    private static final String EXPECTED_DOMAIN = "https://pes-econnect.herokuapp.com"; 
    
    @Override
    public String get(String path, Map<String, String> params) {
        path = checkDomain(path);
        if (params == null) {
            params = new TreeMap<String, String>();
        }
        checkNullParams(params);
        
        switch (path) {
            // Check if the token belongs to an admin
            case "/account/isadmin":
                expectParamsExclusive(params, "token");
                if (equals(params, "token", "badToken")) {
                    return "{\"error\":\"ERROR_INVALID_USER_TOKEN\"}";
                }
                else if (equals(params, "token", "okTokenNoAdmin")) {
                    return "{\"isAdmin\":\"false\"}";
                }
                else {
                    return "{\"isAdmin\":true}";
                }
            
            // Login
            case "/account/login":
                expectParamsExclusive(params, "email", "password");
                // For development purposes. Delete this line when done.
                if (equals(params, "email", "a")) {
                    return "{\"token\":\"okToken\"}";
                }
                
                if (equals(params, "email", "okEmail") && equals(params, "password", "okPassword")) {
                    return "{\"token\":\"okToken\"}";
                }
                if (equals(params, "email", "okEmailNoAdmin") && equals(params, "password", "okPassword")) {
                    return "{\"token\":\"okTokenNoAdmin\"}";
                }
                else if (equals(params, "email", "okEmail")) {
                    return "{\"error\":\"ERROR_USER_INCORRECT_PASSWORD\"}";
                }
                else {
                    return "{\"error\":\"ERROR_USER_NOT_FOUND\"}";
                }
            
            // Logout
            case "/account/logout":
                expectParamsExclusive(params, "token");
                if (equals(params, "token", "badToken")) {
                    return "{\"error\":\"ERROR_INVALID_USER_TOKEN\"}";
                }
                else {
                    return "{}";
                }
            
            // Get list of product types
            case "/products/types":
                expectParamsExclusive(params, "token");
                if (equals(params, "token", "badToken")) {
                    return "{\"error\":\"ERROR_INVALID_USER_TOKEN\"}";
                }
                else {
                    // For each type, return the name and an array of questions
                    return "{\"types\":[{\"name\":\"type1\",\"questions\":[\"q1\",\"q2\",\"q3\"]},{\"name\":\"type2\",\"questions\":[\"q4\",\"q5\",\"q6\"]}]}";
                }
                
            default:
                throw new RuntimeException("Invalid path: " + path);
        }
    }

    @Override
    public String post(String path, Map<String, String> params, String json) {
        path = checkDomain(path);
        if (params == null) {
            params = new TreeMap<String, String>();
        }
        checkNullParams(params);
        
        switch (path) {
            
            // Create a new product type
            case "/products/types":
                expectParamsExclusive(params, "token", "name", "questions");
                if (equals(params, "token", "badToken")) {
                    return "{\"error\":\"ERROR_INVALID_USER_TOKEN\"}";
                }
                else if (equals(params, "name", "existingType")) {
                    return "{\"error\":\"ERROR_TYPE_EXISTS\"}";
                }
                else if (equals(params, "name", "emptyType") && !equals(params, "questions", "[]")) {
                    return "{\"error\":\"incorrect amount of questions\"}";
                }
                else {
                    return "{}";
                }
                
            default:
                throw new RuntimeException("Invalid path: " + path);
        }
        
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
    
    // Check whether a parameter has a certain value
    boolean equals(Map<String, String> params, String param, String value) {
        if (!params.containsKey(param)) {
            throw new IllegalArgumentException("Missing parameter: " + param);
        }
        return params.get(param).equals(value);
    }
}
