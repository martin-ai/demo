package com.example.demo.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class HtmlConvertService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String prefix = "C:\\Users\\yixiang.guo\\Desktop\\78670E08098512C5E0530100007F22C7\\";


    public Document readFromUrl(String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
        return document;
    }

    public Document readFromStr(String str) {
        return Jsoup.parse(str);
    }

    public Document readFromFile(String filename) {
        Document document = null;
        try {
            document = Jsoup.parse(new File(filename), "utf-8");
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
        return document;
    }

    public void resolveTOC(String url, Map<String, String> result) {
        Document document = readFromFile(addPrefixForBaseUrl(url));
        if (document == null) return;
        Elements partLinks = document.select("p[class=PartTOC]");
        if (partLinks.isEmpty()) return;
        for (Element partLink : partLinks) {
            resolvePartTOC(partLink, result);
        }
    }

    //第一层 PartTOC
    private void resolvePartTOC(Element partLink, Map<String, String> result) {
        String partUrl = addPrefixForBaseUrl(partLink.select("a[href]").attr("href"));
        Document partDocument = readFromFile(partUrl);
        if (partDocument == null) return;
        Elements sectionLinks = partDocument.select("p[class=SectionTOC]");
        if (sectionLinks.isEmpty()) {
            result.put(partUrl, partLink.text());
        } else {
            for (Element sectionLink : sectionLinks) {
                resolveSectionTOC(sectionLink, partLink.text(), result);
            }
        }
    }

    //第二层 SectionTOC
    private void resolveSectionTOC(Element sectionLink, String partText, Map<String, String> result) {
        String sectionUrl = addPrefixForBaseUrl(sectionLink.select("a[href]").attr("href"));
        String sectionText = partText + "&&&" + sectionLink.text();
        Document sectionDocument = readFromFile(sectionUrl);
        if (sectionDocument == null) return;
        Elements termLinks = sectionDocument.select("p[class=TermTOC]");
        if (termLinks.isEmpty()) {
            result.put(sectionUrl, sectionText);
        } else {
            for (Element termLink : termLinks) {
                resolveTermTOC(termLink, sectionText, result);
            }
        }
    }

    //第三层 TermTOC
    private void resolveTermTOC(Element termLink, String sectionText, Map<String, String> result) {
        String termUrl = addPrefixForBaseUrl(termLink.select("a[href]").attr("href"));
        String termText = sectionText + "&&&" + termLink.text();
        Document termDocument = readFromFile(termUrl);
        if (termDocument == null) return;
        result.put(termUrl, termText);
    }

    private String addPrefixForBaseUrl(String url) {
        return prefix + url;
    }

}
