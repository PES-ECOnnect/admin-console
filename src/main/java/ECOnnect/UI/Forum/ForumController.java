package ECOnnect.UI.Forum;

import java.awt.event.*;

import ECOnnect.UI.Forum.ForumPostItem.IForumPostCallback;
import ECOnnect.UI.Interfaces.*;
import ECOnnect.API.ForumService.Post;

public class ForumController extends Controller {

    private final ForumView _view = new ForumView(this);
    private final ForumModel _model = new ForumModel();
    
    
    private void refreshList() {
        // Clear the list
        _view.clearList();
        
        // Get posts from model
        Post[] posts = null;
        try {
            posts = _model.getPosts();
        }
        catch (Exception e) {
            _view.displayError("Could not fetch posts: " + e.getMessage());
            return;
        }
        
        // Convert to forum post items
        ForumPostItem[] postItems = new ForumPostItem[posts.length];
        
        for (int i = 0; i < posts.length; ++i) {
            Post p = posts[i];
            postItems[i] = new ForumPostItem(p, _forumPostCallback);
        }
        
        _view.addItems(postItems);
    }
    
    ActionListener refreshButton(){
        return (ActionEvent e) -> {
            refreshList();
        };
    }
    
    ActionListener deleteButton(){
        return (ActionEvent e) -> {
            for (ForumPostItem p : _view.getSelected()) {
                try {
                    _model.deletePost(p.getId());
                    _view.removeItem(p);
                }
                catch (Exception ex) {
                    _view.displayError("Could not delete post: " + ex.getMessage());
                }
            }
        };
    }
    
    private IForumPostCallback _forumPostCallback = new IForumPostCallback() {
        @Override
        public boolean banUser(int userId, boolean isBanned) {
            try {
                _model.banUser(userId, isBanned);
                return true;
            }
            catch (Exception e) {
                _view.displayError("Could not ban user: " + e.getMessage());
                return false;
            }
        }
    };
    

    public View getView() {
        return _view;
    }
    
    @Override
    public void postInit(Object[] args) {
        refreshList();
    }
}
