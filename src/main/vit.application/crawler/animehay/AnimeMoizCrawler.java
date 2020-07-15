package crawler.animehay;

import checker.ElementChecker;
import constance.WebConstance;
import crawler.helper.CrawlingHelper;
import crawler.helper.ParsingResult;
import lombok.SneakyThrows;
import model.anime47.MovieEntity;
import model.phimmoiz.AnimeEntity;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.util.ArrayList;
import java.util.Collection;

public class AnimeMoizCrawler {

    @SneakyThrows
    public Collection<MovieEntity> parseXMLToAnimeObject(String document) {
        Collection<MovieEntity> movieEntities = new ArrayList<>();

        XMLEventReader eventReader = CrawlingHelper.parseStringToXMLEventReader(document);
        XMLEvent event;
        boolean isFound = false;
        boolean isStart = false;


        boolean isLink = false;
        boolean isImage = false;
        boolean isName = false;
        String link = "";
        String image = "";
        String name = "";

        while (eventReader.hasNext()) {
            event = eventReader.nextEvent();

            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                if (ElementChecker.isElementWith(startElement, "ul", "class", "list-movie")) {
                    isFound = true;
                }
                if (isStart) {
                    if (isLink && ElementChecker.isElementWith(startElement, "a")) {
                        link = WebConstance.ANIMEMOIZ + startElement.getAttributeByName(new QName("href")).getValue();
                        isLink = false;
                        isImage = true;
                    }
                    if (isImage && ElementChecker.isElementWith(startElement, "div", "class", "movie-thumbnail")) {
                        image = startElement.getAttributeByName(new QName("style")).getValue()
                                .replace("background:url(", "")
                                .replace("); background-size: cover;", "");
                        isImage = false;
                        isName = true;
                    }
//                    if (isViewNum && ElementChecker.isElementWith(startElement, "span", "class", "viewed_online")) {
//                        Characters characters = eventReader.nextEvent().asCharacters();
//                        viewNum = Integer.parseInt(characters.getData().trim());
//                        isViewNum = false;
//                        isName = true;
//                    }
                    if (isName && ElementChecker.isElementWith(startElement, "span", "class", "movie-title-2")) {
                        Characters characters = eventReader.nextEvent().asCharacters();
                        name = characters.getData().trim();
                        isName = false;
                        isStart = false;
                        MovieEntity movieEntity = new MovieEntity(name,link,image);
                        movieEntities.add(movieEntity);
                    }
                }
            }
            if (isFound && event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                if (ElementChecker.isElementWith(startElement, "li", "class", "movie-item")) {
                    isStart = true;
                    isLink = true;
                }
            }
        }
        return movieEntities;
    }
}
