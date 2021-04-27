package com.tsinghua.course.Biz.Processor;

import com.tsinghua.course.Base.Model.Message;
import com.tsinghua.course.Base.Model.TimeLineSync;
import com.tsinghua.course.Base.Repository.TimeLineSyncRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TimeLineSyncProcessor {
    @Autowired
    TimeLineSyncRepository timeLineSyncRepository;

    public TimeLineSync getTimeLineSyncById(String id){
        return timeLineSyncRepository.findTimeLineSyncById(id);
    }

    public List<Message> getMessagesAfterTimestamp(TimeLineSync timeLineSync,int timestamp){
        List<Message> messages =  timeLineSync.getMessages().stream().filter((message)->{
            return message.getTimestamp()>timestamp;
        }).collect(Collectors.toList());
        return messages;
    }

    public TimeLineSync saveTimeLineSync(TimeLineSync timeLineSync){
        timeLineSyncRepository.save(timeLineSync);
        return timeLineSync;
    }

    public Message addMessageInToTimeLineSync(String timeLineId,Message message){
        TimeLineSync timeLineSync = getTimeLineSyncById(timeLineId);
        timeLineSync.getMessages().add(message);
        saveTimeLineSync(timeLineSync);
        return message;
    }

    public void addMessageInToTimeLineSyncs(List<String> timeLineIds,Message message){
        timeLineIds.forEach((timeLineId)->{
            addMessageInToTimeLineSync(timeLineId,message);
        });
    }
}
