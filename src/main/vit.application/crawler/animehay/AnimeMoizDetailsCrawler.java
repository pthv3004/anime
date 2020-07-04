package crawler.animehay;

import checker.ElementChecker;
import crawler.helper.CrawlingHelper;
import lombok.SneakyThrows;
import model.Anime;
import model.AnimeMoiz;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class AnimeMoizDetailsCrawler {

    @SneakyThrows
    public void parseXMLToAnimeObject(String document, AnimeMoiz anime){
        XMLEventReader eventReader = CrawlingHelper.parseStringToXMLEventReader(document);
        XMLEvent event;

        boolean isStart = false;

        boolean isStatus = false;
        boolean isIMDb = false;
        boolean isVote = false;
        boolean isYear = false;
        boolean isCategory = false;

        while (eventReader.hasNext()){
            event = eventReader.nextEvent();
            if (event.isStartElement()){
                StartElement startElement = event.asStartElement();
                if (ElementChecker.isElementWith(startElement,"dl","class","movie-dl")){
                    isStart = true;
                    isStatus = true;
                }
                if (isStart){
                    if (isStatus && ElementChecker.isElementWith(startElement,"dd","class","movie-dd status")){
                        Characters characters = eventReader.nextEvent().asCharacters();
                        anime.setStatus(characters.getData().trim());
                        isStatus = false;
                        isIMDb = true;
                    }
                    if (isIMDb && ElementChecker.isElementWith(startElement,"dd","class","movie-dd imdb")){
                        Characters characters = eventReader.nextEvent().asCharacters();
                        anime.setIMDb(Float.parseFloat(characters.getData().trim()));
                        isIMDb = false;
                        isVote = true;
                        continue;
                    }
                    if (isVote && ElementChecker.isElementWith(startElement,"dd","class","movie-dd")){
                        Characters characters = eventReader.nextEvent().asCharacters();
                        anime.setYear(characters.getData().trim());
                        isVote = false;
                        isYear = true;
                        continue;
                    }
                    if (isYear && ElementChecker.isElementWith(startElement,"dd","class","movie-dd")){
                        Characters characters = eventReader.nextEvent().asCharacters();
                        anime.setYear(characters.getData().trim());
                        isCategory = true;
                        isYear = false;
                        System.out.println("Year: "+ characters.getData().trim());
                        continue;
                    }
                    if (isCategory && ElementChecker.isElementWith(startElement,"dd","class","movie-dd dd-cat")){
                        Characters characters = eventReader.nextEvent().asCharacters();
                        anime.setCategory(characters.getData().trim());
                        isCategory = false;
                        return;
                    }
                }
            }
        }
    }
}
