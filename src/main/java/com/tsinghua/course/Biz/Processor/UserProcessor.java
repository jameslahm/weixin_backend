package com.tsinghua.course.Biz.Processor;

import com.tsinghua.course.Base.Model.Friend;
import com.tsinghua.course.Base.Model.TimeLineSaved;
import com.tsinghua.course.Base.Model.TimeLineSync;
import com.tsinghua.course.Base.Model.User;
import com.tsinghua.course.Base.Repository.TimeLineSavedRepository;
import com.tsinghua.course.Base.Repository.TimeLineSyncRepository;
import com.tsinghua.course.Base.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    TimeLineSavedRepository timeLineSavedRepository;
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

    public String addFriend(User user, User friend){
        TimeLineSaved timeLineSaved = new TimeLineSaved();
        timeLineSavedRepository.save(timeLineSaved);

        // Add friend into user's friends
        List<Friend> friends = user.getFriends();
        friends.add(new Friend(friend.getId(),timeLineSaved.getId(),friend.getUsername()));
        this.saveUser(user);

        // Add user into friend's friends;
        friends = friend.getFriends();
        friends.add(new Friend(user.getId(),timeLineSaved.getId(),user.getUsername()));
        this.saveUser(friend);

        return timeLineSaved.getId();
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
        if(password!=null){
            user.setPassword(password);
        }
        saveUser(user);
        return user;
    }

    // Attention: check length
    public List<User> getUsersByIds(List<String> ids){
        return userRepository.findAllByIdIn(ids);
    }

    public List<Friend.FriendDetail> getFriendDetailsByUser(User user){
        List<Friend> friends = user.getFriends();
        List<String> friendIds = friends.stream().map(friend -> {
            return friend.getId();
        }).collect(Collectors.toList());
        List<User> friendUsers = getUsersByIds(friendIds);
        List<Friend.FriendDetail> friendDetails = new ArrayList<>();
        for (int index=0;index<friendUsers.size();index++){
            friendDetails.add(new Friend.FriendDetail(friends.get(index),friendUsers.get(index)));
        }
        return friendDetails;
    }
}
