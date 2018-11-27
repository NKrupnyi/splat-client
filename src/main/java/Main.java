import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Requester requester = new Requester("http://localhost:8080/account/");
        executorService.execute(new ReaderWriter(requester, "POST"));
//        executorService.execute(new Reader());
        executorService.shutdown();
    }
}
