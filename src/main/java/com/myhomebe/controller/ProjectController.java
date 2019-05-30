package com.myhomebe.controller;

import com.myhomebe.model.Project;
import com.myhomebe.repository.ProjectRepository;
import com.myhomebe.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    @Autowired
    ProjectRepository repository;

    ProjectController(ProjectRepository repository) {
      this.repository = repository;
    }

    // Index
    @GetMapping
    List<Project> getProjects() {
      return repository.findAll();
    }

    // Create
    @PostMapping
    Project createProject(@RequestBody Project newProject) {
      return repository.save(newProject);
    }

    // Show
    @GetMapping(value = "/{id}")
    Project getProject(@PathVariable long id) {

      return repository.findById(id)
        .orElseThrow(() -> new NotFoundException(id));
    }

    // Update
    @PatchMapping("/{id}")
    Optional<Project> updateProject(@RequestBody Project updProject, @PathVariable Long id) {
      return repository.findById(id)
      .map(project -> {
        if (updProject.getName() != null) {
          project.setName(updProject.getName());
        }
        if (updProject.getDescription() != null) {
          project.setDescription(updProject.getDescription());
        }
        if (updProject.getAddress() != null) {
          project.setAddress(updProject.getAddress());
        }
        return repository.save(project);
      });
    }

    // Delete
    @DeleteMapping("/{id}")
    void deleteProject(@PathVariable long id) {
      repository.deleteById(id);
    }
}
