package com.acme.fromzeroapi.message.domain.model.aggregates;

import com.acme.fromzeroapi.message.domain.model.entities.Message;
import com.acme.fromzeroapi.profiles.domain.model.aggregates.Company;
import com.acme.fromzeroapi.profiles.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Chat extends AuditableAbstractAggregateRoot<Chat> {

    @ManyToOne
    @JoinColumn(name = "developer_id")
    private Developer developer;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Message> messages = new ArrayList<>();

    public Chat(Developer developer, Company company) {
        this.developer = developer;
        this.company = company;
    }

    public Chat() {
    }

    public void addMessage(Message message) {
        messages.add(message);
        message.setChat(this);
    }

    public void removeMessage(Message message) {
        messages.remove(message);
    }

    public void removeMessageById(Long id) {
        messages.removeIf(message -> message.getId().equals(id));
    }

    public void removeMessages() {
        messages.clear();
    }

}
