package crawler.animehay;

import checker.ElementChecker;
import crawler.helper.CrawlingHelper;
import lombok.SneakyThrows;
import model.anime47.CategoryEntity;
import model.anime47.MovieDetailedEntity;
import model.anime47.MovieEntity;
import model.phimmoiz.AnimeEntity;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnimeMoizDetailsCrawler {

    @SneakyThrows
    public void parseXMLToAnimeObject(String document, MovieEntity movieEntity, List<CategoryEntity> categories) {
        XMLEventReader eventReader = CrawlingHelper.parseStringToXMLEventReader(document);
        XMLEvent event;

        boolean isStart = false;

        boolean isStatus = false;
        boolean isIMDb = false;
        boolean isVote = false;

        boolean isYear = false;
        boolean isCategory = false;

        while (eventReader.hasNext()) {
            event = eventReader.nextEvent();
            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                if (ElementChecker.isElementWith(startElement, "dl", "class", "movie-dl")) {
                    isStart = true;
                    isStatus = true;
                }
                if (isStart) {
                    if (isStatus && ElementChecker.isElementWith(startElement, "dd", "class", "movie-dd status")) {
                        Characters characters = eventReader.nextEvent().asCharacters();
                        movieEntity.setStatus(characters.getData().trim());
                        isStatus = false;
                        isIMDb = true;
                    }
                    if (isIMDb && ElementChecker.isElementWith(startElement, "dd", "class", "movie-dd imdb")) {
                        Characters characters = eventReader.nextEvent().asCharacters();
                        movieEntity.setiMdb(Float.parseFloat(characters.getData().trim()));
                        isIMDb = false;
                        isVote = true;
                        continue;
                    }
                    if (isVote && ElementChecker.isElementWith(startElement, "dd", "class", "movie-dd")) {
                        Characters characters = eventReader.nextEvent().asCharacters();
//                        anime.setYear(Integer.parseInt(characters.getData().trim()));
                        isVote = false;
                        isYear = true;
                        continue;
                    }

                    if (isYear && ElementChecker.isElementWith(startElement, "dd", "class", "movie-dd")) {
                        Characters characters = eventReader.nextEvent().asCharacters();
                        movieEntity.setYear(characters.getData().trim());
                        isCategory = true;
                        isYear = false;
                        continue;
                    }
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
                        return;
                    }
                }
            }
        }
    }
}
