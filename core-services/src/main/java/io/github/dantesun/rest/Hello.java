package io.github.dantesun.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dsun on 15/2/15.
 */
@RestController
class Hello {

    @RequestMapping("/")
    public String home() {
        return "Hello There!";
    }
}
