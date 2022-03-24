package ECOnnect;

import org.junit.*;

import ECOnnect.API.*;
import ECOnnect.API.CompanyService.Company;
import ECOnnect.API.HttpClient.StubHttpClient;

import static org.junit.Assert.*;

public class CompanyServiceTest {
    private CompanyService sv;
    
    @Before
    public void setUp() {
        sv = ServiceFactory.getInstance().getCompanyService();
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
    public void testGetCompanyOk() {
        Company[] companies = sv.getCompanies();
        // This should not throw an exception
        
        assertNotNull(companies);
        assertEquals(2, companies.length);
        
        assertEquals(1, companies[0].getId());
        assertEquals("company1", companies[0].getName());
        assertEquals(1.0f, companies[0].getAvgRating(), 0.0f);
        assertEquals("http://www.company1.com/image.png", companies[0].getImageUrl());
        assertEquals(1.0, companies[0].getLat(), 0.0);
        assertEquals(1.0, companies[0].getLon(), 0.0);
        
        assertEquals(2, companies[1].getId());
        assertEquals("company2", companies[1].getName());
        assertEquals(2.0f, companies[1].getAvgRating(), 0.0f);
        assertEquals("http://www.company2.com/image.png", companies[1].getImageUrl());
        assertEquals(2.0, companies[1].getLat(), 0.0);
        assertEquals(2.0, companies[1].getLon(), 0.0);
    }
    
    @Test
    public void cannotGetCompaniesWithInvalidToken() {
        ServiceTestHelper.setToken("badToken");
        expectException(() ->
            sv.getCompanies(),
            // This error is not very friendly, but it should never happen
            "The server responded with error code ERROR_INVALID_TOKEN"
        );
    }
    
    @Test
    public void cannotGetCompaniesWithoutToken() {
        ServiceTestHelper.clearToken();
        expectException(() ->
            sv.getCompanies(),
            "Admin token not set"
        );
    }
    
    
    @Test
    public void testCreateCompanyOk() {        
        sv.createCompany("newCompany", "http://www.newcompany.com/image.png", 1.0, 1.0);
        // This should not throw an exception
    }
    
    @Test
    public void cannotCreateExistingCompany() {
        expectException(() ->
            sv.createCompany("company1", "http://www.newcompany.com/image.png", 1.0, 1.0),
            "The company company1 already exists"
        );
    }
    
    @Test
    public void cannotCreateCompanyWithInvalidToken() {
        ServiceTestHelper.setToken("badToken");
        expectException(() ->
            sv.createCompany("newCompany", "http://www.newcompany.com/image.png", 1.0, 1.0),
            // This error is not very friendly, but it should never happen
            "The server responded with error code ERROR_INVALID_TOKEN"
        );
    }
    
    @Test
    public void cannotCreateCompanyWithoutToken() {
        ServiceTestHelper.clearToken();
        expectException(() ->
            sv.createCompany("newCompany", "http://www.newcompany.com/image.png", 1.0, 1.0),
            "Admin token not set"
        );
    }
    
    @Test
    public void testCreateCompanyNulls() {
        expectException(() ->
            sv.createCompany(null, "http://www.newcompany.com/image.png", 1.0, 1.0),
            "Parameter name cannot be null"
        );
        
        expectException(() ->
            sv.createCompany("newCompany", null, 1.0, 1.0),
            "Parameter imageUrl cannot be null"
        );
    }
}
