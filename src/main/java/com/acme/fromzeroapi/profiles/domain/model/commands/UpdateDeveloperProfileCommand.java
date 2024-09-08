package com.acme.fromzeroapi.profiles.domain.model.commands;

public record UpdateDeveloperProfileCommand(Long id, String description, String country, String phone, String specialties, String profileImgUrl) {
}
