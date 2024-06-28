package com.deliveryboy.service.impl;

import com.deliveryboy.constants.AppConstants;
import com.deliveryboy.service.KafkaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaServiceImpl implements KafkaService {

    private static final Logger log = LoggerFactory.getLogger(KafkaServiceImpl.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public boolean updateLocation(String location) {

        try {
            kafkaTemplate.send(AppConstants.LOCATION_TOPIC_NAME, location);
            log.info("message produced");
            return true;
        }catch (Exception e){
            log.info("Exception message: {}", e.getMessage());
            return false;
        }
    }
}
