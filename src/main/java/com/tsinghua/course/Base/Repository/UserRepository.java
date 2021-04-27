package com.tsinghua.course.Base.Repository;

import com.tsinghua.course.Base.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
    public User findUserByWeixinId(String weixinId);

    public User findUserById(String id);
}
