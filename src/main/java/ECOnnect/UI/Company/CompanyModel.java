package ECOnnect.UI.Company;

import ECOnnect.API.CompanyService;
import ECOnnect.API.ServiceFactory;
import ECOnnect.API.CompanyService.Company;

public class CompanyModel {
    // Get all the companies
    Company[] getCompanies() {
        // Get products from API
        CompanyService service = ServiceFactory.getInstance().getCompanyService();
        Company[] c = service.getCompanies();
        
        // No need to store in model
        
        return c;
    }
    
    public void addCompany(String name, String imageUrl, String latitude, String longitude) {
        // Trim whitespace
        name = name.trim();
        imageUrl = imageUrl.trim();
        
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (imageUrl.isEmpty()) {
            throw new IllegalArgumentException("Image URL cannot be empty");
        }
        if (latitude.isEmpty()) {
            throw new IllegalArgumentException("Latitude cannot be empty");
        }
        if (longitude.isEmpty()) {
            throw new IllegalArgumentException("Longitude cannot be empty");
        }
        
        double lat, lon;
        
        try {
            lat = Double.parseDouble(latitude);
            lon = Double.parseDouble(longitude);
        }
        catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Latitude and longitude must be valid numbers");
        }
        
        // Check for NaN and infinities
        if (Double.isNaN(lat) || Double.isInfinite(lat)) {
            throw new IllegalArgumentException("Latitude must be a number");
        }
        if (Double.isNaN(lon) || Double.isInfinite(lon)) {
            throw new IllegalArgumentException("Longitude must be a number");
        }
        
        if (lat < -90 || lat > 90) {
            throw new IllegalArgumentException("Latitude must be between -90 and 90");
        }
        if (lon < -180 || lon > 180) {
            throw new IllegalArgumentException("Longitude must be between -180 and 180");
        }
        
        
        // Add product type to API
        CompanyService service = ServiceFactory.getInstance().getCompanyService();
        service.createCompany(name, imageUrl, lat, lon);
    }
}
