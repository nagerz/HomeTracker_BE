package com.myhomebe;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GraphQLQuery(name = "projects")
    public List<Project> getProjects(){
        return projectRepository.findAll();
    }

    @GraphQLQuery(name = "project")
    public Optional<Project> getProjectById(@GraphQLArgument(name = "id") Long id){
        return projectRepository.findById(id);
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
