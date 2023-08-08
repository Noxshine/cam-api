package com.example.CAM.repository;

import com.example.CAM.domain.core.User.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {
    @Query("select u.id from UserInfo u where u.userId = :id")
    Long getUserInfoIDbyUserID(@Param("id")Long userid);
}
