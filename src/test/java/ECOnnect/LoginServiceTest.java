package ECOnnect;

import org.junit.*;

import ECOnnect.API.*;
import ECOnnect.Stubs.StubHttpClient;

import static org.junit.Assert.*;

public class LoginServiceTest {
   AdminLoginService sv;
   
   @Before
   public void setUp(){
      sv = ServiceFactory.getInstance().getAdminLoginService();
      AdminLoginService.setHttpClient(new StubHttpClient());
   }

   // test method to add two values
   @Test
   public void testLoginOk(){
      boolean success = sv.login("okUsername", "okPassword");
      assertTrue(success);
   }
}
