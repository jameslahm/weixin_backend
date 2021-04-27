package com.tsinghua.course.Base.Repository;

import com.tsinghua.course.Base.Model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface MessageRepository extends MongoRepository<Message,String> {

}
