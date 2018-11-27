import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Requester {
    private final String serviceUrl;

    public Requester(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public void makeRequest(int id, String method){
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(serviceUrl + id).openConnection();
            connection.setRequestMethod(method);
            connection.setConnectTimeout(3000);
            if (method.equals("POST")) {
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);
                OutputStream os = connection.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
                osw.write("1");
                osw.flush();
                osw.close();
                os.close();
            }
            connection.connect();
            System.out.println(Thread.currentThread().getName()+"  "+
                    serviceUrl + id + "\t\t" + method +" "+ connection.getResponseCode());
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
