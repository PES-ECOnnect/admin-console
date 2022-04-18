package ECOnnect;

import org.junit.*;

import ECOnnect.API.*;
import ECOnnect.API.ForumService.Post;
import ECOnnect.API.HttpClient.StubHttpClient;

import static org.junit.Assert.*;

public class ForumServiceTest {
    ForumService sv;
    
    @Before
    public void setUp() {
        sv = ServiceFactory.getInstance().getForumService();
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
    public void testGetPostsOk() {
        Post[] posts = sv.getPosts(4, null);
        assertNotNull(posts);
        assertEquals(3, posts.length);
        
        assertEquals(1, posts[0].postid);
        assertEquals("user1", posts[0].username);
        assertEquals(1, posts[0].userid);
        assertEquals("m1", posts[0].medal);
        assertEquals("#tag1 text1", posts[0].text);
        assertEquals("https://wallpapercave.com/wp/wp4676582.jpg", posts[0].imageurl);
        assertEquals(1, posts[0].likes);
        assertEquals(2, posts[0].dislikes);
        assertEquals(1, posts[0].useroption);
        assertEquals(1649663866, posts[0].timestamp, 0);
        assertTrue(posts[0].ownpost);

        
        assertEquals(2, posts[1].postid);
        assertEquals("user2", posts[1].username);
        assertEquals(2, posts[1].userid);
        assertEquals("m2", posts[1].medal);
        assertEquals("text2 #another . #tag2", posts[1].text);
        assertEquals("image2", posts[1].imageurl);
        assertEquals(3, posts[1].likes);
        assertEquals(4, posts[1].dislikes);
        assertEquals(0, posts[1].useroption);
        assertEquals(1649663810, posts[1].timestamp, 0);
        assertFalse(posts[1].ownpost);
    }
    
    @Test
    public void testGetPostsWithTag() {
        Post[] posts = sv.getPosts(3, "tag1");
        assertNotNull(posts);
        assertEquals(1, posts.length);
        
        assertEquals(1, posts[0].postid);
        assertEquals("user1", posts[0].username);
        assertEquals(1, posts[0].userid);
        assertEquals("m1", posts[0].medal);
        assertEquals("#tag1 text1", posts[0].text);
        assertEquals("https://wallpapercave.com/wp/wp4676582.jpg", posts[0].imageurl);
        assertEquals(1, posts[0].likes);
        assertEquals(2, posts[0].dislikes);
        assertEquals(1, posts[0].useroption);
        assertEquals(1649663866, posts[0].timestamp, 0);
    }
    
    @Test
    public void testGetPostsInvalidAmount() {
        expectException(() -> 
            sv.getPosts(-1, null),
            "The server responded with error code invalid value of n"
        );
        expectException(() ->
            sv.getPosts(0, null),
            "The server responded with error code invalid value of n"
        );
    }
    
    @Test
    public void testGetPostsTagNotExists() {
        Post[] posts = sv.getPosts(3, "badTag");
        assertNotNull(posts);
        assertEquals(0, posts.length);
    }
    
    @Test
    public void cannotGetPostsWithoutToken() {
        ServiceTestHelper.clearToken();
        expectException(()->
            sv.getPosts(3, null),
            "Admin token not set"
        );
    }
    
    @Test
    public void cannotGetPostsWithWrongToken() {
        ServiceTestHelper.setToken("badToken");
        expectException(()->
            sv.getPosts(3, null),
            // This error is not very friendly, but it should never happen
            "The server responded with error code ERROR_INVALID_TOKEN"
        );
    }
    
}
