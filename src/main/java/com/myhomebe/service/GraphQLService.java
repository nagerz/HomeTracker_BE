package com.myhomebe.service;

import com.myhomebe.model.Project;
import com.myhomebe.model.Room;
import com.myhomebe.model.Material;
import com.myhomebe.repository.ProjectRepository;
import com.myhomebe.repository.RoomRepository;
import com.myhomebe.repository.MaterialRepository;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GraphQLService {

  private final ProjectRepository projectRepository;
  private final RoomRepository roomRepository;
  private final MaterialRepository materialRepository;

  public GraphQLService(ProjectRepository projectRepository,
                        RoomRepository roomRepository,
                        MaterialRepository materialRepository) {
                          this.projectRepository = projectRepository;
                          this.roomRepository = roomRepository;
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

  @GraphQLQuery(name = "project")
  public Optional<Project> getProjectById(@GraphQLArgument(name = "id") Long id){
      return projectRepository.findById(id);
  }

  @GraphQLQuery(name = "room")
  public Optional<Room> getRoomById(@GraphQLArgument(name = "id") Long id){
      return roomRepository.findById(id);
  }

  @GraphQLMutation(name = "createProject")
  public Project createProject(@GraphQLArgument(name = "project") Project project){
      return projectRepository.save(project);
  }

  @GraphQLMutation(name = "saveProject")
  public Project saveProject(@GraphQLArgument(name = "project") Project project){
      return projectRepository.save(project);
  }

  @GraphQLMutation(name = "deleteProject")
  public void deleteProject(@GraphQLArgument(name = "id") Long id){
       projectRepository.deleteById(id);
  }
}
