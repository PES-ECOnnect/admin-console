package ECOnnect.UI.Forum;

import ECOnnect.API.ForumService;
import ECOnnect.API.ServiceFactory;
import ECOnnect.API.ForumService.Post;

public class ForumModel {
    // Get all the posts from the forum
    Post[] getPosts() {
        // Get products from API
        ForumService service = ServiceFactory.getInstance().getForumService();
        // Get the latest 1000 posts with any tag
        Post[] p = service.getPosts(1000, null);
        
        // No need to store in model
        
        return p;
    }
    
    // Delete a post
    void deletePost(int postid) {
        ForumService service = ServiceFactory.getInstance().getForumService();
        service.deletePost(postid);
    }
}
