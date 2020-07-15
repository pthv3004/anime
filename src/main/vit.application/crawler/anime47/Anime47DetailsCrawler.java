package crawler.anime47;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import checker.ElementChecker;
import crawler.helper.CrawlingHelper;
import lombok.SneakyThrows;
import model.anime47.CategoryEntity;
import model.anime47.MovieDetailedEntity;
import model.anime47.MovieEntity;
import model.test.Anime;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Anime47DetailsCrawler {

    @SneakyThrows
    public void parseXMLToAnimeModel(String document, MovieEntity movieEntity, List<CategoryEntity> categories) {
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
                        List<MovieDetailedEntity> movieDetailedEntities = Stream.of(characters.getData().trim()
                                .split(","))
                                .map(c-> categories.stream()
                                        .filter(e -> e.getCategoryName().equalsIgnoreCase(c)).findFirst()
                                        .orElseGet(() -> new CategoryEntity(c)))
                                .map(c-> new MovieDetailedEntity(movieEntity,c))
                                .collect(Collectors.toList());
                        movieEntity.setMovieDetailedEntities(movieDetailedEntities);
                        isCategory = false;
                        isType = true;
                    }

                    if (isType && ElementChecker.isElementWith(startElement, "dd", "class", "movie-dd")) {
                        Characters characters = eventReader.nextEvent().asCharacters();
                        movieEntity.setType(characters.getData().trim());
                        isType = false;
                        isSeason = true;
                        continue;
                    }

                    if (isSeason && ElementChecker.isElementWith(startElement, "dd", "class", "movie-dd")) {
                        Characters characters = eventReader.nextEvent().asCharacters();
                        movieEntity.setSeason(characters.getData().trim());
                        isSeason = false;
                        isYear = true;
                        continue;
                    }

                    if (isYear && ElementChecker.isElementWith(startElement, "dd", "class", "movie-dd")) {
                        Characters characters = eventReader.nextEvent().asCharacters();
                        movieEntity.setYear(characters.getData().trim());
                        isYear = false;
                        return;
                    }
                }
            }
        }
    }
}