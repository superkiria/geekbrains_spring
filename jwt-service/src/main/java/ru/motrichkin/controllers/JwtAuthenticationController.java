package ru.motrichkin.controllers;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.motrichkin.exceptions.UserNotFoundException;
import ru.motrichkin.interfaces.services.IAuthenticationService;
import ru.motrichkin.util.JwtRequest;
import ru.motrichkin.util.JwtResponse;

@RestController
//@CrossOrigin
public class JwtAuthenticationController {

    private static Logger log = Logger.getLogger("authentication-service");
    private final IAuthenticationService authenticationService;

    public JwtAuthenticationController(IAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @CrossOrigin
    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
        String userName = authenticationRequest.getUsername();
        if (StringUtils.isEmpty(userName)) {
            log.info("username is required");
            return ResponseEntity.badRequest().body("username is required");
        }
        else {
            log.info(String.format("User %s is trying to authenticate", userName));
        }

        try {
            String token = authenticationService.getAuthToken(userName);
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (UserNotFoundException e) {
            log.info(String.format("User %s not found", userName));
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(String.format("User '%s' not found", userName));
        } catch (Exception e) {
            log.info(String.format("Exception while authenticating user %s:\n%s", userName, ExceptionUtils.getStackTrace(e)));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Exception while authenticating user '%s':\n%s", userName, ExceptionUtils.getStackTrace(e)));
        }
    }
}
