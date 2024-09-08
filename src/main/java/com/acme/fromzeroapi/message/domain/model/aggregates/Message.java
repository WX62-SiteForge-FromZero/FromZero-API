package com.acme.fromzeroapi.message.domain.model.aggregates;

import com.acme.fromzeroapi.message.domain.model.commands.CreateMessageCommand;
import com.acme.fromzeroapi.iam.domain.model.aggregates.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String subject;

    @Lob
    @Column(nullable = false,columnDefinition = "TEXT")
    private String emailBody;

    @ManyToOne
    @JoinColumn(name = "recipient_id", nullable = false)
    private User recipient;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @Column(nullable = false)
    private LocalDate sentTime;

    public Message(CreateMessageCommand createMessageCommand) {
        this.subject = createMessageCommand.subject();
        this.emailBody = createMessageCommand.emailBody();
        this.recipient = createMessageCommand.recipient();
        this.sender = createMessageCommand.sender();
        this.sentTime = LocalDate.now();
    }

    public Message() {}


}
