import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class Main {

    private static Document getPage() throws IOException {
        String url = "http://www.pogoda.spb.ru/";
        return Jsoup.parse(new URL(url), 3000);

    }

    private static Pattern pattern = Pattern.compile("\\d{2}\\.\\d{2}");

    private static String getDateFromString(String stringDate) throws Exception {
        Matcher matcher = pattern.matcher(stringDate);
        if (matcher.find()) {
            return matcher.group();
        }
        throw new Exception("Can't extract date from string!");
    }

    public static void main(String[] args) {



/*
        String fn = JOptionPane.showInputDialog("Введи первое число");
        String sn = JOptionPane.showInputDialog("Введи второе число");

        int num1 = Integer.parseInt(fn);
        int num2 = Integer.parseInt(sn);

        int sum = num1 + num2;
        JOptionPane.showMessageDialog(null, "Сумма равна " + sum, "Калькулятор", JOptionPane.PLAIN_MESSAGE);

*/


        try {
            Document page = getPage();
            //css query language
            Element tableWth = page.select("table[class=wt]").first();
            Elements names = tableWth.select("tr[class=wth]");
            // Elements values = tableWth.select("tr[valign=top]");
            Element now = tableWth.select("tr[valign=top]").first();
            Element after = tableWth.select("tr[valign=top]").get(1);
            // System.out.println(now.text());
            // System.out.println(after.text());
            System.out.println("Погода в Спб сейчас:");
            System.out.println("Температура " + now.select("td").get(2).text());
            System.out.println(now.select("td").get(1).text());
            System.out.println("Ветер " + now.select("td").get(5).text());
            System.out.println("-----------------------------");
            System.out.println("Погода в Спб на " + after.select("td").get(0).text().toLowerCase() + ":");
            System.out.println("Температура  " + after.select("td").get(2).text());
            System.out.println(after.select("td").get(1).text());
            System.out.println("Ветер " + after.select("td").get(5).text());
        } catch (Exception e) {
            System.out.println("Упс! Ошибочка!");
            System.out.println(e.getMessage());
        }
    }
}
