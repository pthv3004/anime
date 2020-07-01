package crawler.anime47;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import checker.ElementChecker;
import crawler.helper.CrawlingHelper;
import lombok.SneakyThrows;
import model.Anime;

public class Anime47DetailsCrawler {

    @SneakyThrows
    public void parseXMLToAnimeModel(String document, Anime anime) {
        XMLEventReader eventReader = CrawlingHelper.parseStringToXMLEventReader(document);
        XMLEvent event;

        boolean isStart = false;

        boolean isCategory = false;
        boolean isType = false;
        boolean isSeason = false;
        boolean isYear = false;

        while (eventReader.hasNext()) {
            event = eventReader.nextEvent();

            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                if (ElementChecker.isElementWith(startElement, "dl", "class", "movie-dl")) {
                    isStart = true;
                    isCategory = true;
                }

                if (isStart) {
                    if (isCategory && ElementChecker.isElementWith(startElement, "dd", "class", "movie-dd dd-cat")) {
                        Characters characters = eventReader.nextEvent().asCharacters();
                        anime.setCategory(characters.getData().trim());
                        isCategory = false;
                        isType = true;
                    }

                    if (isType && ElementChecker.isElementWith(startElement, "dd", "class", "movie-dd")) {
                        Characters characters = eventReader.nextEvent().asCharacters();
                        anime.setType(characters.getData().trim());
                        isType = false;
                        isSeason = true;
                        continue;
                    }

                    if (isSeason && ElementChecker.isElementWith(startElement, "dd", "class", "movie-dd")) {
                        Characters characters = eventReader.nextEvent().asCharacters();
                        anime.setSeason(characters.getData().trim());
                        isSeason = false;
                        isYear = true;
                        continue;
                    }

                    if (isYear && ElementChecker.isElementWith(startElement, "dd", "class", "movie-dd")) {
                        Characters characters = eventReader.nextEvent().asCharacters();
                        anime.setYear(characters.getData().trim());
                        isYear = false;
                        return;
                    }
                }
            }
        }
    }
}