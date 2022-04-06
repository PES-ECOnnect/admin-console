package ECOnnect.API.HttpClient;

import java.util.Map;
import java.util.TreeMap;

import ECOnnect.API.ApiConstants;

public class StubHttpClient implements HttpClient {
    private static final String _EXPECTED_DOMAIN = ApiConstants.BASE_URL; 
    
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
                    return "{\"error\":\"ERROR_INVALID_TOKEN\"}";
                }
                else if (equals(params, "token", "okTokenNoAdmin")) {
                    return "{\"result\":\"false\"}";
                }
                else {
                    return "{\"result\":true}";
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
                    return "{\"error\":\"ERROR_INVALID_TOKEN\"}";
                }
                else {
                    return "{status: 'success'}";
                }
            
            // Get list of product types
            case "/products/types":
                expectParamsExclusive(params, "token");
                if (equals(params, "token", "badToken")) {
                    return "{\"error\":\"ERROR_INVALID_TOKEN\"}";
                }
                else {
                    // For each type, return the name and an array of questions
                    return "{\"result\":[{\"name\":\"type1\",\"questions\":[\"q1\",\"q2    \",\"q3\"]},{\"name\":\"type2\",\"questions\":[\"q4   \",\"q5\",\"q6\"]}]}";
                }
                
            // Get questions for the company type
            case "/companies/questions":
                expectParamsExclusive(params, "token");
                if (equals(params, "token", "badToken")) {
                    return "{\"error\":\"ERROR_INVALID_TOKEN\"}";
                }
                else {
                    // Array of questions
                    return "{\"result\":[\"q1   \",\"q2\",\"q3\"]}";
                }
                
            // Get list of products
            case "/products":
                expectParamsExclusive(params, "token", "type");
                if (equals(params, "token", "badToken")) {
                    return "{\"error\":\"ERROR_INVALID_TOKEN\"}";
                }
                // For each product of this type, return the id, name, avgRating, imageURL, manufacturer and type
                if (equals(params, "type", "")) {
                    // For each product, return the id, name, avgRating, imageURL, manufacturer and type
                    return "{\"result\":[{\"id\":1,\"name\":\"product1\",\"avgrating\":1.0,\"imageurl\":\"imageUrl1\",\"manufacturer\":\"manufacturer1\",\"type\":\"type1\"},{\"id\":2,\"name\":\"product2\",\"avgrating\":2.0,\"imageurl\":\"imageUrl2\",\"manufacturer\":\"manufacturer2\",\"type\":\"type1\"},{\"id\":3,\"name\":\"product3\",\"avgrating\":3.0,\"imageurl\":\"imageUrl3\",\"manufacturer\":\"manufacturer3\",\"type\":\"type2\"},{\"id\":4,\"name\":\"product4\",\"avgrating\":4.0,\"imageurl\":\"imageUrl4\",\"manufacturer\":\"manufacturer4\",\"type\":\"type2\"}]}";
                }
                else if (equals(params, "type", "type1")) {
                    return "{\"result\":[{\"id\":1,\"name\":\"product1\",\"avgrating\":1.0,\"imageurl\":\"imageUrl1\",\"manufacturer\":\"manufacturer1\",\"type\":\"type1\"},{\"id\":2,\"name\":\"product2\",\"avgrating\":2.0,\"imageurl\":\"imageUrl2\",\"manufacturer\":\"manufacturer2\",\"type\":\"type1\"}]}";
                }
                else if (equals(params, "type", "type2")) {
                    return "{\"result\":[{\"id\":3,\"name\":\"product3\",\"avgrating\":3.0,\"imageurl\":\"imageUrl3\",\"manufacturer\":\"manufacturer3\",\"type\":\"type2\"},{\"id\":4,\"name\":\"product4\",\"avgrating\":4.0,\"imageurl\":\"imageUrl4\",\"manufacturer\":\"manufacturer4\",\"type\":\"type2\"}]}";
                }
                else {
                    return "{\"error\":\"ERROR_TYPE_NOT_EXISTS\"}";
                }
                
            // Get list of companies
            case "/companies":
                expectParams(params, "token");
                if (equals(params, "token", "badToken")) {
                    return "{\"error\":\"ERROR_INVALID_TOKEN\"}";
                }
                else {
                    // For each company, return the id, name, avgRating, imageURL, lat and lon
                    return "{\"result\":[{\"id\":1,\"name\":\"company1\",\"avgrating\":1.0,\"imageurl\":\"http://www.company1.com/image.png\",\"lat\":1.0,\"lon\":1.0},{\"id\":2,\"name\":\"company2\",\"avgrating\":2.0,\"imageurl\":\"http://www.company2.com/image.png\",\"lat\":2.0,\"lon\":2.0}]}";
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
                expectParamsExclusive(params, "token", "name");
                if (equals(params, "token", "badToken")) {
                    return "{\"error\":\"ERROR_INVALID_TOKEN\"}";
                }
                else if (equals(params, "name", "existingType")) {
                    return "{\"error\":\"ERROR_TYPE_EXISTS\"}";
                }
                else if (equals(params, "name", "emptyType") && !json.equals("{\"questions\":[]}")) {
                    return "{\"error\":\"incorrect amount of questions\"}";
                }
                else {
                    return "{status: 'success'}";
                }
                
            // Create a new product
            case "/products":
                expectParamsExclusive(params, "token", "name", "manufacturer", "imageURL", "type");
                if (equals(params, "token", "badToken")) {
                    return "{\"error\":\"ERROR_INVALID_TOKEN\"}";
                }
                else if (equals(params, "name", "existingProduct")) {
                    return "{\"error\":\"ERROR_PRODUCT_EXISTS\"}";
                }
                else if (!equals(params, "type", "type1") && !equals(params, "type", "type2")) {
                    return "{\"error\":\"ERROR_TYPE_NOT_EXISTS\"}";
                }
                else {
                    return "{status: 'success'}";
                }
                
            // Create a new company
            case "/companies":
                expectParamsExclusive(params, "token", "name", "imageURL", "lat", "lon");
                if (equals(params, "token", "badToken")) {
                    return "{\"error\":\"ERROR_INVALID_TOKEN\"}";
                }
                else if (equals(params, "name", "company1") || equals(params, "name", "company2")) {
                    return "{\"error\":\"ERROR_COMPANY_EXISTS\"}";
                }
                else {
                    return "{status: 'success'}";
                }
                
            default:
                throw new RuntimeException("Invalid path: " + path);
        }
        
    }
    
    
    // CHECKS
    
    // Throw an exception if the path doesn't start with the expected domain
    private String checkDomain(String path) {
        if (!path.startsWith(_EXPECTED_DOMAIN)) {
            throw new IllegalArgumentException("Path must start with " + _EXPECTED_DOMAIN);
        }
        return path.substring(_EXPECTED_DOMAIN.length());
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
