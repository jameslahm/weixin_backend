package com.tsinghua.course.Base.Repository;

import com.tsinghua.course.Base.Model.TimeLineSync;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TimeLineSyncRepository extends MongoRepository<TimeLineSync,String> {
    public TimeLineSync findTimeLineSyncById();
}
