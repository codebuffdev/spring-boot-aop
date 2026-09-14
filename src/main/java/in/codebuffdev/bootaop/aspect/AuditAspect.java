package in.codebuffdev.bootaop.aspect;

import in.codebuffdev.bootaop.dto.Applicant;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class AuditAspect {


    // 1. Security / validation
    @Before("within(in.codebuffdev.bootaop.service.LoanService)")
    public void securityCheck(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();

        System.out.println(Arrays.toString(args));
        Applicant applicant = (Applicant) args[0];

        System.out.println(
                "Security check for method: " +
                        joinPoint.getSignature().getName()
        );

        if (applicant.cibil() <= 630) {
            throw new RuntimeException("Not eligible");
        }
    }

    // 2. Successful operation
    @AfterReturning(
            pointcut = "in.codebuffdev.bootaop.aspect.ApplicationPointcuts.loanAuditOperation()",
            returning = "result"
    )
    public void auditSuccess(
            JoinPoint joinPoint,
            Object result) {

        System.out.println(
                "SUCCESS: " +
                        joinPoint.getSignature().getName()
        );

        System.out.println("Result: " + result);
    }

    // 3. Failed operation
    // combining designator
    @AfterThrowing(
            pointcut = "in.codebuffdev.bootaop.aspect.ApplicationPointcuts.loanAuditOperation() && bean(studentService)",
            throwing = "ex"
    )
    public void auditFailure(
            JoinPoint joinPoint,
            Throwable ex) {

        System.out.println(
                "FAILURE: " +
                        joinPoint.getSignature().getName()
        );

        System.out.println(
                "Exception: " + ex.getMessage()
        );
    }


    // 4. Always executed
    @After("in.codebuffdev.bootaop.aspect.ApplicationPointcuts.loanAuditOperation()")
    public void auditCompleted(JoinPoint joinPoint) {

        System.out.println(
                "Operation completed: " +
                        joinPoint.getSignature().getName()
        );
    }


    // 5. Complete control + performance
    @Around("in.codebuffdev.bootaop.aspect.ApplicationPointcuts.loanAuditOperation())")
    public Object performanceAudit(
            ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        System.out.println(
                "START: " +
                        joinPoint.getSignature().getName()
        );

        try {

            return joinPoint.proceed();

        } finally {

            long time =
                    System.currentTimeMillis() - start;

            System.out.println(
                    "Execution time: " + time + " ms"
            );
        }
    }
}