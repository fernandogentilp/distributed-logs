package model;

public class LogEvent  implements Comparable<LogEvent> {
    private final String logID;
    private final int lamportTime;
    private String timestamp;
    private String level;
    private String message;

    public LogEvent(String logId, int lamportTime, String timestamp, String level, String message) {
        this.logID = logId;
        this.lamportTime = lamportTime;
        this.timestamp = timestamp;
        this.level = level;
        this.message = message;
    }

    @Override
    public int compareTo(LogEvent logevent) {
        int response = 0;
        if(this.lamportTime < logevent.lamportTime) {
            response = -1;
        }
        else if(this.lamportTime > logevent.lamportTime) {
            response = 1;
        }
        else {
            response = this.logID.compareTo(logevent.logID);
        }
        return response;
    }

    public String getLogID() {
        return logID;
    }

    public int getLamportTime() {
        return lamportTime;
    }

    public String getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }

}
