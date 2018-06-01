package com.mdem.komunalka.controller.common;

import com.mdem.komunalka.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceNotFoundController {

    @RequestMapping(path = "/**", method = {RequestMethod.GET/*, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE*/})
    public void resourceNotFoundHandler() {
        throw new ResourceNotFoundException("Resource not found on the server!");
    }
}
