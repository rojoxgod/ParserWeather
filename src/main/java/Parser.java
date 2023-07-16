import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class Parser {

    private static Document getPage() throws IOException {

        String url = "http://www.london-weather.info/";
        Document page = Jsoup.parse(new URL(url), 3000);
        return page;
    }

    static int dayCounter;
    static int k = 0;

    public static void printValues(Elements values, int index){

        int iterationCount = 0;
        int i = 0;

        if(index == 0) {
            boolean isMorning = values.get(0).text().split(" ")[0].contains("Morning");
            boolean isDay = values.get(0).text().split(" ")[0].contains("Day");
            boolean isEvening = values.get(0).text().split(" ")[0].contains("Evening");
            boolean isNight = values.get(0).text().split(" ")[0].contains("Night");

            if (isMorning) {
                iterationCount = 4;
            } else if (isDay) {
                iterationCount = 3;
            } else if (isEvening) {
                iterationCount = 2;
            } else if (isNight) {
                iterationCount = 1;
            }

            for (i = 0; i < iterationCount; i++) {
                Element valueLine = values.get(i);
                for (Element td : valueLine.select("td")) {
                    System.out.print(td.text() + "    ");
                }
                dayCounter++;
                System.out.println("   ");
            }
        }

        for(int p = 1; p < 5; p++) {
            if (index == p) {
                boolean isMorning = values.get(0).text().split(" ")[0].contains("Morning");
                boolean isDay = values.get(0).text().split(" ")[0].contains("Day");
                boolean isEvening = values.get(0).text().split(" ")[0].contains("Evening");
                boolean isNight = values.get(0).text().split(" ")[0].contains("Night");

                if (isMorning) {
                    k = 4;
                } else if (isDay) {
                    k = 3;
                } else if (isEvening) {
                    k = 2;
                } else if (isNight) {
                    k = 1;
                }

                int j = k;

                iterationCount = 4;
                for ( ; k < iterationCount + j; k++) {
                    Element valueLine = values.get(dayCounter);
                    for (Element td : valueLine.select("td")) {
                        System.out.print(td.text() + "    ");
                    }
                    dayCounter++;

                    System.out.println("   ");
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        Document page = getPage();
        Element tableWeather = page.select("table[class=wt]").first();

        Elements names = tableWeather.select("tr[class=wth]");
        Elements values = tableWeather.select("tr[valign=top]");
        int index = 0;


        for(Element name : names){

            String date = name.select("th[id=dt]").text().split("weather")[0];
            System.out.println(date);
            printValues(values, index);
            System.out.println(" ");
            index++;

        }
    }
}
