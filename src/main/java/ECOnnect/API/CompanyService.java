package ECOnnect.API;

import java.util.TreeMap;

import ECOnnect.API.Exceptions.ApiException;

public class CompanyService extends Service {
    
    // Only allow instantiating from ServiceFactory
    CompanyService() {}
    
    public class Company {
        // Important: The name of these attributes must match the ones in the returned JSON
        public final int id;
        public final String name;
        public final float avgrating;
        public final String imageurl;
        public final double lat;
        public final double lon;
        
        public Company(int id, String name, float avgRating, String imageURL, double lat, double lon) {
            this.id = id;
            this.name = name;
            this.avgrating = avgRating;
            this.imageurl = imageURL;
            this.lat = lat;
            this.lon = lon;
        }
    }
    
    // Get all companies
    public Company[] getCompanies() {
        
        // Call API
        super.needsToken = true;
        JsonResult result = get(ApiConstants.COMPANIES_PATH, null);
        
        // Parse result
        Company[] companies = result.getArray(ApiConstants.RET_RESULT, Company[].class);
        if (companies == null) {
            // This should never happen, the API should always return an array or an error
            throwInvalidResponseError(result, ApiConstants.RET_RESULT);
        }
        
        return companies;
    }
    
    // Get questions for the company type
    public String[] getQuestions() {
        
        // Call API
        super.needsToken = true;
        JsonResult result = get(ApiConstants.COMPANY_QUESTIONS_PATH, null);
        
        // Parse result
        String[] questions = result.getArray(ApiConstants.RET_RESULT, String[].class);
        if (questions == null) {
            // This should never happen, the API should always return an array or an error
            throwInvalidResponseError(result, ApiConstants.RET_RESULT);
        }
        
        // Trim spaces in questions
        for (int i = 0; i < questions.length; i++) {
            questions[i] = questions[i].trim();
        }
        
        return questions;
    }
    
    // Create a new company
    public void createCompany(String name, String imageURL, double lat, double lon) {
        // Add parameters
        TreeMap<String, String> params = new TreeMap<>();
        params.put(ApiConstants.COMPANY_NAME, name);
        params.put(ApiConstants.COMPANY_IMAGE_URL, imageURL);
        params.put(ApiConstants.COMPANY_LOCATION_LAT, String.valueOf(lat));
        params.put(ApiConstants.COMPANY_LOCATION_LON, String.valueOf(lon));
        
        JsonResult result = null;
        try {
            // Call API
            super.needsToken = true;
            result = post(ApiConstants.COMPANIES_PATH, params, null);
        }
        catch (ApiException e) {
            switch (e.getErrorCode()) {
                case ApiConstants.ERROR_COMPANY_EXISTS:
                    throw new RuntimeException("The company " + name + " already exists");
                default:
                    throw e;
            }
        }
        
        // Parse result
        String status = result.getAttribute(ApiConstants.RET_STATUS);
        if (status == null || !status.equals(ApiConstants.STATUS_OK)) {
            // This should never happen, the API should always return an array or an error
            throwInvalidResponseError(result, ApiConstants.RET_STATUS);
        }
    }
}
