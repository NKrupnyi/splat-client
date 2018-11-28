import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args){
        int rCount = 10;
        int wCount = 10;
        List<Integer> idList = Collections.unmodifiableList(
                new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,77)));
        ExecutorService executor = Executors.newFixedThreadPool(rCount + wCount);
        Requester requester = new Requester("http://localhost:8080/account/");

        for (int i=0; i<rCount; i++) {
            executor.execute(new ReaderWriter(requester, "GET", idList));
        }
        for (int i=0; i<wCount; i++) {
            executor.execute(new ReaderWriter(requester, "POST", idList));
        }

        executor.shutdown();
        try {
            executor.awaitTermination(20, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdownNow();
        }
    }
}
