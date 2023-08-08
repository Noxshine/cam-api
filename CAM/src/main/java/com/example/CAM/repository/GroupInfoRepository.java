package com.example.CAM.repository;

import com.example.CAM.domain.core.Group.GroupInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupInfoRepository extends JpaRepository<GroupInfo, Long> {
    @Query("select u.id from GroupInfo u where u.groupId = :id")
    Long getGroupInfoIDbyGroupID(@Param("id")Long groupid);
}
