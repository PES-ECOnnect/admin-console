package ECOnnect.UI.Product;

import ECOnnect.UI.Interfaces.Screen;

public class ProductScreen extends Screen {
    private String title;

    public ProductScreen(){
        super(new ProductController());
    }

    //TODO: Title should be a product type
    public String getTitle(){return "<Product Type>";}
}
