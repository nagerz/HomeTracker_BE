package com.myhomebe.models;

import com.myhomebe.model.Project;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.instanceOf;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectTest {

  // Project project = new Project();
  // ProjectRepository projectRepository = new ProjectRepository();
  // project.setName("New Project");
  // project.setDescription("Test project");
  // project.setAddress("Fake St.");
  // project.setCity("Denver");
  // project.setState("CO");
  // project.setZip_code("80205");
  // projectRepository.save(project);

//  @Test
//  public void testProjectHasId() {
//    Project project = new Project();
//    project.setId(1L);
//    Assert.assertEquals(1L, project.getId());
//  }

  @Test
  public void testProjectExists() {
    assertThat(new Project(), instanceOf(Project.class));
  }

  @Test
  public void testProjectHasName() {
    Project project = new Project();
    assertNull(project.getName());
    project.setName("Test Project");
    Assert.assertEquals("Test Project", project.getName());
  }

  @Test
  public void testProjectHasDescription() {
    Project project = new Project();
    project.setDescription("Test project description");
    Assert.assertEquals("Test project description", project.getDescription());
  }

  @Test
  public void testProjectHasAddress() {
    Project project = new Project();
    project.setAddress("123 Fake St.");
    Assert.assertEquals("123 Fake St.", project.getAddress());
  }

  @Test
  public void testProjectHasCity() {
    Project project = new Project();
    project.setCity("Denver");
    Assert.assertEquals("Denver", project.getCity());
  }

  @Test
  public void testProjectHasState() {
    Project project = new Project();
    project.setState("CO");
    Assert.assertEquals("CO", project.getState());
  }

  @Test
  public void testProjectHasZipcode() {
    Project project = new Project();
    project.setZip_code("80304");
    Assert.assertEquals("80304", project.getZip_code());
  }
}
