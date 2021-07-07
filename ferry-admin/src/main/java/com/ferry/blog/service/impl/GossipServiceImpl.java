package com.ferry.blog.service.impl;

import com.ferry.blog.service.GossipService;
import com.ferry.common.utils.IdWorker;
import com.ferry.common.utils.StringUtils;
import com.ferry.server.blog.db.GossipDao;
import com.ferry.server.blog.entity.Gossip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author: 摆渡人
 * @Date: 2021/6/11
 */
@Service
public class GossipServiceImpl implements GossipService {

    @Resource
    private GossipDao gossipDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List <Gossip> findAll() {
        return gossipDao.findAll();
    }

    @Override
    public Gossip findById(String id) {
        Gossip gossip = gossipDao.findById(id).get();
        gossip.setVisits(gossip.getVisits() + 1);
        gossipDao.save(gossip);
        return gossip;
    }

    @Override
    public void save(Gossip gossip) {
        if (StringUtils.isBlank(gossip.get_id())) {
            gossip.set_id(idWorker.nextId() + "");
        }
        //初始化数据完善
        gossip.setPublishtime(new Date());//发布日期
        gossip.setVisits(0);//浏览量
        gossip.setShare(0);//分享数
        gossip.setThumbup(0);//点赞数
        gossip.setComment(0);//回复数
        gossip.setState("1");//状态
        //判断当前吐槽是否有父节点
        if(gossip.getParentid()!=null && !"".equals(gossip.getParentid())){
            //给父节点吐槽的回复数加一
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(gossip.getParentid()));
            Update update = new Update();
            update.inc("comment", 1);
            mongoTemplate.updateFirst(query, update, "gossip");
        }
        gossipDao.save(gossip);
    }

    @Override
    public void update(Gossip gossip) {
        gossipDao.save(gossip);
    }

    @Override
    public void deleteById(String id) {
        gossipDao.deleteById(id);
    }

    @Override
    public Page <Gossip> pageQuery(String parentid, int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        return gossipDao.findByParentid(parentid, pageable);
    }

    @Override
    public Page <Gossip> pageByUser(String userId, int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        return gossipDao.findByUserid(userId, pageable);
    }

    @Override
    public void addthumbup(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        //封装修改的数据内容
        Update update = new Update();
        update.inc("thumbup", 1);
        mongoTemplate.updateFirst(query, update, "gossip");
    }

    @Override
    public List <Gossip> findAllByPre(String gossipId) {
        return gossipDao.findAllByParentidAndState(gossipId, "1");
    }
}
