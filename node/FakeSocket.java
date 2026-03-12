package node;

import collector.LogCollector;
import model.LogEvent;

public class FakeSocket {

    private static LogCollector collector = new LogCollector();

    public void send(LogEvent event) {
        collector.receive(event);
    }

    public static LogCollector getCollector() {
        return collector;
    }
}