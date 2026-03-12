package collector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.LogEvent;

public class LogCollector {
    private List<LogEvent> events;

    public LogCollector() {
        events = new ArrayList<>();
    }

    public synchronized void receive(LogEvent event) {
        events.add(event);
        ordenar();
        exibirUltimo(event);
    }

    private void exibirUltimo(LogEvent event) {
        System.out.println(
            "[COLLECTOR] " +
            "Node= " + event.getLogID() +
            "| Lamport= " + event.getLamportTime() +
            "| Level= " + event.getLevel() +
            "| Msg= " + event.getMessage()
        );
    }

    public void listarEventos() {
        System.out.println("\n=== LOG GLOBAL ORDENADO ===");
        for (LogEvent e : events) {
            System.out.println(
                "Node=" + e.getLogID() +
                " | Lamport=" + e.getLamportTime() +
                " | Level=" + e.getLevel() +
                " | Msg=" + e.getMessage()
            );
        }
    }

    private void ordenar() {
        Collections.sort(events);
    }
}
