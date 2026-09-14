package in.codebuffdev.bootaop.controller;

import in.codebuffdev.bootaop.dto.Applicant;
import in.codebuffdev.bootaop.service.LoanService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/apply")
    public String apply(@RequestBody Applicant applicant) {
         return loanService.applyLoan(applicant);
    }
}
