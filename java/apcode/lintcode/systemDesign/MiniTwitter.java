package lintcode.systemDesign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MiniTwitter {
    private HashMap<Integer, Set<Integer>> friendship;
    private List<Tweet> tweets;
    private final int TIMELINE_COUNT = 10;
    private final int NEWSFEED_COUNT = 10;

    public MiniTwitter() {
        // initialize your data structure here.
        // 首先设计需要的数据储存结构
        // 搜索tweets, using pull model
        // friendship -- HashMap + list, using set for duplicating and faster searching
        friendship = new HashMap<Integer, Set<Integer>>();
        // tweets list, need to recode the timestamp
        // timestamp就由在list中的顺序代替了
        // 其实不应该把所有的tweet存在一个list中，而应该用HashMap来按user_id
        // 为key来存储，这样timeline的时候就不用扫描全表了
        tweets = new ArrayList<Tweet>();
    }

    // @param user_id an integer
    // @param tweet a string
    // return a tweet
    public Tweet postTweet(int user_id, String tweet_text) {
        // using pull model
        Tweet newTweet = Tweet.create(user_id, tweet_text);
        tweets.add(newTweet);
        return newTweet;
    }

    // @param user_id an integer
    // return a list of 10 new feeds recently
    // and sort by timeline
    public List<Tweet> getNewsFeed(int user_id) {
        // get feeds from followers and user themselves
        Set<Integer> followers = friendship.get(user_id);
        if (followers == null) {
            followers = new HashSet<Integer>();
        }
        followers.add(user_id);
        List<Tweet> newsFeed = new ArrayList<Tweet>();
        int count = 0;
        for (int i = tweets.size() - 1; i >= 0; i--) {
            Tweet current = tweets.get(i);
            // System.out.println(i + "-" + "Tweet:" + current.text);
            if (count >= NEWSFEED_COUNT) {
                break;
            }
            if (followers.contains(current.user_id)) {
                newsFeed.add(current);
                count++;
            }
        }
        return newsFeed;
    }
        
    // @param user_id an integer
    // return a list of 10 new posts recently
    // and sort by timeline
    public List<Tweet>  getTimeline(int user_id) {
        // only themselves
        List<Tweet> timeline = new ArrayList<Tweet>();
        int count = 0;
        for (int i = tweets.size() - 1; i >= 0; i--) { // need descending order
            Tweet current = tweets.get(i);
            if (count >= TIMELINE_COUNT) {
                break;
            }
            if (current.user_id == user_id) {
                timeline.add(current);
                count++;
            }
        }
        return timeline;
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id follows to_user_id
    public void follow(int from_user_id, int to_user_id) {
        // Write your code here
        if (friendship.containsKey(from_user_id)) {
            friendship.get(from_user_id).add(to_user_id);
        } else {
            HashSet<Integer> followList = new HashSet<Integer>();
            followList.add(to_user_id);
            friendship.put(from_user_id, followList);
        }
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id unfollows to_user_id
    public void unfollow(int from_user_id, int to_user_id) {
        // Write your code here
        if (friendship.containsKey(from_user_id)) {
            friendship.get(from_user_id).remove((Integer)to_user_id);
        }
    }

    public static void main(String[] args) {
    }
}
class Tweet {
	 private static int max_id = 0;
     public int id;
     public int user_id;
     public String text;
     public Tweet(int id, int user_id, String tweet_text) {
    	 this.id = id;
    	 this.user_id = user_id;
    	 this.text = tweet_text;
     }
     public static Tweet create(int user_id, String tweet_text) {
         // This will create a new tweet object,
         // and auto fill
    	 max_id++;
    	 return new Tweet(max_id, user_id, tweet_text);
     }
}
