package com.tsinghua.course.Base.Repository;

import com.tsinghua.course.Base.Model.Group;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupRepository extends MongoRepository<Group,String> {
    public Group findGroupById(String id);
}
