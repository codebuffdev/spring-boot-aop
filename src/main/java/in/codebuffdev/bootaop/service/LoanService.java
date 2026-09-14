package in.codebuffdev.bootaop.service;

import in.codebuffdev.bootaop.dto.Applicant;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    public String applyLoan(Applicant applicant){
        if(applicant.cibil() < 635){
            throw new RuntimeException("NEXT Year: apply again");
        }
        return "Approved " + applicant.name();
    }
}
