package com.myhomebe.controller;

import com.myhomebe.model.Room;
import com.myhomebe.repository.RoomRepository;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

    @Autowired
    RoomRepository repository;

    @GetMapping(value = "/{id}")
    public Optional<Room> findRoom(@PathVariable("id") long id) {

      return repository.findById(id);
    }

    // @GetMapping(value = "/{type}")
    // public List<Room> findByType(@PathVariable("type") String type) {
    //
    //   List<Room> rooms = repository.findByType(type);
    //   return rooms;
    // }

    @GetMapping
    public List<Room> getRooms() {
      System.out.println("Get all Rooms...");

      List<Room> rooms = new ArrayList<>();
      repository.findAll().forEach(rooms::add);

      return rooms;
    }

    @PostMapping
    public Room createRoom(@RequestBody Room room) {
      System.out.println("Create Room with name " + room.getName());

      Room _room = repository.save(room);

      return _room;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable("id") long id) {
      System.out.println("Delete Room with ID = " + id + "...");

      repository.deleteById(id);

      return new ResponseEntity<>("Room has been deleted!", HttpStatus.OK);
    }
}
