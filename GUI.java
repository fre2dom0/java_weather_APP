import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class GUI extends JFrame {

    private Map<String , String > data;
     GUI(Map<String , String > data){
         this.data = data;
         this.setTitle("WEATHER APP DEMO"); //sets title of frame
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exits out of the application
         this.setSize(300, 400);
         this.setResizable(false);
         //this.setUndecorated(true); // Çerçevesiz (undecorated) bir frame oluşturur
         //this.setOpacity(0.f); // %50 şeffaf

         this.add(mainPanel());
         this.setVisible(true); //makes frame visible
    }
    private JPanel mainPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0xB9B9B9));
        panel.setSize(200, 250);
        panel.setLayout(null); // Panelleri alt alta sıralamak için Y_AXIS kullanılır

        panel.add(mainWeatherIcon());
        panel.add(mainWeatherTitle());
        panel.add(temp());
        panel.add(feelsTemp());
        panel.add(date());
        panel.add(locationData());

        return panel;
    }
    private JLabel mainWeatherIcon(){
        try {
            String mainWeatherIcon = (String) data.get("mainWeatherIcon");
            URL imageUrl = new URL("https://openweathermap.org/img/wn/" + mainWeatherIcon + "@2x.png");
            ImageIcon icon = new ImageIcon(imageUrl);

            Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            icon = new ImageIcon(scaledImage);

            JLabel label = new JLabel(icon);
            // JLabel'ın boyutlarını ayarlayabilirsiniz
            label.setBounds(90, 20, 100, 100);

            label.setOpaque(false);
            return label;
        } catch (IOException e) {
            e.printStackTrace();
            JLabel label = new JLabel("error");
            // JLabel'ın boyutlarını ayarlayabilirsiniz
            label.setBounds(90, 20, 100, 100);
            return null;
        }
    }
    private JLabel mainWeatherTitle(){
        String mainWeatherTitle = (String) data.get("mainWeather");
        JLabel label = new JLabel(mainWeatherTitle);
        label.setForeground(Color.white);
        Font font = label.getFont();
        label.setFont(new Font(font.getName(), Font.BOLD, 18));
        label.setBounds(105, 60, 100, 100);
        return label;
    }
    private JLabel temp(){
        String mainWeatherTitle = (String) data.get("temp");
        JLabel label = new JLabel(mainWeatherTitle + "℃");
        label.setForeground(Color.white);
        Font font = label.getFont();
        label.setFont(new Font(font.getName(), Font.BOLD, 16));

        label.setBounds(107, 80, 100, 100);
        return label;
    }

    private JLabel feelsTemp(){
        String mainWeatherTitle = (String) data.get("feelsTemp");
        JLabel label = new JLabel("Feels : " + mainWeatherTitle + "℃");
        label.setForeground(Color.white);
        Font font = label.getFont();
        label.setFont(new Font(font.getName(), Font.ITALIC, 12));
        label.setBounds(95, 100, 100, 100);
        return label;
    }
    private JLabel date(){
        LocalDateTime now = LocalDateTime.now();
        LocalDate currentDate = LocalDate.now();

        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();
        JLabel label = new JLabel(hour + "." + minute + " - " + month + "/" + day + "/" + year);
        label.setForeground(Color.white);
        Font font = label.getFont();
        label.setFont(new Font(font.getName(), Font.ITALIC, 12));
        label.setBounds(10, 300, 100, 100);
        return label;
    }
    private JLabel locationData(){
        String country = (String) data.get("country");
        String city = (String) data.get("city");

        JLabel label = new JLabel(city + "-" + country);
        label.setForeground(Color.white);
        Font font = label.getFont();
        label.setFont(new Font(font.getName(), Font.ITALIC, 12));
        label.setBounds(180, 300, 100, 100);
        return label;
    }

    static void noConnection(){
        JOptionPane noInternet = new JOptionPane();
        JOptionPane.showMessageDialog(null, "No Internet Connection!", "NETWORK ERROR", JOptionPane.ERROR_MESSAGE);
    }


}
