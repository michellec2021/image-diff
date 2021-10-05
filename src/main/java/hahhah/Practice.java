package hahhah;

import java.net.URISyntaxException;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author Michelle.Chen
 */
public class Practice {
    public static void main(String[] args) throws URISyntaxException {
        String zoneTimeStr = "2021-08-13T01:46:01.997567Z";
        String newTime = getTimeHour(zoneTimeStr);
        System.out.println("newTime = " + newTime);
        String s = newTime.replaceAll("am|pm", "");
        System.out.println("s = " + s);
    }

    private static String getTimeHour(String time) {
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(time).withZoneSameInstant(ZoneId.systemDefault());
        // TODO: 2021/8/27 转成am/pm
        LocalTime localTime = zonedDateTime.toLocalTime();
        String hourMinus;
        if (localTime.getHour() > 12) {
            hourMinus = localTime.minusHours(12).getHour() + ":" + localTime.getMinute() + "pm";
        } else {
            hourMinus = localTime.getHour() + ":" + localTime.getMinute() + "am";
        }
        return hourMinus;
    }
}
