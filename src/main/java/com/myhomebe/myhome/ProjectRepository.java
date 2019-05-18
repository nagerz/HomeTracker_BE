package com.myhomebe.myhome;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ProjectRepository extends JpaRepository<Project, Long> {
}
