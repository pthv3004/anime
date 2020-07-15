package crawler.anime47;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import checker.ElementChecker;
import constance.WebConstance;
import crawler.helper.CrawlingHelper;
import lombok.SneakyThrows;
import model.anime47.MovieEntity;
import model.test.Anime;

public class Anime47Crawler {

    @SneakyThrows
    public Collection<MovieEntity> parseXMLToAnimeModel(String document) {
        Collection<MovieEntity> movieEntities = new ArrayList<>();
        XMLEventReader eventReader = CrawlingHelper.parseStringToXMLEventReader(document);
        XMLEvent event;
        boolean isFound = false;
        boolean isStart = false;

        boolean isName = false;
        boolean isImage = false;
        boolean isLink = false;
        boolean isStatus = false;
        boolean isCommentNum = false;
        boolean isViewNum = false;

        String name = "";
        String image = "";
        String link = "";
        String status = "";
        int commentNum = 0;
        int viewNum = 0;

        while (eventReader.hasNext()) {
            event = eventReader.nextEvent();

            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                if (ElementChecker.isElementWith(startElement, "ul", "class", "last-film-box")) {
                    isFound = true;
                }

                if (isStart) {
                    if (isLink && ElementChecker.isElementWith(startElement, "a")) {
                        link = WebConstance.ANIME47 + startElement.getAttributeByName(new QName("href")).getValue();
                        isLink = false;
                        isImage = true;
                    }

                    if (isImage && ElementChecker.isElementWith(startElement, "div", "class",
                            "public-film-item-thumb ratio-content")) {
                        image = startElement.getAttributeByName(new QName("style")).getValue()
                                .replace("background-image:url('", "").replace("')", "");
                        isImage = false;
                        isName = true;
                    }

                    if (isName && ElementChecker.isElementWith(startElement, "div", "class", "movie-title-1")) {
                        Characters characters = eventReader.nextEvent().asCharacters();
                        name = characters.getData().trim();
                        isName = false;
                        isCommentNum = true;
                    }

                    if (isCommentNum && ElementChecker.isElementWith(startElement, "span", "class", "fbcom-left")) {
                        Characters characters = eventReader.nextEvent().asCharacters();
                        commentNum = Integer.parseInt(characters.getData().trim());
                        isCommentNum = false;
                        isViewNum = true;
                    }

                    if (isViewNum && ElementChecker.isElementWith(startElement, "span", "class", "viewed-right")) {
                        Characters characters = eventReader.nextEvent().asCharacters();
                        viewNum = Integer.parseInt(characters.getData().trim());
                        isViewNum = false;
                        isStatus = true;
                    }

                    if (isStatus && ElementChecker.isElementWith(startElement, "span", "class", "ribbon")) {
                        Characters characters = eventReader.nextEvent().asCharacters();
                        status = characters.getData().trim();
                        isStatus = false;
                        isStart = false;

                        MovieEntity movieEntity = new MovieEntity(name,link,status,commentNum,viewNum,image);
                        movieEntities.add(movieEntity);
                    }
                }
            }

            if (isFound && event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                if (ElementChecker.isElementWith(startElement, "div", "class", "movie-item m-block")) {
                    isStart = true;
                    isLink = true;
                }
            }
        }

        return movieEntities;
    }
}