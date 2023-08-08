package com.example.CAM.repository;

import com.example.CAM.domain.core.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
     @Query("select user from User user where user.id = :userid")
     User getUserById(@Param("userid") Long userid);

     @Procedure("USER_LIST")
     List<User> getUserList(String filter1, String filter2, String filter3,
                                   int startIndex, int itemPerPage, String ascOrder, String descOrder);

     @Query("select e.value from Email e where e.value = :mail")
     Optional<String> existsUserByMail(@Param("mail")String tempmail);
     @Query("select u.userName from User u where u.userName= :username")
     Optional<String> existsUserByUsername(@Param("username")String username);

     @Query("select u.id from User u where u.externalId = :uuid")
     Long getIdByUUID(@Param("uuid") UUID uuid);
     @Modifying
     @Query("delete from User u where u.id = :id")
     void deleteById(@Param("id")Long userId);
}
