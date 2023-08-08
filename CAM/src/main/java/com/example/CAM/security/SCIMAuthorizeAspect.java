package com.example.CAM.security;

import com.example.CAM.domain.response.error.ErrorResponse;
import com.example.CAM.rest.UserController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.example.CAM.domain.response.error.HttpStatus.UNAUTHORIZED;

@Aspect
@Component
public class SCIMAuthorizeAspect {
    private final Logger log = LoggerFactory.getLogger(UserController.class);
    private static final String secretKey = "admin1234567890";

    private final static Long EXPIRE_GRACE_PERIOD = 100L;
    @Around("@annotation(SCIMAuthorization)")
    public Object validateActionRequest(final ProceedingJoinPoint joinPoint) throws Throwable {
        Object joinPointResult;
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder
                .getRequestAttributes())).getRequest();
        String authToken = request.getHeader("Authorization");
        Map<String, String> reqParams =  convertToStringMap(request.getParameterMap());

        ErrorResponse e = checkNonce(reqParams);
        if (e == null) { e = checkTimeStamp(reqParams); }
        if (e == null) { e = checkAccountAndAuthorizationHeader(request, authToken,reqParams); }
        if (e != null) {
            return new ResponseEntity<>(new ErrorResponse(e.getDetail(), e.getStatus()), HttpStatus.UNAUTHORIZED);
        }
        else
        {
            joinPointResult = joinPoint.proceed();
            return joinPointResult;
        }
    }
    private ErrorResponse checkAccountAndAuthorizationHeader(HttpServletRequest request, String authToken, Map<String,
            String> requestPar) {
//        String acctIDq = requestPar.get("accountid");
//        if (!accessID.equals(acctIDq)) {
//            return new ErrorResponse("Invalid account",UNAUTHORIZED.getCode());
//        }
        String httpMethod = request.getMethod().toUpperCase();
        String protocol = request.getScheme().toLowerCase();
        String hostName = request.getServerName().toLowerCase();
        String hostPort = request.getServerPort() + "";
        hostName = hostPort == "4444" ? hostName : String.format("%s:%s", hostName, hostPort);
        String requestURI = request.getRequestURI().toLowerCase();
        String parameters = constructAndSortParam(requestPar);
//        String combinedString = String.format("%s&%s://%s%s?%s", httpMethod, protocol,hostName,requestURI,parameters);
        String combinedString = String.format("%s&%s://%s%s?%s", httpMethod, protocol,hostName,requestURI,parameters);

        System.out.println(combinedString);

        try {
            SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes(), "HMACSHA256");
            Mac mac = Mac.getInstance("HMACSHA256");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(combinedString.getBytes());

            String authString = Base64.getEncoder().encodeToString(rawHmac);

            if (!authToken.equals(authString)) {
                return new ErrorResponse("Invalid authorization header",UNAUTHORIZED.getCode());
            }
            return null;
        } catch (Exception ex) {
            //TODO Log exception here
            return new ErrorResponse("Invalid authorization header",UNAUTHORIZED.getCode());
        }
    }

    private ErrorResponse checkNonce(Map<String, String> reqParams) {
        String nonce = reqParams.getOrDefault("nonce", "");
//        if (nonceValidator.checkNonce(nonce)) {
        if(checkNonce(nonce)){
            return null;
        }
        return new ErrorResponse("Invalid nonce",UNAUTHORIZED.getCode());
    }
    private ErrorResponse checkTimeStamp(Map<String, String> reqParams) {
        try {
            String reqdtq = reqParams.getOrDefault("ts", "");
            if (!reqdtq.equals("") ) {
                long reqdtl = Long.parseLong(reqdtq);
                long currentMillisecond = System.currentTimeMillis();

                if (currentMillisecond - reqdtl <= (EXPIRE_GRACE_PERIOD * 100)) {
                    return null;
                }
            }
        } catch (Exception e) {
            // TODO: Log exception here
            return new ErrorResponse("1Invalid time stamp",UNAUTHORIZED.getCode());
        }
        return new ErrorResponse("Invalid time stamp2",UNAUTHORIZED.getCode());
    }
    public static Map<String, String> convertToStringMap(Map<String, String[]> originalMap) {
        Map<String, String> convertedMap = new HashMap<>();

        for (Map.Entry<String, String[]> entry : originalMap.entrySet()) {
            String key = entry.getKey();
            String[] values = entry.getValue();

            if (values != null && values.length > 0) {
                // For simplicity, we take the first element of the String[] as the value
                convertedMap.put(key, values[0]);
            }
        }
        return convertedMap;
    }
    private Boolean checkNonce(String nonce){
        try {
            Long n = Long.parseLong(nonce);
            return true;
        } catch (NumberFormatException e){
            log.error(e.toString());
            return false;
        }

    }
    private String constructAndSortParam(Map<String, String> reqpar){
        String value = "";
        value += "nonce=" + reqpar.get("nonce") + "&";
        value += "ts=" + reqpar.get("ts");
        return value;
    }
}