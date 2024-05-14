package haut.zsc.music.model;

import lombok.Data;

@Data
public class Review {
	private int reviewId;
	private int userId;
	private int songId;
	private String review;
	private String reviewTime;
	//评论点赞数目
	private int likeCoefficient;
	//发表该评论的用户昵称
	private String userName;
	//当前用户是否对该评论点赞
	private boolean whetherLiked;



}
