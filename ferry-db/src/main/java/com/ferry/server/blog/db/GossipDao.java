package com.ferry.server.blog.db;


import com.ferry.server.blog.entity.Gossip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface GossipDao extends MongoRepository <Gossip, String> {

    List <Gossip> findAllByParentidAndState(String parentid, String status);

    List <Gossip> findByParentid(String parentid);

    Page <Gossip> findAllByParentidIs(String parentId, Pageable pageable);

    Page <Gossip> findAllByParentidIsAndContentLike(String parentId, String content, Pageable pageable);

    public Page <Gossip> findByParentid(String parentid, Pageable pageable);

    public Page <Gossip> findByUserid(String userId, Pageable pageable);
}

