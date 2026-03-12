package lamport;
public class LamportClock {
    int clock;

    public LamportClock() {
        this.clock = 0;
    }

    public int tick() {
        this.clock = this.clock + 1;
        return this.clock;
    }

    public int update(int receveidClock) {
        this.clock = max(clock, receveidClock) + 1;
        return this.clock;
    }

    public int getTime() {
        return this.clock;
    }

    private int max(int clock2, int receveidClock) {
        int max = 0;
        if(clock2 > receveidClock) {
            max = clock2;
        }
        else {
            max = receveidClock;
        }
        
        return max;
    }
}
