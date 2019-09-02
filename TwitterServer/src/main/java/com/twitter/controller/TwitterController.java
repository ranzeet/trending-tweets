package com.twitter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.dto.FriendTweetDTO;
import com.twitter.dto.ProfileFetchDTO;
import com.twitter.dto.TweetFetchListDTO;
import com.twitter.service.TwitterService;

@RestController
@RequestMapping(value = "/twitterspring")
public class TwitterController {

	@Autowired
	TwitterService twitterService;

	@GetMapping(value = "/profile")
	public TwitterProfile getProfile() {
		return twitterService.getTwitterProfile();
	}

	@GetMapping(value = "/profile/{profileID}")
	public ProfileFetchDTO getProfile(@PathVariable String profileID) {
		return twitterService.getProfileBasicData(profileID);
	}

	@GetMapping(value = "/tweets")
	public TweetFetchListDTO getUniqueTweets() {
		return twitterService.getUniqueTweets();
	}

	@GetMapping(value = "/friends")
	public List<Tweet> getFriendTweet() {
		return twitterService.getFriendTweet();
	}

	@GetMapping(value = "/friends/{friendID}")
	public FriendTweetDTO[] getFriendTweet(@PathVariable String friendID) {
		return twitterService.getFriendCustomTweet(friendID);
	}
}
