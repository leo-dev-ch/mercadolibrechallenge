package ar.com.leogaray.email.rest;

import ar.com.leogaray.email.common.BusinessException;
import ar.com.leogaray.email.domain.email.EmailDTO;
import ar.com.leogaray.email.domain.email.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mail")
@Api(value = "/api/mail", description = "Manager mail")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @GetMapping(value = "/searchDevOps", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Search String DevOps email and save")
    public List<EmailDTO> searchAndSave() throws Exception {
        //try {
            String pattern = "DevOps";
            List<EmailDTO> list = this.emailService.searchAndSave(pattern);
            return list;

    }

}
