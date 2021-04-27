package com.tsinghua.course.Biz.Processor;

import com.tsinghua.course.Base.Model.Friend;
import com.tsinghua.course.Base.Model.TimeLineSync;
import com.tsinghua.course.Base.Model.User;
import com.tsinghua.course.Base.Repository.TimeLineSyncRepository;
import com.tsinghua.course.Base.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述 用户原子处理器，所有与用户相关的原子操作都在此处理器中执行
 **/
@Component
public class UserProcessor {
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    TimeLineSyncRepository timeLineSyncRepository;

    @Autowired
    UserRepository userRepository;
    /** 根据用户名从数据库中获取用户 */
//    public User getUserByUsername(String username) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where(KeyConstant.USERNAME).is(username));
//        return mongoTemplate.findOne(query, User.class);
//    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public User getUserByWeixinId(String weixinId){
        return  userRepository.findUserByWeixinId(weixinId);
    }

    public User getUserById(String id){
        return userRepository.findUserById(id);
    }

    public User addFriend(User user, User friend){
        List<Friend> friends = user.getFriends();
        friends.add(new Friend(friend.getWeixinId(),friend.getTimeLineSyncId(),friend.getUsername()));
        this.saveUser(user);
        return user;
    }

    public User createUser(String weixinId,String username,String password){
        TimeLineSync timeLineSync = new TimeLineSync();
        timeLineSyncRepository.save(timeLineSync);
        User user = new User(weixinId,username,password,timeLineSync.getId());
        saveUser(user);
        return user;
    }

    public User updateUser(User user, String weixinId, String username, String password){
        user.setWeixinId(weixinId);
        user.setUsername(username);
        user.setPassword(password);
        saveUser(user);
        return user;
    }

    // Attention: check length
    public List<User> getUsersByWeixinIds(String... weixinIds){
        List<User> users = new ArrayList<>();
        for (String weixinId:weixinIds){
            User user = getUserByWeixinId(weixinId);
            if(user!=null){
                users.add(user);
            }
        }
        return users;
    }

}
