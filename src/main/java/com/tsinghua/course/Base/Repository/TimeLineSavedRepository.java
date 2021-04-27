package com.tsinghua.course.Base.Repository;

import com.tsinghua.course.Base.Model.TimeLineSaved;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface TimeLineSavedRepository extends MongoRepository<TimeLineSaved,String> {
    public TimeLineSaved findTimeLineSavedById(String id);

}
