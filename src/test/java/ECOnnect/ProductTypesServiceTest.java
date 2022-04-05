package ECOnnect;

import org.junit.*;

import ECOnnect.API.*;
import ECOnnect.API.HttpClient.StubHttpClient;

import static org.junit.Assert.*;

public class ProductTypesServiceTest {
    ProductTypesService sv;
   
    @Before
    public void setUp() {
        sv = ServiceFactory.getInstance().getProductTypesService();
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
    public void testGetProductTypesOk() {
        ProductTypesService.ProductType[] types = sv.getProductTypes();
        // This should not throw an exception
        assertNotNull(types);
        assert(types.length == 2);
        
        assertEquals("type1", types[0].name);
        assertEquals("type2", types[1].name);
        
        assertEquals("q1", types[0].questions[0]);
        assertEquals("q2", types[0].questions[1]);
        assertEquals("q6", types[1].questions[2]);
    }
    
    @Test
    public void cannotGetProductsWithoutToken() {
        ServiceTestHelper.clearToken();
        expectException(()->
            sv.getProductTypes(),
            "Admin token not set"
        );
    }
    
    @Test
    public void cannotGetProductsWithWrongToken() {
        ServiceTestHelper.setToken("badToken");
        expectException(()->
            sv.getProductTypes(),
            // This error is not very friendly, but it should never happen
            "The server responded with error code ERROR_INVALID_TOKEN"
        );
    }
    
    
    @Test
    public void testCreateProductTypeOk() {
        sv.createProductType("newType", new String[]{"q1", "q2"});
        // This should not throw an exception
    }
    
    @Test
    public void testCreateEmptyProductType() {
        sv.createProductType("emptyType", new String[]{});
        // This should not throw an exception
        
        expectException(()->
            sv.createProductType("emptyType", new String[]{"q1", "q2"}),
            "The server responded with error code incorrect amount of questions"
        );
    }
    
    @Test
    public void testCreateExistingProductType() {
        expectException(()->
            sv.createProductType("existingType", new String[]{"q1", "q2"}),
            "There is already a product type with this name"
        );
    }
    
    @Test
    public void cannotCreateProductTypeWithoutToken() {
        ServiceTestHelper.clearToken();
        expectException(()->
            sv.createProductType("newType", new String[]{"q1", "q2"}),
            "Admin token not set"
        );
    }
    
    @Test
    public void cannotCreateProductTypeWithWrongToken() {
        ServiceTestHelper.setToken("badToken");
        expectException(()->
            sv.createProductType("newType", new String[]{"q1", "q2"}),
            // This error is not very friendly, but it should never happen
            "The server responded with error code ERROR_INVALID_TOKEN"
        );
    }
    
}
