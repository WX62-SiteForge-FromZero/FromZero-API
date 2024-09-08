package com.acme.fromzeroapi.projects.interfaces.rest.resources;

import java.util.List;

public record CreateProjectResource(
        String name, String description,Long ownerId,List<Integer> languages,
        List<Integer> frameworks,String type,String budget, String methodologies){

}
