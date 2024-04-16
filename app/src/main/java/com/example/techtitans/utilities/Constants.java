package com.example.techtitans.utilities;

import java.util.HashMap;

public class Constants {
    public static final String KEY_COLLECTION_USERS = "users";
    public static final String KEY_NAME = "name";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_PREFERENCE = "chatAppPreference";
    public static final String KEY_IS_SIGNED_IN = "isSignedIn";
    public static final String KEY_USER_ID = "userId";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_FCM_TOKEN = "fcmToken";
    public static final String KEY_USER = "user";
    public static final String KEY_IS_GUIDE = "isGuide";
    public static final String KEY_COLLECTION_CHAT = "chat";
    public static final String KEY_SENDER_ID = "senderId";
    public static final String KEY_RECEIVER_ID = "receiverId";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_TIMESTAMP = "timestamp";
    public static final String KEY_COLLECTION_CONVERSATIONS= "conversations";
    public static final String KEY_SENDER_NAME= "senderName";
    public static final String KEY_SUCCESS= "success";
    public static final String KEY_RECEIVER_NAME= "receiverName";
    public static final String KEY_SENDER_IMAGE= "senderImage";
    public static final String KEY_RECEIVER_IMAGE= "receiverImage";
    public static final String KEY_LAST_MESSAGE = "lastMessage";
    public static final String KEY_AVAILABILITY = "availability";
    public static final String REMOTE_MSG_AUTHORIZATION = "Authorization";
    public static final String REMOTE_MSG_CONTENT_TYPE = "Content-Type";
    public static final String REMOTE_MSG_DATA = "data";
    public static final String REMOTE_MSG_REGISTRATION_IDS = "registration_ids";
    public static final String NAME_PLACE = "name";
    public static final String DESCRIPTION_PLACE = "description";
    public static final String TYPE_PLACE = "type";
    public static final String LATITUDE_PLACE = "latitude";
    public static final String LONGITUDE_PLACE = "longitude";
    public static final String FOUNDER_PLACE = "founder";
    public static final String IMAGE_PLACE = "image";
    public static final String IMAGE_PLACE_STRING = "imageString";
    public static final String LIKES_PLACE = "likes";
    public static final String TEXT_REVIEW = "textReview";
    public static final String REVIEWER_IMAGE = "reviewerImage";
    public static final String REVIEW_RATING = "reviewRating";
    public static final String REVIEWS_COLLECTION = "reviews_collection";
    public static final String REVIEWER_NAME = "reviewerName";
    public static final String ADMIN_PLACE_COLLECTION = "admin_place_collection";
    public static HashMap<String, String> remoteMsgHeaders = null;
    public static  HashMap<String, String> getRemoteMsgHeaders(){
        if (remoteMsgHeaders == null){
            remoteMsgHeaders = new HashMap<>();
            remoteMsgHeaders.put(
                    REMOTE_MSG_AUTHORIZATION,
                    "key=AAAAWIAXZew:APA91bF8TBadTbt9Tl0uJLNJf4kUkR9lFNw6XqI7w1I6Z-AJpKCKS_0SZpX1FEoVWGF4gfxGdjT2Tho_RIBrL8xsxZDd6m-ETS_3VgOvZcSHahGq0njslQi5a0tK_gJocgU11hSJSJRe"
            );
            remoteMsgHeaders.put(
                    REMOTE_MSG_CONTENT_TYPE,
                    "application/json"
            );
        }
        return remoteMsgHeaders;
    }

}
