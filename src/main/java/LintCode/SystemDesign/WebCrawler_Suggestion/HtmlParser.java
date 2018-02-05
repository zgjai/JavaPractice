package LintCode.SystemDesign.WebCrawler_Suggestion;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parse a html page, extract the Urls in it.
 * 
 * Hint: use regex to parse html.
 */
public class HtmlParser {

    private Pattern pattern = Pattern.compile("\\s*(?i)href\\s*=\\s*(\"|')+([^\"'>\\s]*)", Pattern.CASE_INSENSITIVE);

    /**
     * @param content: content source code
     * @return: a list of links
     */
    public List<String> parseUrls(String content) {
        // Write your code here
        List<String> links = new ArrayList<String>();
        Matcher matcher = pattern.matcher(content);
        String url = null;
        while (matcher.find()) {
            url = matcher.group(2);
            if (url.length() == 0 || url.startsWith("#"))
                continue;
            links.add(url);
        }
        return links;
    }

    public static void main(String[] args) {
        String content = "<html>" + "  <body>" + "    <div>"
                + "      <a href=\"http://www.google.com/\" class=\"text-lg\">Google</a>"
                + "      <a href=\"http://www.facebook.com/\" style=\"display:none\">Facebook</a>" + "    </div>"
                + "    <div>" + "      <a href=\"http://www.linkedin.com/\">Linkedin</a>"
                + "      <a href = \"http://www.lintcode.com/\">LintCode</a>" + "    </div>" + "  </body>" + "</html>";
        HtmlParser htmlParser = new HtmlParser();
        htmlParser.parseUrls(content);
    }
}
