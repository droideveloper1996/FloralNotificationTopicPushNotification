package nursery.floralyard.com.floralnotificationtopicpushnotification;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Abhishek on 18/05/2017.
 */


public class NetworkUtils {


    public static final String GLOBAL_BASE_IMAGE_URL = "http://www.codeham.com/floralyard/firebase/";
    public static final String MESSAGE_GLOBAL = "message";
    public static final String URL_FIELD = "url_field";
    public static final String TITLE_GLOBAL = "title";
    public static final String GLOBAL_IMAGE_ON = "include_image";
    public static final String PUSH_TYPE = "push_type";

    private NetworkUtils() {
    }

    public static URL makeGlobalImageUrl(String title, String message, String path) {
        URL url = null;
        String uri = Uri.parse(GLOBAL_BASE_IMAGE_URL).buildUpon()
                .appendQueryParameter(TITLE_GLOBAL, title)
                .appendQueryParameter(MESSAGE_GLOBAL, message)
                .appendQueryParameter(URL_FIELD, path)
                .appendQueryParameter(GLOBAL_IMAGE_ON, "on")
                .appendQueryParameter(PUSH_TYPE, "topic")
                .build().toString();
        try {
            url = new URL(uri);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
