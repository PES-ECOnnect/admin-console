package ECOnnect.API.ImageUpload;

import java.util.TreeMap;

import ECOnnect.API.JsonResult;
import ECOnnect.API.Service;
import ECOnnect.API.Exceptions.ApiException;

public class ImageService extends Service {
    
    // This token should be stored in the backend and retrieved from there
    private final String _IMAGESHACK_TOKEN = "HZFLDN3Q913ed2f68ed00054d7e1f3cdcc1d71ad";
    
    public static class UploadedImage {
        public final String id; // Unique alphanumeric image id
        public final int server; // Server location
        public final int bucket; // bucket directory
        public final String filename; // The filename on the server
        public final String original_filename; // The original filename sent from user
        public final String direct_link; // Direct link to the file
        public final String title; // User generated image title
        public final String album_id; // Unique alphanumeric album id. Only displays if image is in an album
        public final String album_title; // Album tile. Only displays if image is in an album
        public final int creation_date; // The creation date/time as a unix timestamp
        public final boolean hidden; // States whether the image has been deleted
        public final int filesize; // file size in kilobytes
        public final int width; // The width of the image
        public final int height; // the height of the image
        
        public UploadedImage (String id, int server, int bucket, String filename, String original_filename, String direct_link, String title, String album_id, String album_title, int creation_date, boolean hidden, int filesize, int width, int height) {
            this.id = id;
            this.server = server;
            this.bucket = bucket;
            this.filename = filename;
            this.original_filename = original_filename;
            this.direct_link = direct_link;
            this.title = title;
            this.album_id = album_id;
            this.album_title = album_title;
            this.creation_date = creation_date;
            this.hidden = hidden;
            this.filesize = filesize;
            this.width = width;
            this.height = height;
        }
    }
    
    public static class UploadResult {
        public final int max_filesize; // The maximum filesize the current user is allowed in kilobytes
        public final int space_limit; // The maximum account storage limit in kilobytes
        public final int space_used; // The current storage used in kilobytes
        public final int space_left; // The difference from space_limit and space_used in kilobytes
        public final int passed; // The number of images uploaded successfully
        public final int failed; // The number of images upload failures
        public final UploadedImage[] images; // An array of the Basic Image Models successfully uploaded
        
        public UploadResult(int max_filesize, int space_limit, int space_used, int space_left, int passed, int failed, UploadedImage[] images) {
            this.max_filesize = max_filesize;
            this.space_limit = space_limit;
            this.space_used = space_used;
            this.space_left = space_left;
            this.passed = passed;
            this.failed = failed;
            this.images = images;
        }
    }
    
    
    // Upload an image in base64, returns an UploadResult object
    public UploadResult uploadImage(String fileBase64) {
        // Add parameters
        TreeMap<String, String> params = new TreeMap<>();
        params.put("key", _IMAGESHACK_TOKEN);
        params.put("file", fileBase64);
        params.put("album", "my_album");
        params.put("comments_disabled", "true");
        
        JsonResult result = null;
        try {
            // Call API
            super.needsToken = true;
            result = post("https://api.imageshack.com/v2/images", params, null);
        }
        catch (ApiException e) {
            switch (e.getErrorCode()) {
                // This endpoint does not throw any errors
                default:
                    throw e;
            }
        }
        
        // Parse result
        UploadResult uploadResult = result.asObject(UploadResult.class);
        
        return uploadResult;
    }
    
    // Upload an image in base64, returns the url to the image
    public String uploadImageToUrl(String fileBase64) {
        UploadResult uploadResult = uploadImage(fileBase64);
        return uploadResult.images[0].direct_link;
    }
    
}
