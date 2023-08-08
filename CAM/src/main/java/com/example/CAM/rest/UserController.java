package com.example.CAM.rest;

import com.example.CAM.domain.message.AddUser.AddUserBody;
import com.example.CAM.domain.response.UserList;
import com.example.CAM.domain.message.PatchOp.PatchOpUser;
import com.example.CAM.domain.message.UserInfoBody;
import com.example.CAM.domain.message.GetListBody;
import com.example.CAM.domain.response.error.ErrorResponse;
import com.example.CAM.JsonConverter.JsonUtils;
import com.example.CAM.repository.UserRepository;
import com.example.CAM.security.SCIMAuthorization;
import com.example.CAM.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import org.slf4j.Logger;

import static com.example.CAM.domain.response.error.HttpStatus.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/info")
    @SCIMAuthorization
    public ResponseEntity<?> getUserInfo(HttpServletRequest request,
                                         @RequestParam Map<String, String> requestPar,
                                         @RequestBody UserInfoBody userinfoBody) {

        try {
            String user = userService.getUserById(userinfoBody.getUserId());
            if (user == null) {
                return new ResponseEntity<>(new ErrorResponse("User not found", NOT_FOUND.getCode()), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(user, HttpStatus.OK);

        } catch (Exception e){
                return new ResponseEntity<>(new ErrorResponse(String.valueOf(e), INTERNAL_SERVER_ERROR.getCode()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/findbycriteria")
    @SCIMAuthorization
    public ResponseEntity<?> getUserList(HttpServletRequest request,
                                         @RequestParam Map<String, String> requestPar,
                                         @RequestBody GetListBody userlistBody) {
        try {
            UserList userList = userService.getUserList(userlistBody);

            if (userList == null) {
                return new ResponseEntity<>(new ErrorResponse("User list not found", NOT_FOUND.getCode()), HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new ErrorResponse(String.valueOf(e), INTERNAL_SERVER_ERROR.getCode()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/update")
    @SCIMAuthorization
    public ResponseEntity<?> setUserActivityStatus(HttpServletRequest request,
                                                   @RequestParam Map<String, String> requestPar,
                                                   @RequestBody String patchOp) throws JsonProcessingException {
        ObjectMapper objectMapper = JsonUtils.getObjectMapper();

        try {
            PatchOpUser patchOpt = objectMapper.readValue(patchOp, PatchOpUser.class);

            Long userId = patchOpt.getUserId();
            String activityStatus = patchOpt.getOperations().get(0).getValue();
            String u = userService.setUserActivityStatus(userId,activityStatus);

            if (u == null) {
                return new ResponseEntity<>(new ErrorResponse("User not found", NOT_FOUND.getCode()), HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(u, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new ErrorResponse(String.valueOf(e), INTERNAL_SERVER_ERROR.getCode()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PostMapping("/remove")
    @SCIMAuthorization
    public ResponseEntity<?> removeUser(HttpServletRequest request,
                                        @RequestParam Map<String, String> requestPar,
                                        @RequestBody UserInfoBody userinfoBody) {

        try{
            Long userId = userinfoBody.getUserId();
            if(!userRepository.existsById(userId)){
                return new ResponseEntity<>(new ErrorResponse("User not found", NOT_FOUND.getCode()), HttpStatus.NOT_FOUND);
            }
            userRepository.deleteById(userId);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e){
            log.info(String.valueOf(e));
            return new ResponseEntity<>(new ErrorResponse(String.valueOf(e), INTERNAL_SERVER_ERROR.getCode()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PostMapping("/add")
    @SCIMAuthorization
    public ResponseEntity<?> addUser(HttpServletRequest request,
                                     @RequestParam Map<String, String> requestPar,
                                     @RequestBody AddUserBody user){

        String status = userService.addUser(user);
        if(!status.equals("OK")){
            return new ResponseEntity<>(new ErrorResponse(status, INTERNAL_SERVER_ERROR.getCode()), HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
//    @PostMapping("/addAdmin")
//    @SCIMAuthorization
//    public ResponseEntity<?> addUserAdmin(HttpServletRequest request,
//                                     @RequestParam Map<String, String> requestPar){
//
//        String status = userService.addUserAdmin();
//        return new ResponseEntity<>(status, HttpStatus.OK);
//    }


}