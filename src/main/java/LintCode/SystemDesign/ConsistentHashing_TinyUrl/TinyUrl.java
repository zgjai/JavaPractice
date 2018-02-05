package LintCode.SystemDesign.ConsistentHashing_TinyUrl;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a long url, make it shorter. To make it simpler, let's ignore the domain name.
 * 
 * You should implement two methods:
 * 
 * longToShort(url). Convert a long url to a short url. shortToLong(url). Convert a short url to a long url starts with
 * http://tiny.url/. You can design any shorten algorithm, the judge only cares about two things:
 * 
 * The short key's length should equal to 6 (without domain and slash). And the acceptable characters are [a-zA-Z0-9].
 * For example: abcD9E No two long urls mapping to the same short url and no two short urls mapping to the same long
 * url.
 */
public class TinyUrl {

    private static final String SHORT_PRE = "http://tiny.url/";
    private static final String NUMBER = "0123456789";
    private static final String SMALL_CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static final String BIG_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String CHARS = NUMBER + SMALL_CHARS + BIG_CHARS;
    private static final int CHARS_LEN = CHARS.length();
    private static final Character ZERO_CHAR = CHARS.charAt(0);

    private int globalId = 0;
    private Map<Integer, String> idToUrl = new HashMap<>();
    private Map<String, Integer> urlToId = new HashMap<>();

    /**
     * @param url a long url
     * @return a short url starts with http://tiny.url/
     */
    public String longToShort(String url) {
        // Write your code here
        if (urlToId.containsKey(url)) {
            return SHORT_PRE + idToShortKey(urlToId.get(url));
        }
        globalId++;
        idToUrl.put(globalId, url);
        urlToId.put(url, globalId);
        return SHORT_PRE + idToShortKey(globalId);
    }

    private String idToShortKey(int id) {
        StringBuilder shortKey = new StringBuilder();
        while (id > 0) {
            shortKey.insert(0, CHARS.charAt(id % CHARS_LEN));
            id = id / CHARS_LEN;
        }
        while (shortKey.length() < 6) {
            shortKey.insert(0, ZERO_CHAR);
        }
        return shortKey.toString();
    }

    /**
     * @param url a short url starts with http://tiny.url/
     * @return a long url
     */
    public String shortToLong(String url) {
        // Write your code here
        String key = url.substring(SHORT_PRE.length());
        return idToUrl.get(shortKeyToId(key));
    }

    private int shortKeyToId(String key) {
        int id = 0;
        for (int i = 0; i < key.length(); i++) {
            id = id * CHARS_LEN + CHARS.indexOf(key.charAt(i));
        }
        return id;
    }
}