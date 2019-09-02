package com.twitter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Service;

import com.twitter.core.TwitterInitializer;
import com.twitter.dto.FriendTweetDTO;
import com.twitter.dto.ProfileFetchDTO;
import com.twitter.dto.TweetFetchListDTO;
import com.twitter.service.TwitterService;
import com.twitter.utils.TwitterUtility;

@Service
public class TwitterServiceImpl implements TwitterService {

	@Autowired
	TwitterInitializer twitterInitializer;

	@Autowired
	TwitterUtility twitterUtility;

	@Override
	public TwitterProfile getTwitterProfile() {
		return twitterInitializer.getTwitterInstance().userOperations().getUserProfile("@ranzeet786");
	}

	@Override
	public TweetFetchListDTO getUniqueTweets() {
		return twitterUtility.mapDataWithListDTO(
				twitterInitializer.getTwitterInstance().searchOperations().search("#SaahoReview", 10));
	}

	@Override
	public List<Tweet> getFriendTweet() {
		return twitterInitializer.getTwitterInstance().timelineOperations().getUserTimeline("@sagarsm96");
	}

	@Override
	public ProfileFetchDTO getProfileBasicData(String profileID) {
		return twitterUtility
				.mapDataWithDTO(twitterInitializer.getTwitterInstance().userOperations().getUserProfile(profileID));
	}

	@Override
	public FriendTweetDTO[] getFriendCustomTweet(String friendID) {
		return twitterUtility
				.mapDataWithDTO(twitterInitializer.getTwitterInstance().timelineOperations().getUserTimeline(friendID));
	}

}
