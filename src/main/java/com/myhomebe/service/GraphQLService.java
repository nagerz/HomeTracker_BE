package com.myhomebe.service;

import com.myhomebe.model.Project;
import com.myhomebe.model.Room;
import com.myhomebe.model.RoomMaterial;
import com.myhomebe.model.Material;
import com.myhomebe.repository.ProjectRepository;
import com.myhomebe.repository.RoomRepository;
import com.myhomebe.repository.RoomMaterialRepository;
import com.myhomebe.repository.MaterialRepository;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GraphQLService {

  private final ProjectRepository projectRepository;
  private final RoomRepository roomRepository;
  private final RoomMaterialRepository roomMaterialRepository;
  private final MaterialRepository materialRepository;

  public GraphQLService(ProjectRepository projectRepository,
                        RoomRepository roomRepository,
                        RoomMaterialRepository roomMaterialRepository,
                        MaterialRepository materialRepository) {
                          this.projectRepository = projectRepository;
                          this.roomRepository = roomRepository;
                          this.roomMaterialRepository = roomMaterialRepository;
                          this.materialRepository = materialRepository;
                        }

  @GraphQLQuery(name = "projects")
  public List<Project> getProjects(){
      return projectRepository.findAll();
  }

  @GraphQLQuery(name = "rooms")
  public List<Room> getRooms(){
      return roomRepository.findAll();
  }

  @GraphQLQuery(name = "materials")
  public List<Material> getMaterials(){
      return materialRepository.findAll();
  }

  @GraphQLQuery(name = "getRoomMaterials")
  public Collection<Material> getRoomMaterials(@GraphQLArgument(name = "room_id") Long id){
    return materialRepository.roomMaterialsByType(id);
  }

  // @GraphQLQuery(name = "getRoomsMaterials")
  // public Collection<RoomMaterial> getRoomsMaterials(@GraphQLArgument(name = "room_id") Long id){
  //   return roomMaterialRepository.roomMaterialsByType(id);
  // }

  @GraphQLQuery(name = "getRoomsMaterials")
  public Map<String, List<Material>> getRoomsMaterials(@GraphQLArgument(name = "room_id") Long id){
    List<RoomMaterial> room_materials = roomMaterialRepository.roomMaterialsByType(id);
    Map<String, List<Material>> result =
    room_materials.stream().collect(Collectors.groupingBy(RoomMaterial::getElement_type, Collectors.mapping(RoomMaterial::getMaterial, Collectors.toList())));
    return result;
  }

  @GraphQLQuery(name = "project")
  public Optional<Project> getProjectById(@GraphQLArgument(name = "id") Long id){
      return projectRepository.findById(id);
  }

  @GraphQLQuery(name = "room")
  public Optional<Room> getRoomById(@GraphQLArgument(name = "id") Long id){
      return roomRepository.findById(id);
  }

  @GraphQLMutation(name = "createProject")
  public Project createProject(@GraphQLArgument(name = "project") Map<String, String> projectData,
                              @GraphQLArgument(name = "rooms") ArrayList<Room> roomsArray){
    Project project = new Project();
    project.setName(projectData.get("name"));
    project.setDescription(projectData.get("description"));
    project.setAddress(projectData.get("address"));
    project.setCity(projectData.get("city"));
    project.setState(projectData.get("state"));
    project.setZip_code(projectData.get("zip_code"));
    projectRepository.save(project);
    if (roomsArray != null) {
      List<Room> rooms=new ArrayList<Room>();
      for (Room room : roomsArray)
        {
          room.setProject(project);
          roomRepository.save(room);
          rooms.add(room);
        }
      project.setRooms(rooms);
    }
    return project;
  }

  @GraphQLMutation(name = "addProjectRoom")
  public Optional<Room> addRoomToProject(@GraphQLArgument(name = "project_id") Long id,
                              @GraphQLArgument(name = "room") Room room){
    return projectRepository.findById(id)
    .map(project -> {
      room.setProject(project);
      return roomRepository.save(room);
    });
  }

  @GraphQLMutation(name = "addRoomMaterial")
  public Optional<RoomMaterial> addMaterialToRoom(@GraphQLArgument(name = "room_id") Long id,
                              @GraphQLArgument(name = "element_type") String element_type,
                              @GraphQLArgument(name = "material") Material material){
    return roomRepository.findById(id)
    .map(room -> {
      Material newMaterial = materialRepository.save(material);
      RoomMaterial roomMaterial = new RoomMaterial();
      roomMaterial.setElement_type(element_type);
      roomMaterial.setRoom(room);
      roomMaterial.setMaterial(newMaterial);
      return roomMaterialRepository.save(roomMaterial);
    });
  }

  @GraphQLMutation(name = "updateProject")
  public Optional<Project> updateProject(@GraphQLArgument(name = "project") Project updProject){
    return projectRepository.findById(updProject.getId())
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
      if (updProject.getCity() != null) {
        project.setCity(updProject.getCity());
      }
      if (updProject.getState() != null) {
        project.setState(updProject.getState());
      }
      if (updProject.getZip_code() != null) {
        project.setZip_code(updProject.getZip_code());
      }
      return projectRepository.save(project);
    });
  }

  @GraphQLMutation(name = "updateRoom")
  public Optional<Room> updateRoom(@GraphQLArgument(name = "room") Room updRoom){
    return roomRepository.findById(updRoom.getId())
    .map(room -> {
      if (updRoom.getName() != null) {
        room.setName(updRoom.getName());
      }
      if (updRoom.getType() != null) {
        room.setType(updRoom.getType());
      }
      if (updRoom.getDescription() != null) {
        room.setDescription(updRoom.getDescription());
      }
      return roomRepository.save(room);
    });
  }

  @GraphQLMutation(name = "updateProjectMaterial")
  public Optional<RoomMaterial> updateProjectMaterial(@GraphQLArgument(name = "roomMaterial_id") Long id,
                                                  @GraphQLArgument(name = "element_type") String element_type,
                                                  @GraphQLArgument(name = "material") Material updMaterial){
    return roomMaterialRepository.findById(id)
      // or else throw not found exception
    .map(rm -> {
      Material material = rm.getMaterial();
      if (updMaterial != null) {
        if (updMaterial.getName() != null) {
          material.setName(updMaterial.getName());
        }
        if (updMaterial.getModel_number() != null) {
          material.setModel_number(updMaterial.getModel_number());
        }
        if (updMaterial.getBrand() != null) {
          material.setBrand(updMaterial.getBrand());
        }
        if (updMaterial.getVendor() != null) {
          material.setVendor(updMaterial.getVendor());
        }
        if (updMaterial.getManual_url() != null) {
          material.setManual_url(updMaterial.getManual_url());
        }
        if (updMaterial.getNotes() != null) {
          material.setNotes(updMaterial.getNotes());
        }
        if (updMaterial.getQuantity() != null) {
          material.setQuantity(updMaterial.getQuantity());
        }
        if (updMaterial.getUnit_price() != null) {
          material.setUnit_price(updMaterial.getUnit_price());
        }
      }
      if (element_type != null) {
        rm.setElement_type(element_type);
      }
      materialRepository.save(material);
      return roomMaterialRepository.save(rm);
    });
  }

  @GraphQLMutation(name = "deleteProject")
  public void deleteProject(@GraphQLArgument(name = "id") Long id){
    projectRepository.deleteById(id);
  }

  @GraphQLMutation(name = "deleteRoom")
  public void deleteRoom(@GraphQLArgument(name = "id") Long id){
    roomRepository.deleteById(id);
  }

  @GraphQLMutation(name = "deleteRoomMaterial")
  public void deleteRoomMaterial(@GraphQLArgument(name = "id") Long id){
    roomMaterialRepository.deleteById(id);
  }
}
