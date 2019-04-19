package com.khromov;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Visitor {

    private Set<String> pagesVisited = new HashSet<String>();
    private List<String> pagesToVisit = new LinkedList<String>();

    private Set<String> uniqueSites = new HashSet<String>();

    public Visitor() {
    }

    public Visitor(Set<String> pagesVisited, List<String> pagesToVisit) {
        this.pagesVisited = pagesVisited;
        this.pagesToVisit = pagesToVisit;
    }

    public void search(String url) {
        String currentUrl;

        VisitorHand hand = new VisitorHand();
        if(this.pagesToVisit.isEmpty()) {
            currentUrl = url;
            this.pagesVisited.add(url);
        } else {
            currentUrl = this.nextUrl();
        }

        hand.crawl(currentUrl);

        this.pagesToVisit.addAll(hand.getLinks());

        for(String l : hand.getLinks()) {
            System.out.println("\tLINK: " + l);

            try {
                URL url_link = new URL(l);
                System.out.println("[[===== \t\t DOMAIN: " + url_link.getHost() + " ADDED TO LIST! ======]]");
                uniqueSites.add(url_link.getHost());
            } catch (MalformedURLException e) {
                System.out.println("[[\t\t ERROR: " + e.getMessage() + "]]");
            }
        }

        uniqueSites.remove("");

        for(String uq : uniqueSites) {
            System.out.println("UNIQUE SITE FOUND: >>> " + uq);
        }

        System.out.println("\n**Done** Visited " + this.pagesVisited.size() + " web page(s)");
    }

    private String nextUrl() {
        String nextUrl;
        do {
            nextUrl = this.pagesToVisit.remove(0);
        } while(this.pagesVisited.contains(nextUrl));

        this.pagesVisited.add(nextUrl);

        return nextUrl;
    }
}
