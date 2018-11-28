import java.util.List;
import java.util.Random;

public class ReaderWriter implements Runnable {
    private final Requester requester;
    private final String method;
    private final List<Integer> idList;
    private final Random rnd = new Random(System.currentTimeMillis());

    public ReaderWriter(Requester requester, String method, List<Integer> idList) {
        this.requester = requester;
        this.method = method;
        this.idList = idList;
    }

    public void run() {
        while (!Thread.interrupted()) {
            int randomIndex = rnd.nextInt(idList.size());
            requester.makeRequest(idList.get(randomIndex),method);
        }
    }
}
