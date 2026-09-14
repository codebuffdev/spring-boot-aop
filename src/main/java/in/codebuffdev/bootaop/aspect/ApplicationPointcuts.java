package in.codebuffdev.bootaop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * In the industry to keep things clean & reusable we use maintain all the named pointcuts in a separate class
 *
 */
@Aspect
public class ApplicationPointcuts {

    // named pointcut
    @Pointcut("execution(* in.codebuffdev.bootaop.service.LoanService.*(..))")
    public void loanAuditOperation() {
        //empty body
    }

    // pc for method names starts with get
    @Pointcut("execution(* *.get* (..))")
    public void adviceOnGet() {
        //empty body
    }

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void logBeforeMethod(){}

    @Pointcut("@target(org.springframework.stereotype.Service)")
    public void logBeforeAllMethod(){}

    @Pointcut("args(in.codebuffdev.bootaop.dto.Student) && within(in.codebuffdev.bootaop.service.StudentService)" )
    public void takeStudentArgAsParam(){}

    @Pointcut("target(in.codebuffdev.bootaop.service.StudentService)")
    public void targetClassType(){
    }

    @Pointcut("this(in.codebuffdev.bootaop.service.StudentService)")
    public void proxyClassType(){
    }
}
