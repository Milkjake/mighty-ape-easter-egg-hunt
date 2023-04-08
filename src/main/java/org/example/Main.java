package org.example;

import me.tongfei.progressbar.ProgressBar;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Mighty Ape Easter Egg finder!");

        String baseUrl = "https://www.mightyape.co.nz";

        // Categories for eggs: Games, Electronics, Books, Toys, Sports, Music and Movies
        List<String> linksForDepartmentsWithEggs = List.of(
                baseUrl + "/games",
                baseUrl + "/electronics",
                baseUrl + "/books",
                baseUrl + "/toys",
                baseUrl + "/sports",
                baseUrl + "/music",
                baseUrl + "/movies-tv"
        );

        List<String> allProductLinks = new ArrayList<>();

        for (String departmentWithEggLink : ProgressBar.wrap(linksForDepartmentsWithEggs, "Traversing departments with eggs")) {

            Document webpage = fetchWebpage(departmentWithEggLink);

            Elements productLinks = extractProductLinks(webpage);

            if (productLinks != null) {
                allProductLinks.addAll(productLinks.stream().map(productLink -> productLink.attr("href")).toList());
            }
        }

        List<String> allUniqueProductLinks = new ArrayList<>(new HashSet<>(allProductLinks));

        List<String> linksOfWebpagesWithEggs = new ArrayList<>();

        for (String productLink : ProgressBar.wrap(allUniqueProductLinks, "Traversing all product links")) {
            String productPageUrl = baseUrl + productLink;

            Document webpage = fetchWebpage(productPageUrl);

            Element easterEgg = fetchEasterEgg(webpage);

            if (easterEgg != null) {
                String easterEggAltText = easterEgg.attr("alt");
                String easterEggColour = easterEggAltText.substring(easterEggAltText.indexOf("(") + 1, easterEggAltText.indexOf(")"));

                linksOfWebpagesWithEggs.add(easterEggColour.substring(0, 1).toUpperCase() + easterEggColour.substring(1) + " Egg: " + productPageUrl);
            }
        }

        linksOfWebpagesWithEggs.forEach(System.out::println);
    }

    static Document fetchWebpage(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static Elements extractProductLinks(Document webpage) {
        Elements mainContentElements = webpage.getElementsByClass("main");

        if (!mainContentElements.isEmpty()) {
            Element mainContent = mainContentElements.get(0);

            // select only the links to products
            return mainContent.select("a[href^=\"/product\"]");
        }

        return null;
    }

    static Element fetchEasterEgg(Document webpage) {
        return webpage.getElementById("product-hunt-image");
    }
}