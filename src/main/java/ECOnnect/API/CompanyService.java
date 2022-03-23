package ECOnnect.API;

import java.util.TreeMap;

import ECOnnect.API.Exceptions.ApiException;

public class CompanyService extends Service {
    
    // Only allow instantiating from ServiceFactory
    CompanyService() {}
    
    public class Company {
        // Important: The name of these attributes must match the ones in the returned JSON
        private int id;
        private String name;
        private float avgRating;
        private String imageUrl;
        private double lat;
        private double lon;
        
        public Company(int id, String name, float avgRating, String imageUrl, double lat, double lon) {
            this.id = id;
            this.name = name;
            this.avgRating = avgRating;
            this.imageUrl = imageUrl;
            this.lat = lat;
            this.lon = lon;
        }
        
        public int getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public float getAvgRating() {
            return avgRating;
        }
        public String getImageUrl() {
            return imageUrl;
        }
        public double getLat() {
            return lat;
        }
        public double getLon() {
            return lon;
        }
    }
    
    // Get all companies
    public Company[] getCompanies() {
        
        // Call API
        super.needsToken = true;
        JsonResult result = get(ApiConstants.COMPANIES_PATH, null);
        
        // Parse result
        Company[] companies = result.getArray(ApiConstants.RET_COMPANIES, Company[].class);
        if (companies == null) {
            // This should never happen, the API should always return an array or an error
            throwInvalidResponseError(result, ApiConstants.RET_COMPANIES);
        }
        
        return companies;
    }
    
    // Create a new company
    public void createCompany(String name, String imageUrl, double lat, double lon) {
        // Add parameters
        TreeMap<String, String> params = new TreeMap<>();
        params.put(ApiConstants.COMPANY_NAME, name);
        params.put(ApiConstants.COMPANY_IMAGE_URL, imageUrl);
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
        if (result.size() > 0) {
            // This should never happen, the API should always return an array or an error
            throwInvalidResponseError(result, ApiConstants.RET_PRODUCTS);
        }
    }
}
