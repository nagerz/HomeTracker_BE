package com.myhomebe.repository;

import com.myhomebe.model.RoomMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface RoomMaterialRepository extends JpaRepository<RoomMaterial, Long> {
  // @Query("SELECT rm.element_type, rm FROM RoomMaterial rm JOIN rm.room r JOIN rm.material m WHERE r.id = :room_id GROUP BY rm.element_type")
  //  Collection<RoomMaterial> roomMaterialsByType(@Param("room_id") Long id);
  @Query("SELECT rm FROM RoomMaterial rm JOIN rm.room r WHERE r.id = :room_id")
   List<RoomMaterial> roomMaterialsByType(@Param("room_id") Long id);
}
