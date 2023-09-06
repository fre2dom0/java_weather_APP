import java.io.IOException;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {

        if (getIpAdress.isInternetAvailable()){
            Map<String, String> data = new fetchData(getIpAdress.getIP()).getData();
            //System.out.println(data);
            new GUI(data);
        }
        else{
            GUI.noConnection();
        }

    }
}

