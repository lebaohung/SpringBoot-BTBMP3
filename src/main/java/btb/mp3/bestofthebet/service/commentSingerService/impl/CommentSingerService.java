package btb.mp3.bestofthebet.service.commentSingerService.impl;

import btb.mp3.bestofthebet.model.Comment_Singer;
import btb.mp3.bestofthebet.service.IService;

import java.util.List;

public interface CommentSingerService extends IService<Comment_Singer> {

    List<Comment_Singer> deleteByUserId(Long userId);
}
