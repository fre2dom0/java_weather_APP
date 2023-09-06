import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class getIpAdress {
    public static boolean isInternetAvailable() {
        try {
            int timeoutMs = 3000; // Örnek olarak 3 saniye
            Socket socket = new Socket();
            InetAddress address = InetAddress.getByName("www.google.com");
            InetSocketAddress socketAddress = new InetSocketAddress(address, 80);
            socket.connect(socketAddress, timeoutMs);
            socket.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static String getIP() {
        try {
            String url = "https://api64.ipify.org?format=json";
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
