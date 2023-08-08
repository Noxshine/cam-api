package com.example.CAM.repository;

import com.example.CAM.domain.core.Group.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
    @Query("select ug from UserGroup ug where ug.user_id = :userId and ug.group_id = :groupId ")
    Optional<UserGroup> findByUserGrId(@Param("userId")Long userId, @Param("groupId")Long groupId);
}
