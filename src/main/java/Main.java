import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args){
        int rCount = 10;
        int wCount = 10;
        ExecutorService executor = Executors.newFixedThreadPool(rCount + wCount);
        Requester requester = new Requester("http://localhost:8080/account/");

        for (int i=0; i<rCount; i++) {
            executor.execute(new ReaderWriter(requester, "GET"));
        }
        for (int i=0; i<wCount; i++) {
            executor.execute(new ReaderWriter(requester, "POST"));
        }

        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdownNow();
        }
    }
}
