package com.company;

        import org.jsoup.Jsoup;
        import org.jsoup.nodes.Document;
        import org.jsoup.nodes.Element;
        import org.jsoup.select.Elements;

        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.List;

public class Main {

    String MAIN_URL = "http://www.susu.ru/ru/university/old-departments";

    public static void main(String[] args) throws IOException {
        List <Article> articleList = new ArrayList<>();

        Main start = new Main();
        String currentUrl = "http://www.susu.ru/ru/university/old-departments";
        start.parseNote(currentUrl);

    }

    void parseNote(String URL) throws IOException {

        Document doc = Jsoup.connect(URL).get();

        Element d = doc.getElementById("md1");
        Element divElement = d.child(0);
        Elements hElements = divElement.children();


        for (Element hElement: hElements) {
            String check = hElement.html();
            if ((check.compareTo("&nbsp;")==0)||(!hElement.isBlock())) continue;

            Element aElement = hElement.child(0);
            if (aElement.isBlock()||!aElement.is("a")) return;

            String url = aElement.attr("abs:href");
            String title = aElement.text();

            System.out.println(url+" "+title);
            parseNote(url);
            //articleList.add(new Article(url, title));
        }
        
    }

    void findRef(Elements elements) {
        for (Element element: elements) {
            if(element.isBlock()) findRef(element.children());
            else {
                if (element.is("a")) {
                    String url = element.attr("abs:href");
                    String title = element.text();
                    System.out.println(url+" "+title);
                }
                else return;
            }
        }

    }

    class Example {
        private String text;
    }
}
