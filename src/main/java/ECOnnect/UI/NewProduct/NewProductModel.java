package ECOnnect.UI.NewProduct;

import java.util.Locale;

public class NewProductModel {
    public boolean validate(String name, String manufacturer, String imageUrl, String type) {
        name = name.trim();
        manufacturer = manufacturer.trim();
        imageUrl = imageUrl.trim();
        type = type.trim();
        // There might be more aspects to validate
        if(name.isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if(manufacturer.isEmpty()){
            throw new IllegalArgumentException("Manufacturer cannot be empty");
        }
        if(imageUrl.isEmpty()){
            throw new IllegalArgumentException("Manufacturer cannot be empty");
        }
        if(type.isEmpty()){
            throw new IllegalArgumentException("Manufacturer cannot be empty");
        }
        // TODO: call API
        return true;
    }
}
