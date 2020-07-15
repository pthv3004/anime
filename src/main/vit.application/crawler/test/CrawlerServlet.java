package crawler.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constance.WebConstance;
import crawler.anime47.Anime47Crawler;
import crawler.anime47.Anime47DetailsCrawler;
import crawler.helper.CrawlingHelper;
import jpa.AnimeJPA;
import model.anime47.MovieEntity;
import model.test.Anime;
import utils.HtmlNormalization;

@WebServlet(name = "SCrawlerServlet", urlPatterns = { "/SCrawlerServlet" })
public class CrawlerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
        AnimeJPA animeJPA = new AnimeJPA();
        List<String> imageLinks = animeJPA.getImageLink();
        int count = 179553;
        for (String imageLink : imageLinks) {
            try {
                ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(imageLink).openStream());
                FileOutputStream fileOutputStream = new FileOutputStream("F:\\semester_9\\PRX391\\WibuLover\\images" + (++count) + ".jpg");
                FileChannel fileChannel = fileOutputStream.getChannel();
                fileChannel.transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            }catch (Exception e){

            }

        }
//        String content = HtmlNormalization.refineHtml(CrawlingHelper.normalizeHTML(WebConstance.ANIME_PAGE));
//
//        Anime47Crawler crawler = new Anime47Crawler();
//        Anime47DetailsCrawler detailsCrawler = new Anime47DetailsCrawler();
//
//        detailsCrawler.parseXMLToAnimeModel(content, new MovieEntity());
//        while (true) {
//            Collection<MovieEntity> movieEntities = crawler.parseXMLToAnimeModel(content);
//
//            for (Anime anime : animes) {
//                content = HtmlNormalization.refineHtml(CrawlingHelper.normalizeHTML(anime.getLink()));
//                String expression = "<a.*?>";
//                content = content.replaceAll(expression, "").replaceAll("</a>", "");
//                detailsCrawler.parseXMLToAnimeModel(content, anime);
//                System.out.println(anime);
//                System.out.println("*".repeat(10));
//            }
//
//            break;
//        }
    }
}