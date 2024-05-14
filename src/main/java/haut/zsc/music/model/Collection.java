package haut.zsc.music.model;

import lombok.Data;

/**
 * 收藏
 */
@Data
public class Collection {
	private int collectionId;
	private int userId;
	private int songId;

	public Collection() {

	}

	public Collection(int userId,int songId) {
		this.userId=userId;
		this.songId=songId;
	}

}
