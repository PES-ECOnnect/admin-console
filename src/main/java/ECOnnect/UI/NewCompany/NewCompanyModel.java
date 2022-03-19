package ECOnnect.UI.NewCompany;

public class NewCompanyModel {
    public void validate(String name, String location, String imageUrl) {
        name = name.trim();
        location = location.trim();
        imageUrl = imageUrl.trim();

        if(name.isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if(location.isEmpty()){
            throw new IllegalArgumentException("Location cannot be empty");
        }
        // we supose image is not required
    }
}
