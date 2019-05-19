package com.myhomebe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ProjectRepository extends JpaRepository<Project, Long> {
}
