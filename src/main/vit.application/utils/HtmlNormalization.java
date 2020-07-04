package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import checker.XMLSyntaxChecker;

public class HtmlNormalization {
    public static String refineHtml(String htmlString) {
        XMLSyntaxChecker xmlSyntaxChecker = new XMLSyntaxChecker();

        htmlString = removeMiscellAneousTags(htmlString);
        htmlString = xmlSyntaxChecker.check(htmlString);
        return htmlString;
    }
    public static String refineDetailHtml(String htmlString){
        XMLSyntaxChecker xmlSyntaxChecker = new XMLSyntaxChecker();
        htmlString = getBody(htmlString);
        htmlString = removeMiscellAneousTags(htmlString);
        htmlString = xmlSyntaxChecker.check(htmlString);
        return htmlString;
    }

    private static String removeMiscellAneousTags(String src) {
        String result = src;

        String expression = "\n";
        result = result.replaceAll(expression, "");

        expression = " {2}";
        result = result.replaceAll(expression, " ");

        expression = "&apos;";
        result = result.replaceAll(expression, "'");

        expression = "♡";
        result = result.replaceAll(expression, " ");

        expression = "<script.*?</script>";
        result = result.replaceAll(expression, "");

        expression = "<noscript.*?</noscript>";
        result = result.replaceAll(expression, "");

        expression = "<!--.*?-->";
        result = result.replaceAll(expression, "");

        expression = "&nbsp;?";
        result = result.replaceAll(expression, "");

        return result;
    }

    private static String getBody(String src) {
        Pattern pattern = Pattern.compile("<div class=\"MovieInfo TPost Single\">(.+)((\\s)+(.+))+</div>");
        Matcher matcher = pattern.matcher(src);

        return matcher.matches() ? matcher.replaceAll("$1") : src;
    }
}