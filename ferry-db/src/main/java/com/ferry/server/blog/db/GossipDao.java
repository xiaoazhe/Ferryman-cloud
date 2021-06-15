package com.ferry.server.blog.db;


import com.ferry.server.blog.entity.Gossip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface GossipDao extends MongoRepository <Gossip, String> {

    List <Gossip> findAllByParentidAndState(String parentid, String status);

    public Page <Gossip> findByParentid(String parentid, Pageable pageable);

    public Page <Gossip> findByUserid(String userId, Pageable pageable);
}

