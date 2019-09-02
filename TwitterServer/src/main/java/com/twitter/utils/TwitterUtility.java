package com.twitter.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Component;

import com.twitter.dto.FriendTweetDTO;
import com.twitter.dto.ProfileFetchDTO;
import com.twitter.dto.TweetFetchListDTO;

@Component
public class TwitterUtility {

	public ProfileFetchDTO mapDataWithDTO(TwitterProfile twitterProfile) {
		ProfileFetchDTO twitterFetchDTO = new ProfileFetchDTO();
		twitterFetchDTO.setDescription(twitterProfile.getDescription());
		twitterFetchDTO.setId(twitterProfile.getId());

		return twitterFetchDTO;
	}

	public TweetFetchListDTO mapDataWithListDTO(SearchResults searchResults) {
		TweetFetchListDTO tweetFetchListDTO = new TweetFetchListDTO();
		List<String> tweetFetchDTOList = new ArrayList<>();
		for (Tweet tweet : searchResults.getTweets()) {
			tweetFetchDTOList.add(tweet.getText());
		}
		tweetFetchListDTO.setTweetFetchDTOList(tweetFetchDTOList);

		return tweetFetchListDTO;
	}

	public FriendTweetDTO[] mapDataWithDTO(List<Tweet> al) {
		FriendTweetDTO[] ft = new FriendTweetDTO[al.size()];

		for (int i = 0; i < ft.length; i++) {
			ft[i] = new FriendTweetDTO();
			ft[i].setId(al.get(i).getId());
			ft[i].setUsername(al.get(i).getText());
		}

		return ft;
	}

}
