package ECOnnect.UI.NewProduct;

import ECOnnect.UI.Interfaces.Screen;

public class NewProductScreen extends Screen {
    public NewProductScreen(){super(new NewProductController());}

    @Override
    public String getTitle() {
        return "New Product Form";
    }
}
