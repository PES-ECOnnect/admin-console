package ECOnnect.UI.Product;

import ECOnnect.UI.Interfaces.Controller;
import ECOnnect.UI.Interfaces.View;
import ECOnnect.API.ProductService.Product;
import ECOnnect.UI.ScreenManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductController implements Controller {
    private final ProductView _view = new ProductView(this);
    private final ProductModel _model = new ProductModel();

    ActionListener addButton() {
        return (ActionEvent e) -> {
            ScreenManager.getInstance().show(ScreenManager.NEW_PRODUCT_SCREEN);
        };
    }
    
    ActionListener backButton(){
        return (ActionEvent e) -> {
            ScreenManager.getInstance().show(ScreenManager.MAIN_MENU_SCREEN);
        };
    }
    
    
    ProductItem[] getProductItems() {
        String productType = _model.getSelectedType();
        
        // Get products from model
        Product[] products = null;
        try {
            products = _model.getProducts(productType);
        }
        catch (Exception e) {
            _view.displayError("Could not fetch products: " + e.getMessage());
            return new ProductItem[0];
        }
        
        // Convert to product items
        ProductItem[] productItems = new ProductItem[products.length];
        
        for (int i = 0; i < products.length; ++i) {
            Product p = products[i];
            productItems[i] = new ProductItem(p.getName(), p.getManufacturer(), p.getImageUrl(), p.getAvgRating(), p.getType());
        }
        
        return productItems;
    }
    
    
    public View getView(){
        return _view;
    }
}
