package com.myhomebe.repository;

import com.myhomebe.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
  @Query("SELECT m, rm.element_type FROM Material m JOIN m.roomMaterials rm JOIN rm.room r WHERE r.id = :room_id GROUP BY rm.element_type")
   Collection<Material> roomMaterialsByType(@Param("room_id") Long id);
}
