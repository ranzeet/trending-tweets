package com.twitter.service;

import java.util.List;

import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.TwitterProfile;

import com.twitter.dto.FriendTweetDTO;
import com.twitter.dto.ProfileFetchDTO;
import com.twitter.dto.TweetFetchListDTO;

public interface TwitterService {

	public TwitterProfile getTwitterProfile();

	public TweetFetchListDTO getUniqueTweets();

	public List<Tweet> getFriendTweet();

	public ProfileFetchDTO getProfileBasicData(String profileID);

	public FriendTweetDTO[] getFriendCustomTweet(String friendID);
}
