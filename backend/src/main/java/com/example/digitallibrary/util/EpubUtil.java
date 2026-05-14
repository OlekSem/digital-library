package com.example.digitallibrary.util;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubReader;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;

@Component
public class EpubUtil {
    private static final int CHARS_PER_PAGE = 1024;

    public int estimatePages(String filePath) throws IOException {
        EpubReader epubReader = new EpubReader();
        Book book = epubReader.readEpub(new FileInputStream(filePath));
        long totalCharacters = 0;

        for (Resource resource : book.getSpine().getSpineReferences().stream()
                .map(ref -> ref.getResource()).toList()) {
            if (resource.getMediaType().getName().contains("html")) {
                String htmlContent = new String(resource.getData(), resource.getInputEncoding());
                String cleanText = Jsoup.parse(htmlContent).text();
                totalCharacters += cleanText.length();
            }
        }
        return Math.max(1, (int) Math.ceil((double) totalCharacters / CHARS_PER_PAGE));
    }
}
