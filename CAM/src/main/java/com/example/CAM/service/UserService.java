package com.example.CAM.service;

import com.example.CAM.domain.core.User.Email;
import com.example.CAM.domain.core.User.UserInfo;
import com.example.CAM.domain.message.AddUser.AddUserBody;
import com.example.CAM.domain.message.AddUser.EmailDTO;
import com.example.CAM.domain.message.GetListBody;
import com.example.CAM.domain.core.User.User;
import com.example.CAM.domain.message.Status;
import com.example.CAM.repository.UserInfoRepository;
import com.example.CAM.repository.UserRepository;

import com.example.CAM.domain.response.UserList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;

    public UserService(UserRepository userRepository, UserInfoRepository userInfoRepository) {
        this.userRepository = userRepository;
        this.userInfoRepository = userInfoRepository;
    }
    public String getUserById(Long userid){
        User user = userRepository.getUserById(userid);
        if(user==null){
            return null;
        }
        return user.toJson();
    }
    @Transactional
    public UserList getUserList (GetListBody userlistBody){

        String filter = userlistBody.getFilter();
        String[] filters = filter.split("\\s+");

        filters[0] = (Objects.equals(filters[0], "groupName")) ? "display_name" : "external_id";
        filters[1] = (Objects.equals(filters[1], "eq")) ? "=" : "like";

        int startIndex = userlistBody.getStartIndex() ;
        int itemPerPage = userlistBody.getItemsPerPage();
        String ascOrder = userlistBody.getAscOrderBy();
        String descOrder = userlistBody.getDescOrderBy();

        List<User> usList = userRepository.getUserList(filters[0], filters[1], filters[2],
                                                        startIndex-1, itemPerPage, ascOrder, descOrder);
        List<String> ulist = new ArrayList<>();
        for (User user : usList) {
            ulist.add(user.toJson());
        }

        UserList userList = new UserList(usList.size(), ulist, startIndex, itemPerPage);
        return userList;
    }

    public String setUserActivityStatus(Long userId, String activityStatus){
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            user.get().setActive(Boolean.valueOf(activityStatus));
            userRepository.save(user.get());
            System.out.println(user.get());
            return user.get().toJson();
        }
        return null;
    }
    public String addUser(AddUserBody u){
        String username = u.getUserName();
        if(userRepository.existsUserByUsername(username).isPresent()){
            return String.valueOf(Status.USER_EXIST);
        }
        User user = new User();

        Random random = new Random();
        Long randomLong = random.nextLong();

        user.setId(randomLong);
        user.setExternalId(UUID.randomUUID());
        user.setResourceType(u.getResourceType());
        user.setCreated(Instant.now());
        user.setUserName(u.getUserName());
        user.setDisplayName(u.getDisplayName());
        user.setFormatted(u.getFormatted());
        user.setFamilyName(u.getFamilyName());
        user.setGivenName(u.getGivenName());
        user.setActive(true);

        List<EmailDTO> emaildto = u.getEmail();
        if (!(emaildto == null)){
            List<Email> emails = new ArrayList<>();
            for(EmailDTO x : emaildto){
                String tempmail = x.getValue();
                if(userRepository.existsUserByMail(tempmail).isEmpty()){
                    Email email = new Email();
                    email.setUserId(83L);
                    email.setValue(x.getValue());
                    email.setType(x.getType());
                    email.setPrimary(x.getPrimary());
                    emails.add(email);
                } else{
                    return String.valueOf(Status.MAIL_DUPLICATE);
                }
            }
            user.setEmail(emails);
        }

        user.setProfileUrl(u.getProfileUrl());
        user.setTitle(u.getTitle());
        user.setUserType(u.getUserType());
        user.setOrganization(u.getOrganization());
        user.setDivision(u.getDivision());
        user.setDepartment(u.getDepartment());
        user.setManager_value(u.getManager_value());
        user.setManager_ref(u.getManager_ref());
        user.setManager_displayName(u.getDisplayName());
        user.setIsPrivileged(false);

        userRepository.save(user);

        Long id = userRepository.getIdByUUID(user.getExternalId());

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(id);
        userInfo.setValue(user.getExternalId());
        userInfo.setRef("https://userProfile/"+ user.getExternalId());
        userInfo.setDisplay_name(user.getDisplayName());
        userInfoRepository.save(userInfo);

        return String.valueOf(Status.OK);
    }

//    public String addUserAdmin() {
//        User user = new User();
//
//        Random random = new Random();
//        Long randomLong = random.nextLong();
//
//        user.setId(randomLong);
//        user.setExternalId(UUID.randomUUID());
//        userRepository.save(user);
//        return String.valueOf(Status.OK);
//    }
}
