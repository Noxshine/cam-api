package com.example.CAM.repository;

import com.example.CAM.domain.core.Group.Group;
import com.example.CAM.domain.core.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
    public Group getGroupById(Long groupid);
    @Query("select gr.displayName from Group gr where gr.displayName = :grname")
    Optional<String> existsByGrName(@Param("grname")String grname);

    @Query("select gr.id from Group gr where gr.externalId = :uuid")
    Long getIdByUUID(@Param("uuid") UUID uuid);

    @Procedure("GROUP_LIST")
    List<Group> getGroupList(String filter1, String filter2, String filter3,
                           int startIndex, int itemPerPage, String ascOrder, String descOrder);

}
