public class ReaderWriter implements Runnable {
    private final Requester requester;
    private final String method;

    public ReaderWriter(Requester requester, String method) {
        this.requester = requester;
        this.method = method;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
//            System.out.println(Thread.currentThread().getName() + "_" + i);
            requester.makeRequest(i,method);

        }

    }
}
