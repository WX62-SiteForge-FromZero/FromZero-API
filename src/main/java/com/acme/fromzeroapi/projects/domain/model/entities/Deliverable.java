package com.acme.fromzeroapi.projects.domain.model.entities;

import com.acme.fromzeroapi.projects.domain.model.commands.CreateDeliverableCommand;
import com.acme.fromzeroapi.projects.domain.model.events.DeliverableCompletedEvent;
import com.acme.fromzeroapi.projects.domain.model.events.UpdateProjectProgressEvent;
import com.acme.fromzeroapi.projects.domain.model.valueObjects.DeliverableState;
import com.acme.fromzeroapi.projects.domain.model.aggregates.Project;
import com.acme.fromzeroapi.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Entity
public class Deliverable extends AuditableAbstractAggregateRoot<Deliverable> {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/

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

    public void updateProjectProgress(Long projectId, Long completedDeliverables, Integer totalDeliverables){
        this.registerEvent(new UpdateProjectProgressEvent(this,projectId,completedDeliverables,totalDeliverables));
    }

}
