package com.acme.fromzeroapi.deliverables.domain.model.aggregates;

import com.acme.fromzeroapi.deliverables.domain.model.commands.CreateDeliverableCommand;
import com.acme.fromzeroapi.deliverables.domain.model.valueObjects.DeliverableState;
import com.acme.fromzeroapi.projects.domain.model.aggregates.Project;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Entity
public class Deliverable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDate date;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliverableState state;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Setter
    @Column(columnDefinition = "TEXT")
    private String developerMessage;

    public Deliverable(CreateDeliverableCommand command, Project project) {
        this.name=command.name();
        this.description=command.description();
        this.date=command.date();
        this.state=DeliverableState.PENDIENTE;
        this.developerMessage=null;
        this.project=project;
    }

    public Deliverable(String name, String description, LocalDate date, Project project) {
        this.name=name;
        this.description=description;
        this.date=date;
        this.state=DeliverableState.PENDIENTE;
        this.developerMessage=null;
        this.project=project;
    }

    public Deliverable() {

    }

}
