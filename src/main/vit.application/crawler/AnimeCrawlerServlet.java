package crawler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constance.WebConstance;
import crawler.animehay.AnimeMoizCrawler;
import crawler.animehay.AnimeMoizDetailsCrawler;
import crawler.helper.CrawlingHelper;
import model.Anime;
import model.AnimeMoiz;
import utils.HtmlNormalization;

@WebServlet(name = "CrawlerServlet", urlPatterns = {"/CrawlerServlet"})
public class AnimeCrawlerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {

        //crawl ANIME 47
//        String content = HtmlNormalization.refineHtml(CrawlingHelper.normalizeHTML(WebConstance.ANIME_PAGE));
//
//        Anime47Crawler crawler = new Anime47Crawler();
//        Anime47DetailsCrawler detailsCrawler = new Anime47DetailsCrawler();
//
//        detailsCrawler.parseXMLToAnimeModel(content, new Anime());
//        while (true) {
//            Collection<Anime> animes = crawler.parseXMLToAnimeModel(content);
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
        // crawl AnimeMoiz
        String content = HtmlNormalization.refineHtml(CrawlingHelper.normalizeHTML(WebConstance.VIETANIME_PAGE));
        AnimeMoizCrawler crawler = new AnimeMoizCrawler();
        AnimeMoizDetailsCrawler detailsCrawler = new AnimeMoizDetailsCrawler();
        detailsCrawler.parseXMLToAnimeObject(content, new AnimeMoiz());
        while (true) {
            Collection<AnimeMoiz> animes = crawler.parseXMLToAnimeObject(content);
            for (AnimeMoiz anime : animes) {
                content = HtmlNormalization.refineHtml(CrawlingHelper.normalizeHTML(anime.getLink()));
                Pattern pattern = Pattern.compile("<div class=\"movie-meta-info\">(.+?)</div>");
                Matcher matcher = pattern.matcher(content);
                matcher.find();
                content = matcher.group(1);
                String exp = "<a.*?>";
                content = content.replaceAll(exp, "").replaceAll("</a>", "");
                exp = "<div class=\"clear\">";
                content = content.replaceAll(exp, "");
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\vitpt\\Downloads\\study\\semester_8\\prx\\anime\\anime.html"));
                bufferedWriter.write(content);
                bufferedWriter.close();
                detailsCrawler.parseXMLToAnimeObject(content, anime);
                System.out.println(anime);
                System.out.println("*".repeat(10));
            }
            break;
        }
    }
}