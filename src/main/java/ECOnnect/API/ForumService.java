package ECOnnect.API;

import java.util.TreeMap;

import ECOnnect.API.Exceptions.ApiException;

public class ForumService extends Service {
    // Only allow instantiating from ServiceFactory
    ForumService() {}
    
    public static class Post {
        // Important: The name of these attributes must match the ones in the returned JSON
        public final int postid;
        public final String username;
        public final int userid;
        public final String medal;
        public final String text;
        public final String imageurl;
        public final int likes;
        public final int dislikes;
        public final int useroption;
        public final long timestamp;
        
        public Post(int postId, String username, int userId, String medal, String text, String imageURL, int likes, int dislikes, int userOption, long timestamp) {
            this.postid = postId;
            this.username = username;
            this.userid = userId;
            this.medal = medal;
            this.text = text;
            this.imageurl = imageURL;
            this.likes = likes;
            this.dislikes = dislikes;
            this.useroption = userOption;
            this.timestamp = timestamp;
        }
    }
    
    // Get all posts
    public Post[] getPosts(int numPosts, String tag) {
        // Add parameters
        TreeMap<String, String> params = new TreeMap<>();
        params.put(ApiConstants.POST_AMOUNT, Integer.toString(numPosts));
        // No tag means all posts
        if (tag == null) tag = "";
        params.put(ApiConstants.POST_TAG, tag);
        
        JsonResult result = null;
        try {
            // Call API
            super.needsToken = true;
            result = get(ApiConstants.POSTS_PATH, params);
        }
        catch (ApiException e) {
            switch (e.getErrorCode()) {
                // This endpoint does not throw any errors
                default:
                    throw e;
            }
        }
        
        // Parse result
        Post[] posts = result.getArray(ApiConstants.RET_RESULT, Post[].class);
        if (posts == null) {
            // This should never happen, the API should always return an array or an error
            throwInvalidResponseError(result, ApiConstants.RET_RESULT);
        }
        
        return posts;
    }
    
    // Delete a post
    public void deletePost(int postId) {
        
        JsonResult result = null;
        try {
            // Call API
            super.needsToken = true;
            result = delete(ApiConstants.POSTS_PATH + "/" + postId, null);
        }
        catch (ApiException e) {
            switch (e.getErrorCode()) {
                case ApiConstants.ERROR_POST_NOT_EXISTS:
                    throw new RuntimeException("The post with id=" + postId + " does not exist");
                default:
                    throw e;
            }
        }
        
        // Parse result
        String status = result.getAttribute(ApiConstants.RET_STATUS);
        if (status == null || !status.equals(ApiConstants.STATUS_OK)) {
            // This should never happen, the API should always return an array or an error
            throwInvalidResponseError(result, ApiConstants.RET_STATUS);
        }
    }
}
