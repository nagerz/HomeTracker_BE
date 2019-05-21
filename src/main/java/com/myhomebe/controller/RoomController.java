package com.myhomebe.controller;

import com.myhomebe.model.Room;
import com.myhomebe.repository.RoomRepository;
import com.myhomebe.exceptions.SpringException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

    @Autowired
    RoomRepository repository;

    @GetMapping(value = "/{id}")
    public Optional<Room> findRoom(@PathVariable("id") long id) {

      return repository.findById(id);
    }

    @GetMapping
    public List<Room> getRooms() {
      List<Room> rooms = new ArrayList<>();
      repository.findAll().forEach(rooms::add);

      return rooms;
    }

    @PostMapping
    public Room createRoom(@RequestBody Room room) {
      Room _room = repository.save(room);

      return _room;
    }

    @PatchMapping("/{id}")
    Optional<Room> updateRoom(@RequestBody Room updRoom, @PathVariable Long id) {
      return repository.findById(id)
      .map(room -> {
        room.setName(updRoom.getName());
        room.setType(updRoom.getType());
        room.setDescription(updRoom.getDescription());
        return repository.save(room);
      });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable("id") long id) {
      repository.deleteById(id);

      return new ResponseEntity<>("Room deleted.", HttpStatus.OK);
    }
}
