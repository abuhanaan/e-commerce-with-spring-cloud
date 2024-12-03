package com.abuhanaan.ecommerce.notification.repository;

import com.abuhanaan.ecommerce.notification.model.entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {

}
