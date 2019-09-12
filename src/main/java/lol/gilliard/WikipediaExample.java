package lol.gilliard;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WikipediaExample {

    public static void main(String[] args) {
        String twilioSummary = new WikipediaExample().getWikipediaSummary("Twilio");
        System.out.println(twilioSummary);
    }

    private String getWikipediaSummary(String keyword) {

        Document document;
        try {
            document = Jsoup.connect("https://en.wikipedia.org/wiki/" + keyword).get();

        } catch (IOException e) {
            return String.format("Sorry, I couldn't find '%s' on Wikipedia", keyword);
        }

        Elements paragraphs = document.select("div#mw-content-text > div:first-of-type > p");

        String backupSentence = String.format("I couldn't find info on '%s' on Wikipedia - this bot works best if you provide a noun.", keyword);

        String wikipediaSummary = paragraphs.stream()
            .filter(e -> !e.text().isBlank())
            .findFirst()
            .map(para -> {
                    para.select("sup").remove();  // <sup> tags are used to refer to footnotes. Remove them.
                    para.select(".nowrap").remove();  // This removes the IPA and pronunciation guides.
                return para.text().replace("() ", "");}) // Remove any "()" left over from the above rules
            .orElse(backupSentence);

        String firstSentence = wikipediaSummary.substring(0, wikipediaSummary.indexOf(".") + 1);

        if (firstSentence.isBlank()) {
            firstSentence = backupSentence;
        }

        return firstSentence;
    }

}
