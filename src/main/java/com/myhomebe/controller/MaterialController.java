package com.myhomebe.controller;

import com.myhomebe.model.Material;
import com.myhomebe.repository.MaterialRepository;
import com.myhomebe.exceptions.NotFoundException;
import com.myhomebe.exceptions.NotFoundAdvice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/materials")
public class MaterialController {

    @Autowired
    MaterialRepository repository;

    MaterialController(MaterialRepository repository) {
      this.repository = repository;
    }

    // Index
    @GetMapping
    List<Material> getMaterials() {
      return repository.findAll();
    }

    // Create
    @PostMapping
    Material createMaterial(@RequestBody Material newMaterial) {
      return repository.save(newMaterial);
    }

    // Show
    @GetMapping(value = "/{id}")
    Material getMaterial(@PathVariable long id) {

      return repository.findById(id)
        .orElseThrow(() -> new NotFoundException(id));
    }

    // Update
    @PatchMapping("/{id}")
    Optional<Material> updateMaterial(@RequestBody Material updMaterial, @PathVariable Long id) {
      return repository.findById(id)
      .map(material -> {
        // Add catches so doesn't reset value to null if not supplied.
        material.setName(updMaterial.getName());
        material.setVendor(updMaterial.getVendor());
        return repository.save(material);
      });
    }

    // Delete
    @DeleteMapping("/{id}")
    void deleteMaterial(@PathVariable long id) {
      repository.deleteById(id);
    }
}
