package LintCode.SystemDesign;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangguijiang on 2017/3/17.
 */
class Tweet {
    public int id;
    public int user_id;
    public String text;

    public Tweet(int user_id, String text) {
        this.user_id = user_id;
        this.text = text;
    }

    public static Tweet create(int user_id, String tweet_text) {
        // This will create a new tweet object,
        // and auto fill id
        return new Tweet(user_id, tweet_text);
    }
}

class TweetSort implements Comparator<Tweet> {
    public int compare(Tweet o1, Tweet o2) {
        return Integer.valueOf(o2.id).compareTo(o1.id);
    }
}

public class MiniTwitter {

    private Map<Integer, List<Tweet>> userTweetMap;
    private Map<Integer, List<Integer>> followMap;

    public MiniTwitter() {
        // initialize your data structure here.
        this.userTweetMap = new HashMap<Integer, List<Tweet>>();
        this.followMap = new HashMap<Integer, List<Integer>>();
    }

    // @param user_id an integer
    // @param tweet a string
    // return a tweet
    public Tweet postTweet(int user_id, String tweet_text) {
        // Write your code here
        Tweet tweet = Tweet.create(user_id, tweet_text);
        List<Tweet> tweetList = userTweetMap.get(user_id);
        if (tweetList == null) {
            tweetList = new ArrayList<Tweet>();
        }
        tweetList.add(tweet);
        userTweetMap.put(user_id, tweetList);
        return tweet;
    }

    // @param user_id an integer
    // return a list of 10 new feeds recently
    // and sort by timeline
    public List<Tweet> getNewsFeed(int user_id) {
        // Write your code here
        List<Tweet> userTweetList = getTimeline(user_id);
        List<Tweet> tweetList = new ArrayList<Tweet>(userTweetList);
        List<Integer> friendList = followMap.get(user_id);
        if (friendList == null || friendList.size() == 0) {
            return tweetList;
        }
        for (Integer item : friendList) {
            List<Tweet> tmpList = userTweetMap.get(item);
            if (tmpList == null) {
                continue;
            }
            tweetList.addAll(tmpList);
        }
        Collections.sort(tweetList, new TweetSort());
        int end = tweetList.size() >= 10 ? 10 : tweetList.size();
        return tweetList.subList(0, end);
    }

    // @param user_id an integer
    // return a list of 10 new posts recently
    // and sort by timeline
    public List<Tweet> getTimeline(int user_id) {
        // Write your code here
        List<Tweet> tweetList = userTweetMap.get(user_id);
        if (tweetList == null) {
            return new ArrayList<Tweet>();
        }
        Collections.sort(tweetList, new TweetSort());
        if (tweetList.size() <= 10) {
            return tweetList;
        }
        return tweetList.subList(0, 10);
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id follows to_user_id
    public void follow(int from_user_id, int to_user_id) {
        // Write your code here
        List<Integer> friendList = followMap.get(from_user_id);
        if (friendList == null) {
            friendList = new ArrayList<Integer>();
        }
        friendList.add(to_user_id);
        followMap.put(from_user_id, friendList);
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id unfollows to_user_id
    public void unfollow(int from_user_id, int to_user_id) {
        // Write your code here
        List<Integer> friendList = followMap.get(from_user_id);
        friendList.remove(friendList.indexOf(to_user_id));
        followMap.put(from_user_id, friendList);
    }

    public static void main(String[] args) {
        MiniTwitter miniTwitter = new MiniTwitter();
        miniTwitter.postTweet(1, "LintCode is Good!!!");
        miniTwitter.getNewsFeed(1);
        miniTwitter.getTimeline(1);
        miniTwitter.follow(2, 1);
        miniTwitter.follow(2, 3);
        miniTwitter.postTweet(3, "LintCode is Cool!!!");
        miniTwitter.postTweet(3, "How to do A + B problem?");
        miniTwitter.postTweet(3, "I have accepted A + B problem.");
        miniTwitter.postTweet(3, "I favorite A + B problem.");
        miniTwitter.postTweet(1, "I favorite A + B problem too.");
        miniTwitter.postTweet(2, "Nani?");
        miniTwitter.postTweet(2, "I want to solve this problem now.");
        miniTwitter.postTweet(3, "I want to solve this problem now.");
        miniTwitter.postTweet(3, "The problem is so easy.");
        miniTwitter.postTweet(1, "hehe.");
        miniTwitter.postTweet(2, "Let's to do it together.");
        miniTwitter.postTweet(2, "haha");
        miniTwitter.getNewsFeed(2);
        miniTwitter.getTimeline(2);
        miniTwitter.getNewsFeed(1);
        miniTwitter.follow(1, 2);
        miniTwitter.getNewsFeed(1);
        miniTwitter.follow(1, 3);
        miniTwitter.getNewsFeed(1);
        miniTwitter.unfollow(2, 1);
        miniTwitter.getNewsFeed(2);
    }
}
