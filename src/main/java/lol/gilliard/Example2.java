package lol.gilliard;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Example2 {

    public static void main(String[] args) throws IOException {

        // Selects an interesting fact from the page using a CSS selector and prints it

        String url = "https://elegant-jones-f4e94a.netlify.com/valid_doc.html";

        Document document = Jsoup.connect(url).get();
        String interestingFact = document.select("p#interesting").text();

        System.out.println(interestingFact);

    }

}
