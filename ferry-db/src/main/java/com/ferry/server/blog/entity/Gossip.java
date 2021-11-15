package com.ferry.server.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class Gossip implements Serializable {

    /**
     *  id
     */
    @Id
    private String _id;

    /**
     *  吐槽内容
     */
    private String content;

    /**
     *  发布日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date publishtime;

    /**
     *  发布人ID
     */
    private String userid;

    /**
     *  发布人昵称
     */
    private String nickname;

    /**
     *  浏览量
     */
    private Integer visits;

    /**
     *  点赞数
     */
    private Integer thumbup;

    /**
     *  分享数
     */
    private Integer share;

    /**
     *  回复数
     */
    private Integer comment;

    /**
     *  是否可见 1可 0否
     */
    private String state;

    /**
     *  上级ID
     */
    private String parentid;

    private List<Gossip> gossipList;

    public List<Gossip> getGossipList() {
        return gossipList;
    }

    public void setGossipList(List<Gossip> gossipList) {
        this.gossipList = gossipList;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getVisits() {
        return visits;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
    }

    public Integer getThumbup() {
        return thumbup;
    }

    public void setThumbup(Integer thumbup) {
        this.thumbup = thumbup;
    }

    public Integer getShare() {
        return share;
    }

    public void setShare(Integer share) {
        this.share = share;
    }

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }
}
