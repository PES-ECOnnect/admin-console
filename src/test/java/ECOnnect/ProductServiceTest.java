package ECOnnect;

import org.junit.*;

import ECOnnect.API.*;
import ECOnnect.API.HttpClient.StubHttpClient;
import ECOnnect.API.ProductService.Product;

import static org.junit.Assert.*;

public class ProductServiceTest {
    ProductService sv;
   
    @Before
    public void setUp() {
        sv = ServiceFactory.getInstance().getProductService();
        ServiceTestHelper.injectHttpClient(new StubHttpClient());
        ServiceTestHelper.setToken();
    }
    
    private void expectException(Runnable r, String expectedMessage) {
        try {
            r.run();
            fail("Should have thrown an exception with message: " + expectedMessage);
        }
        catch (Exception e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }
    
    @Test
    public void testGetAllProductsOk() {
        Product[] products = sv.getProducts(null);
        // This should not throw an exception
        assertNotNull(products);
        assert(products.length == 4);
        
        assertEquals(1, products[0].getId());
        assertEquals(2, products[1].getId());
        assertEquals(3, products[2].getId());
        assertEquals(4, products[3].getId());
        
        assertEquals("product1", products[0].getName());
        assertEquals("product2", products[1].getName());
        assertEquals("product3", products[2].getName());
        assertEquals("product4", products[3].getName());
        
        assertEquals("manufacturer2", products[1].getManufacturer());
        assertEquals("manufacturer3", products[2].getManufacturer());
        
        assertEquals("imageUrl3", products[2].getImageUrl());
        assertEquals("imageUrl4", products[3].getImageUrl());
        
        assertEquals("type1", products[0].getType());
        assertEquals("type2", products[2].getType());
    }
    
    @Test
    public void testGetAllProductsWithTypeOk() {
        Product[] products = sv.getProducts("type1");
        // This should not throw an exception
        assertNotNull(products);
        assert(products.length == 2);
        
        assertEquals(1, products[0].getId());
        assertEquals(2, products[1].getId());
        
        assertEquals("product1", products[0].getName());
        assertEquals("product2", products[1].getName());
        
        assertEquals("manufacturer2", products[1].getManufacturer());
        
        assertEquals("imageUrl2", products[1].getImageUrl());
        
        assertEquals("type1", products[0].getType());
    }
    
    @Test
    public void testGetAllProductsWithTypeNotExists() {
        expectException(()->
            sv.getProducts("type3"),
            "The product type type3 does not exist"
        );
    }
    
    @Test
    public void cannotGetProductsWithouthToken() {
        ServiceTestHelper.clearToken();
        expectException(()->
            sv.getProducts(null),
            "Admin token not set"
        );
    }
    
    
    @Test
    public void testCreateProductOk() {
        sv.createProduct("newProduct", "manufacturer", "imageUrl", "type1");
        // This should not throw an exception
    }
    
    @Test
    public void cannotCreateExistingProduct() {
        expectException(()->
            sv.createProduct("existingProduct", "manufacturer", "imageUrl", "type1"),
            "The product existingProduct already exists"
        );
    }
    
    @Test
    public void cannotCreateProductWithoutToken() {
        ServiceTestHelper.clearToken();
        expectException(()->
            sv.createProduct("newProduct", "manufacturer", "imageUrl", "type1"),
            "Admin token not set"
        );
    }
    
    @Test
    public void cannotCreateProductWithTypeNotExists() {
        expectException(()->
            sv.createProduct("newProduct", "manufacturer", "imageUrl", "type3"),
            "The product type type3 does not exist"
        );
    }
}