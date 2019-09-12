package lol.gilliard;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Example1 {

    public static void main(String[] args) throws IOException {

        // prints the page title from a valid HTML5 page

        String url = "https://elegant-jones-f4e94a.netlify.com/valid_doc.html";

        Document document = Jsoup.connect(url).get();
        String title = document.title();

        System.out.println(title);

    }

}
