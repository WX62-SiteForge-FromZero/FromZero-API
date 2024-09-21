package com.acme.fromzeroapi.projects.application.internal.queryServices;

import com.acme.fromzeroapi.projects.domain.model.entities.Deliverable;
import com.acme.fromzeroapi.projects.domain.model.queries.GetAllDeliverablesByProjectIdQuery;
import com.acme.fromzeroapi.projects.domain.model.queries.GetCompletedDeliverablesQuery;
import com.acme.fromzeroapi.projects.domain.model.queries.GetDeliverableByIdQuery;
import com.acme.fromzeroapi.projects.domain.model.valueObjects.DeliverableState;
import com.acme.fromzeroapi.projects.domain.services.DeliverableQueryService;
import com.acme.fromzeroapi.projects.infrastructure.persistence.jpa.repositories.DeliverableRepository;
import com.acme.fromzeroapi.projects.infrastructure.persistence.jpa.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliverableQueryServiceImpl implements DeliverableQueryService {
    private final DeliverableRepository deliverableRepository;
    private final ProjectRepository projectRepository;

    public DeliverableQueryServiceImpl(
            DeliverableRepository deliverableRepository,
            ProjectRepository projectRepository) {

        this.deliverableRepository = deliverableRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Deliverable> handle(GetAllDeliverablesByProjectIdQuery query) {

        var project = projectRepository.findById(query.projectId());
        if (project.isEmpty()){
            return List.of();
        }
        return this.deliverableRepository.findAllByProject(project.get());
    }

    @Override
    public Optional<Deliverable> handle(GetDeliverableByIdQuery query) {
        return this.deliverableRepository.findById(query.id());
    }

    @Override
    public Long handle(GetCompletedDeliverablesQuery query) {
        var command = new GetAllDeliverablesByProjectIdQuery(query.projectId());
        var deliverables = this.handle(command);
        return deliverables.stream()
                .filter(deliverable -> DeliverableState.COMPLETADO.equals(deliverable.getState()))
                .count();
    }
}
