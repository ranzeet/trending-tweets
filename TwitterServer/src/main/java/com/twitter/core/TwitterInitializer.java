package com.twitter.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Component;

@Component
public class TwitterInitializer {

	@Value("${twitter.consumerKey}")
	private String twitterConsumerKey;

	@Value("${twitter.consumerSecret}")
	private String twitterConsumerSecret;

	@Value("${twitter.accessToken}")
	private String twitterAccessToken;

	@Value("${twitter.accessSecret}")
	private String twitterAccessSecret;

	public Twitter getTwitterInstance() {
		Twitter twitter = new TwitterTemplate(twitterConsumerKey, twitterConsumerSecret, twitterAccessToken, twitterAccessSecret); 
		return twitter;
	}
}
