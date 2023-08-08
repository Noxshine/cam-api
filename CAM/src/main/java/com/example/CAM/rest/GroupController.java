package com.example.CAM.rest;

import com.example.CAM.domain.message.AddGroupBody;
import com.example.CAM.domain.message.GetListBody;
import com.example.CAM.domain.message.GroupBody;
import com.example.CAM.domain.message.PatchOp.PatchOpGroup;
import com.example.CAM.JsonConverter.JsonUtils;
import com.example.CAM.domain.response.GroupList;
import com.example.CAM.domain.response.error.ErrorResponse;
import com.example.CAM.repository.GroupRepository;
import com.example.CAM.domain.core.Group.Group;
import com.example.CAM.repository.UserRepository;
import com.example.CAM.security.SCIMAuthorization;
import com.example.CAM.service.GroupService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.example.CAM.domain.response.error.HttpStatus.INTERNAL_SERVER_ERROR;
import static com.example.CAM.domain.response.error.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/group")
public class GroupController {
    private final GroupService groupService;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public GroupController(GroupService groupService, GroupRepository groupRepository, UserRepository userRepository) {
        this.groupService = groupService;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }
    @PostMapping("/info")
    @SCIMAuthorization
    public ResponseEntity<?> getGroups (HttpServletRequest request,
                                        @RequestParam Map<String, String> requestPar,
                                        @RequestBody GroupBody groupBody) {
        try{
            Long groupid = groupBody.getGroupId();
            String group = groupService.getGroupById(groupid);

            if(group == null){
                return new ResponseEntity<>(new ErrorResponse("Group not found", NOT_FOUND.getCode()), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(group, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new ErrorResponse(String.valueOf(e), INTERNAL_SERVER_ERROR.getCode()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/findbycriteria")
    @SCIMAuthorization
    public ResponseEntity<?> getListGroup (HttpServletRequest request,
                                           @RequestParam Map<String, String> requestPar,
                                           @RequestBody GetListBody getListBody) {
//        try{
            GroupList grlist = groupService.getlistgroup(getListBody);
            if(grlist == null){
                return new ResponseEntity<>(new ErrorResponse("Group list not found", NOT_FOUND.getCode()), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(grlist, HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity<>(new ErrorResponse(String.valueOf(e), INTERNAL_SERVER_ERROR.getCode()), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }

    // add/remove user from group
    @PostMapping("/update")
    @SCIMAuthorization
    public ResponseEntity<?> updateGroup (HttpServletRequest request,
                                          @RequestParam Map<String, String> requestPar,
                                          @RequestBody String patchOpGroup) throws JsonProcessingException {
        ObjectMapper objectMapper = JsonUtils.getObjectMapper();

        try{
            PatchOpGroup patchOpt = objectMapper.readValue(patchOpGroup, PatchOpGroup.class);

            String opt = patchOpt.getOperations().get(0).getOp();
            Long groupId = patchOpt.getGroupId();
            Long userId = Long.valueOf(patchOpt.getOperations().get(0).getValue());

            if(!groupRepository.existsById(groupId)||!userRepository.existsById(userId)){
                return new ResponseEntity<>(new ErrorResponse("User or Group not found", NOT_FOUND.getCode()), HttpStatus.NOT_FOUND);
            }
            String status = groupService.updateGroup(opt,groupId, userId);
            if(!status.equals("OK")){
                return new ResponseEntity<>(new ErrorResponse(status, INTERNAL_SERVER_ERROR.getCode()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new ErrorResponse(String.valueOf(e), INTERNAL_SERVER_ERROR.getCode()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/remove")
    @SCIMAuthorization
    public ResponseEntity<?> removeGroup (HttpServletRequest request,
                                          @RequestParam Map<String, String> requestPar,
                                          @RequestBody GroupBody groupBody) {
        try{
            Long groupId = groupBody.getGroupId();
            if(!groupRepository.existsById(groupId)){
                return new ResponseEntity<>(new ErrorResponse("Group not found", NOT_FOUND.getCode()), HttpStatus.NOT_FOUND);
            }
            groupRepository.deleteById(groupId);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new ErrorResponse(String.valueOf(e), INTERNAL_SERVER_ERROR.getCode()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/add")
    @SCIMAuthorization
    public ResponseEntity<?> addGroup (HttpServletRequest request,
                                          @RequestParam Map<String, String> requestPar,
                                          @RequestBody AddGroupBody addGroup) {
        String status = groupService.saveGroup(addGroup);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
