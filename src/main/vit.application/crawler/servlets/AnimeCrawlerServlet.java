package crawler.servlets;

import java.io.IOException;
import java.util.*;

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
import model.anime47.CategoryEntity;
import model.anime47.MovieDetailedEntity;
import model.anime47.MovieEntity;
import model.test.Anime;
import utils.HtmlNormalization;

@WebServlet(name = "CrawlerServlet", urlPatterns = {"/CrawlerServlet"})
public class AnimeCrawlerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {

//        crawl ANIME 47
        String link = WebConstance.ANIME_PAGE;
        AnimeJPA animeJPA = new AnimeJPA();
        List<CategoryEntity> categories = animeJPA.getCategories();
        Anime47Crawler crawler = new Anime47Crawler();
        Anime47DetailsCrawler detailsCrawler = new Anime47DetailsCrawler();
//
//        detailsCrawler.parseXMLToAnimeModel(content, new Anime());
        for (int i = 2; i < 97; i++) {
            String content = HtmlNormalization.refineHtml(CrawlingHelper.normalizeHTML(link));
            Collection<MovieEntity> movieEntities = crawler.parseXMLToAnimeModel(content);

            for (MovieEntity movieEntity : movieEntities) {
                content = HtmlNormalization.refineHtml(CrawlingHelper.normalizeHTML(movieEntity.getMovieLink()));
                String expression = "<a.*?>";
                content = content.replaceAll(expression, "").replaceAll("</a>", "");
                detailsCrawler.parseXMLToAnimeModel(content, movieEntity, new ArrayList<>(categories));
                animeJPA.persistMovie(movieEntity);
            }

            link = "https://anime47.com/danh-sach/phim-moi/" + i + ".html";
            //save Collections animes
        }

        // crawl AnimeMoiz
//        String link = WebConstance.VIETANIME_PAGE;
//        String content = "";
//        ParsingResult<AnimeEntity> result;
//        AnimeMoizCrawler crawler = new AnimeMoizCrawler();
//        AnimeMoizDetailsCrawler detailsCrawler = new AnimeMoizDetailsCrawler();
//
//        for (int i = 2; i <= 21; i++) {
//            content = HtmlNormalization.refineHtml(CrawlingHelper.normalizeHTML(link));
//            result = crawler.parseXMLToAnimeObject(content);
//            Collection<AnimeEntity> animes = result.getData();
//            for (AnimeEntity anime : animes) {
//                content = HtmlNormalization.refineDetailHtml(CrawlingHelper.normalizeHTML(anime.getAnimeLink()));
//                //ghi file xml
//                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\vitpt\\Downloads\\study\\semester_8\\prx\\anime\\anime.html"));
//                bufferedWriter.write(content);
//                bufferedWriter.close();
//                detailsCrawler.parseXMLToAnimeObject(content, anime);
//                if (anime != null){
//                    AnimeJPA jpa = new AnimeJPA();
//                    jpa.persistAnime(anime);
//                }
//                System.out.println(anime);
//                System.out.println("*".repeat(10));
//            }
//            link = "http://www.phimmoiz.com/the-loai/phim-hoat-hinh/page-" + i + ".html";
//        }
    }
}
