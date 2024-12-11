package FileSync;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.calendar.Calendar;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class CalendarServiceHelper {
    private static final String APPLICATION_NAME = "FileSync";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    public static Calendar getCalendarService() throws GeneralSecurityException, IOException {
        Credential credentials = DriveServiceHelper.getCredentials();
        return new Calendar.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                credentials
        ).setApplicationName(APPLICATION_NAME).build();
    }
}
