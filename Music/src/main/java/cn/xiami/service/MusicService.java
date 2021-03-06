package cn.xiami.service;

import cn.xiami.module.Music;

import java.util.List;

/**
 *  music 的接口，放除BaseService外的其他特殊的业务
 */
public interface MusicService extends BaseService<Music>{

    /**
     * 收藏歌曲
     * 即将cinfoID与musicId插入关联表
     * 并且要将cinfoNum的消息加一
     */
    boolean insertCinfoToMusic(int cinfoId,int musicId);

    /**
     * 获取musicId，即最大id+1
     */
    Integer selectMusicId();

    /**
     * 存入userToMusic
     *
     */
    void insertUserToMusic(String phoneNumber,int musicId);

    /**
     * 查询历史歌曲
     */
    List<Music> selectHistoryMusicByNumber(String phoneNumber);
}
