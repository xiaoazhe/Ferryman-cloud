package com.ferry.blog.service;

import com.ferry.server.blog.entity.Gossip;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author: 摆渡人
 * @Date: 2021/7/6
 */
public interface GossipService {

    List <Gossip> findAll();

    Gossip findById(String id);

    void save(Gossip gossip);

    void update(Gossip gossip);

    void deleteById(String id);

    Page <Gossip> pageQuery(String parentid, int page, int size);

    Page <Gossip> pageByUser(String userId, int page, int size);

    void addthumbup(String id);

    List<Gossip> findAllByPre(String gossipId);

    Page<Gossip> findPage(int page, int size, String content);
}
