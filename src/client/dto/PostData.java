package client.dto;

public class PostData {
    private final Integer hour;
    private final Integer minute;
    private final String requestId;

    public PostData(Integer hour, Integer minute, String requestId) {
        this.hour = hour;
        this.minute = minute;
        this.requestId = requestId;
    }

    public Integer getHour() {
        return hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public String getRequestId() {
        return requestId;
    }
}
