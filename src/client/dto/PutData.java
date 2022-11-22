package client.dto;

public class PutData {
    private final Integer hour;
    private final Integer minute;

    public PutData(Integer hour, Integer minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public Integer getHour() {
        return hour;
    }

    public Integer getMinute() {
        return minute;
    }
}

