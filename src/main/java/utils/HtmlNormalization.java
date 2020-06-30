package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import checker.XMLSyntaxChecker;

public class HtmlNormalization {
    public static String refineHtml(String htmlString) {
        XMLSyntaxChecker xmlSyntaxChecker = new XMLSyntaxChecker();
        htmlString = getBody(htmlString);
        htmlString = xmlSyntaxChecker.check(htmlString);
        htmlString = removeMiscellAneousTags(htmlString);
        // htmlString = getBody(htmlString);

        return htmlString;
    }

    private static String removeMiscellAneousTags(String src) {
        String result = src;

        String expression = "\n";
        result = result.replaceAll(expression, "");

        expression = " {2}";
        result = result.replaceAll(expression, " ");
        //
        // expression = "<span .*?</span>";
        // result = result.replaceAll(expression, "");
        //
        // expression = "<sup.*?</sup>";
        // result = result.replaceAll(expression, "");

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
        Pattern pattern = Pattern.compile(".*?<body.*?>(.*?)</body>.*?");
        Matcher matcher = pattern.matcher(src);

        return matcher.matches() ? matcher.replaceAll("$1") : src;
    }
}