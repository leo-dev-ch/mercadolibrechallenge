package ar.com.leogaray.email.rest;

import ar.com.leogaray.email.common.BusinessException;
import ar.com.leogaray.email.domain.email.EmailDTO;
import ar.com.leogaray.email.domain.email.EmailService;
import ar.com.leogaray.email.domain.user.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mail")
@Api(value = "/api/mail", description = "Manager mail")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @GetMapping(value = "/searchDevOps", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("It searches the email for the word \"DevOps\" and saves  in the database. Email and password default")
    public List<EmailDTO> searchAndSave() throws Exception {
        String pattern = "DevOps";
        List<EmailDTO> list = this.emailService.searchAndSave(pattern);
        return list;
    }

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("It searches the email for the word \"DevOps\" and saves  in the database. Input Email and password")
    public List<EmailDTO> searchAndSaveByUser(@RequestBody UserDTO user) throws Exception {
        String pattern = "DevOps";

        List<EmailDTO> list = this.emailService.searchAndSaveByUser(user,pattern);
        return list;
    }
}
