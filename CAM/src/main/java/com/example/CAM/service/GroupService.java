package com.example.CAM.service;

import com.example.CAM.domain.core.Group.Group;
import com.example.CAM.domain.core.Group.GroupInfo;
import com.example.CAM.domain.core.Group.UserGroup;
import com.example.CAM.domain.core.User.User;
import com.example.CAM.domain.message.AddGroupBody;
import com.example.CAM.domain.message.GetListBody;
import com.example.CAM.domain.message.Status;
import com.example.CAM.domain.response.GroupList;
import com.example.CAM.domain.response.UserList;
import com.example.CAM.repository.GroupInfoRepository;
import com.example.CAM.repository.GroupRepository;
import com.example.CAM.repository.UserGroupRepository;
import com.example.CAM.repository.UserInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;

@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final GroupInfoRepository groupInfoRepository;
    private final UserInfoRepository userInfoRepository;
    private final UserGroupRepository userGroupRepository;


    public GroupService(GroupRepository groupRepository, GroupInfoRepository groupInfoRepository, UserInfoRepository userInfoRepository, UserGroupRepository userGroupRepository) {
        this.groupRepository = groupRepository;
        this.groupInfoRepository = groupInfoRepository;
        this.userInfoRepository = userInfoRepository;
        this.userGroupRepository = userGroupRepository;
    }

    @Transactional
    public GroupList getlistgroup(GetListBody getListBody){

        String filter = getListBody.getFilter();
        String[] filters = filter.split("\\s+");

        filters[0] = (Objects.equals(filters[0], "groupName")) ? "display_name" : "external_id";
        filters[1] = (Objects.equals(filters[1], "eq")) ? "=" : "like";

        int startIndex = getListBody.getStartIndex() ;
        int itemPerPage = getListBody.getItemsPerPage();
        String ascOrder = getListBody.getAscOrderBy();
        String descOrder = getListBody.getDescOrderBy();

        List<Group> grList = groupRepository.getGroupList(filters[0], filters[1], filters[2],
                startIndex-1, itemPerPage, ascOrder, descOrder);
        List<String> glist = new ArrayList<>();

        for(Group group : grList) {
            glist.add(group.toJson());
        }

        GroupList userList = new GroupList(glist.size(), glist, startIndex, itemPerPage);
        return userList;

    }

    public String updateGroup(String op, Long groupId, Long userId){
        if(Objects.equals(op, "Add")){
            Optional<UserGroup> userGroup = userGroupRepository.findByUserGrId(userId, groupId);
            if(userGroup.isEmpty()){
                Long grInfoId = groupInfoRepository.getGroupInfoIDbyGroupID(groupId);
                Long userInfoId = userInfoRepository.getUserInfoIDbyUserID(userId);

                System.out.println(userId);
                System.out.println(groupId);
                System.out.println(userInfoId);
                System.out.println(grInfoId);

                UserGroup usGroup = new UserGroup(userInfoId,grInfoId,userId, groupId);
                userGroupRepository.save(usGroup);
                return String.valueOf(Status.OK);

            }else{
                return String.valueOf(Status.USER_EXIST_IN_GROUP);
            }
        } else{
            Optional<UserGroup> userGroup = userGroupRepository.findByUserGrId(userId, groupId);
            if(userGroup.isPresent()){
                userGroupRepository.delete(userGroup.get());
                return String.valueOf(Status.OK);
            } else{
                return String.valueOf(Status.ERROR);
            }
        }
    }

    public String saveGroup(AddGroupBody addGroup) {
        Group group = new Group();

        String grname = addGroup.getDisplayName();
        if(groupRepository.existsByGrName(grname).isPresent()){
            return String.valueOf(Status.GROUP_NAME_DUPLICATE);
        }

        group.setExternalId(UUID.randomUUID());
        group.setResourceType("group");
        group.setCreated(Instant.now());
        group.setDisplayName(addGroup.getDisplayName());
        group.setGroupAccessRightInfo(addGroup.getGroupAccessRightInfo());
        groupRepository.save(group);

        Long id = groupRepository.getIdByUUID(group.getExternalId());

        GroupInfo groupInfo = new GroupInfo();
        groupInfo.setGroupId(id);
        groupInfo.setValue(group.getExternalId());
        groupInfo.setRef("https://userProfile/"+ group.getExternalId());
        groupInfo.setDisplayName(group.getDisplayName());
        groupInfoRepository.save(groupInfo);

        return String.valueOf(Status.OK);
    }

    public String getGroupById(Long groupid) {
        Group group= groupRepository.getGroupById(groupid);
        if(group==null){
            return null;
        }
        return group.toJson();
    }
}
