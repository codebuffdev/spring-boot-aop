package in.codebuffdev.bootaop.controller;

import in.codebuffdev.bootaop.service.SimpleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simple")
public class SimpleController {

    private final SimpleService simpleService;

    public SimpleController(SimpleService simpleService) {
        this.simpleService = simpleService;
    }

    @GetMapping
    public String doSimpleTask(){
        return simpleService.doSimpleTask();
    }
}
