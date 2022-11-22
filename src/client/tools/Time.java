package client.tools;

import java.util.Calendar;
import java.util.Formatter;

public class Time {
    private final Integer hour;
    private final Integer minute;

    public Time() {
        Calendar now = Calendar.getInstance();

        this.hour = now.get(Calendar.HOUR_OF_DAY);
        this.minute = now.get(Calendar.MINUTE);
    }

    public Integer getHour() {
        return hour;
    }

    public Integer getMinute() {
        return minute;
    }
}
