package lol.gilliard;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import static org.jsoup.safety.Whitelist.basicWithImages;

public class Example4 {

    public static void main(String[] args) throws IOException {

        // The HTML in this string has an attempt at an XSS in it

        String xssHTML = "Check out my cool website: <a href='http://example.com' onclick='javascript: extractUsersSessionId()'>It's right here</a>";

        Document dangerousFragment = Jsoup.parseBodyFragment(xssHTML);
        System.out.print("Dangerous HTML:");
        dangerousFragment.body().childNodes().forEach(System.out::println);


        String cleanHTML = Jsoup.clean(xssHTML, basicWithImages());

        Document safeFragment = Jsoup.parseBodyFragment(cleanHTML);
        System.out.print("Safe HTML:");
        safeFragment.body().childNodes().forEach(System.out::println);


    }

}
