package node;

import lamport.LamportClock;
import model.LogEvent;

public class LogNode {
    private String nodeID;
    private LamportClock clock;
    private FakeSocket socket;
    private boolean ativo;

    public LogNode(String nodeID) {
        this.nodeID = nodeID;
        this.clock = new LamportClock();
        this.ativo = false;
    }


    public void start() {
        conectarAoColetor();
        this.ativo = true;

        while(ativo) {
            gerarEvento();
        }
    }

    private void gerarEvento() {
        int tempo = clock.tick();
        LogEvent event = new LogEvent(nodeID, tempo, horaAtual(), nivelAleatorio(), mensagemAleatoria());
        
        socket.send(event);
        dormir();
    }

    private void conectarAoColetor() {
        this.socket = new FakeSocket();
        System.out.println("Node" + this.nodeID + " conectado ao colector");
    }

    private String horaAtual() {
        return String.valueOf(System.currentTimeMillis());
    }

    private String nivelAleatorio() {
        String[] niveis = {"INFO", "WARN", "ERROR"};
        int idx = (int) (Math.random() * niveis.length);
        return niveis[idx];
    }

    private String mensagemAleatoria() {
        return "Evento gerado pelo node " + this.nodeID;
    }

    private void dormir() {
        try {
            Thread.sleep(2000);
        }
        catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void stop() {
        this.ativo = false;
    }
}
