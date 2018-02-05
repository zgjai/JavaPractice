package LintCode.SystemDesign.ConsistentHashing_TinyUrl;

import java.util.HashMap;
import java.util.Map;

/**
 * As a follow up for Tiny URL, we are going to support custom tiny url, so that user can create their own tiny url.
 */
public class TinyUrl2 {
    private static final String SHORT_PRE = "http://tiny.url/";
    private static final String NUMBER = "0123456789";
    private static final String SMALL_CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static final String BIG_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String CHARS = SMALL_CHARS + BIG_CHARS + NUMBER;
    private static final int CHARS_LEN = CHARS.length();
    private static final Character ZERO_CHAR = CHARS.charAt(0);

    private int globalId = 0;
    private Map<Integer, String> idToUrl = new HashMap<>();
    private Map<String, Integer> urlToId = new HashMap<>();

    private Map<String, String> customShortToLong = new HashMap<>();
    private Map<String, String> customLongToShort = new HashMap<>();

    /**
     * @param long_url: a long url
     * @param key: a short key
     * @return: a short url starts with http://tiny.url/
     */
    public String createCustom(String long_url, String key) {
        // write your code here
        if (!isSuitableKey(long_url, key)) {
            return "error";
        }
        if (customLongToShort.containsKey(long_url)) {
            return customLongToShort.get(long_url);
        }
        String shortUrl = SHORT_PRE + key;
        customLongToShort.put(long_url, shortUrl);
        customShortToLong.put(shortUrl, long_url);
        return shortUrl;
    }

    private boolean isSuitableKey(String longUrl, String key) {
        for (int i = 0; i < key.length(); i++) {
            if (CHARS.indexOf(key.charAt(i)) == -1) {
                return false;
            }
        }
        int id = shortKeyToId(key);
        if (idToUrl.containsKey(id) && !longUrl.equals(idToUrl.get(id))) {
            return false;
        }
        if (urlToId.containsKey(longUrl) && urlToId.get(longUrl) != id) {
            return false;
        }
        if (customLongToShort.containsKey(longUrl) && !customLongToShort.get(longUrl).equals(SHORT_PRE + key)) {
            return false;
        }
        return !customShortToLong.containsKey(SHORT_PRE + key)
                || customShortToLong.get(SHORT_PRE + key).equals(longUrl);
    }

    /**
     * @param url a long url
     * @return a short url starts with http://tiny.url/
     */
    public String longToShort(String url) {
        // Write your code here
        if (customLongToShort.containsKey(url)) {
            return customLongToShort.get(url);
        }
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
        if (customShortToLong.containsKey(url)) {
            return customShortToLong.get(url);
        }
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
