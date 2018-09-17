
package json;
import com.google.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Klijent extends JFrame {
    private JPanel panel = new JPanel();
    private JPanel panel2 = new JPanel();
    private JLabel drzava = new JLabel("Drzava: ");
    private JLabel grad = new JLabel("Grad: ");
    private JComboBox comboBox = new JComboBox();
    private JComboBox comboBox1 = new JComboBox();
    private JButton send = new JButton("UPIT");
    private JTextArea tarea = new JTextArea();
    private final static String BASE_ADDR = "http://api.openweathermap.org/data/2.5/weather?q=";
    private final static String API_KEY = "6be5e34e8d7f255c0e8717ed09a28860";

    public Klijent() throws IOException {
        setTitle("Vremenska prognoza");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,300);
        setLocation(450,200);
        setLayout(null);
        setVisible(true);
        setResizable(false);
        dodajKomponente();
        URL url = new URL("http://data.okfn.org/data/core/country-list/r/data.json");
        InputStreamReader reader = new InputStreamReader(url.openStream());
        Country[] country = new Gson().fromJson(reader, Country[].class);
        for (int i = 0; i < country.length; i++) {
            napuniComboBox(country[i]);
        }
        url = new URL("http://data.okfn.org/data/core/world-cities/r/world-cities.json");
        reader = new InputStreamReader(url.openStream());
        reader.
        Cities[] cities = new Gson().fromJson(reader, Cities[].class);
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBox1.removeAllItems();
                revalidate();
                Country selected = (Country) comboBox.getSelectedItem();
                for (int i = 0; i < cities.length; i++) {
                    if (selected.getName().equals(cities[i].getCountry()))
                        napuniComboBox1(cities[i]);
                }
            }
        });
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gradic;
                Country selectedCountry = (Country) comboBox.getSelectedItem();
                Cities selectedCity = (Cities) comboBox1.getSelectedItem();
                gradic = selectedCity.getName().replaceAll(" ","");
                //System.out.println(gradic);
                URL url1 = null;
                tarea.removeAll();
                tarea.setText("");
                tarea.repaint();
                revalidate();
                try {
                    url1 = new URL(BASE_ADDR + gradic + "," + selectedCountry.getCode().toLowerCase() + "&appid="+ API_KEY);
                    //System.out.println(url1);
                    InputStreamReader reader1 = new InputStreamReader(url1.openStream());
                    Data data = new Gson().fromJson(reader1, Data.class);
                    tarea.append("  Podaci za grad " + selectedCity.getName() + ":\n");
                    tarea.append("  Opis: " + data.getDescription() + "\n");
                    tarea.append("  Temperatura: " + data.getTemperatureInCelsius() + "\u2103" + "\n");
                    //tarea.append("  Maksimalna temperatura: " + data.getMaxTemperatureInCelsius() + "\u2103" + "\n");
                    //tarea.append("  Minimalna temperatura: " + data.getMinTemperatureInCelsius() + "\u2103" + "\n");
                    tarea.append("  Vlažnost: " + data.getHumidity() + "%" + "\n");
                    //tarea.append("  Izlazak Sunca: " + data.getSunrise() + "\n");
                    //tarea.append("  Zalazak Sunca: " + data.getSunset() + "\n");
                    //tarea.append("Vremenski parametri: " + data.getDescription() + "\n");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });
    }

    public void dodajKomponente() {
        panel.setLayout(null);
        panel2.setLayout(null);
        panel.setBounds(0, 0, 300, 130);
        panel2.setBounds(0, 135, 300, 165);
        panel.add(comboBox);
        panel.add(comboBox1);
        panel.add(send);
        panel.add(drzava);
        panel.add(grad);
        comboBox.setBounds(10, 30, 130, 20);
        comboBox1.setBounds(150, 30, 120, 20);
        send.setBounds(90, 70, 100, 40);
        drzava.setBounds(10, 5, 50, 20);
        grad.setBounds(150, 5, 50, 20);
        panel2.add(tarea);
        tarea.setBounds(0, 0, 300, 160);
        tarea.setFocusable(false);
        add(panel);
        add(panel2);
    }

    public void napuniComboBox(Country c) {
        this.comboBox.addItem(c);
        revalidate();
    }

    public void napuniComboBox1(Cities c) {
        this.comboBox1.addItem(c);
        revalidate();
    }

    public static void main(String[] args) throws IOException {
        Klijent k = new Klijent();
    }

}
