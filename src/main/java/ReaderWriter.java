import java.util.Random;

public class ReaderWriter implements Runnable {
    private final Requester requester;
    private final String method;
    private Random rnd = new Random(System.currentTimeMillis());

    public ReaderWriter(Requester requester, String method) {
        this.requester = requester;
        this.method = method;
    }

    public void run() {

        while (!Thread.interrupted()) {
            // Running big task
            requester.makeRequest(rnd.nextInt(100),method);

        }

    }
}
