package FileSync;

import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.TimeZone;

public class CalendarHelper {
    public static void logBackupEvent(int fileCount, long dataSize) throws IOException, GeneralSecurityException {
        Calendar service = CalendarServiceHelper.getCalendarService();

        Event event = new Event()
                .setSummary("FileSync Backup Completed")
                .setDescription("Backup completed with " + fileCount + " files (" + dataSize + " bytes) transferred.");

        Date now = new Date();
        EventDateTime start = new EventDateTime()
                .setDateTime(new com.google.api.client.util.DateTime(now))
                .setTimeZone(TimeZone.getDefault().getID());
        event.setStart(start);
        event.setEnd(start);

        service.events().insert("primary", event).execute();
        System.out.println("Backup event added to Google Calendar.");
    }
}
