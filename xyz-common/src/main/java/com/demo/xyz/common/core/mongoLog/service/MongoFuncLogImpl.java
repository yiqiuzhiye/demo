package com.demo.xyz.common.core.mongoLog.service;//package com.cloudminds.crss.common.core.mongoLog.service;
//
//import com.cloudminds.crss.common.core.mongoLog.vo.HariLogData;
//import com.cloudminds.crss.common.core.util.JsonUtils;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.bson.Document;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Date;
//
//
//@Slf4j
//@Service
//@AllArgsConstructor
//@Transactional(rollbackFor = Exception.class)
//public class MongoFuncLogImpl implements MongoFuncLog {
//
//    private final MongoTemplate mongoTemplate;
//
//    public final static String MONGO_HARILOG = "harilog";
//
//    @Override
//    @Async
//    public void addHariLog(String robotCode, Object reqeust, Object response, int respCode, int type, String messageType,String level) {
//        try {
//            HariLogData h = new HariLogData();
//            h.setReq(JsonUtils.beanToJson(reqeust));
//            h.setRobotCode(robotCode);
//            h.setType(type);
//            h.setResp(JsonUtils.beanToJson(response));
//            h.setRespCode(respCode);
//            h.setMessageType(messageType);
//            h.setLevel(level);
//            h.setCreateTime(new Date());
//            Document data = mongoTemplate.save(Document.parse(JsonUtils.beanToJson(h)), MONGO_HARILOG);
//        } catch (Exception e) {
//            log.error("add hariLog error {}", e.getMessage());
//            log.error(e.getMessage(), e);
//        }
//    }
//
//
//}
