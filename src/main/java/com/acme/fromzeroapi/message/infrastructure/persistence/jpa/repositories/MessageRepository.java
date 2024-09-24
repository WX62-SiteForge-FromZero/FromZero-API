package com.acme.fromzeroapi.message.infrastructure.persistence.jpa.repositories;

import com.acme.fromzeroapi.message.domain.model.aggregates.Chat;
import com.acme.fromzeroapi.message.domain.model.entities.Message;
import com.acme.fromzeroapi.iam.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByChatOrderByCreatedAt(Chat chat);
}
