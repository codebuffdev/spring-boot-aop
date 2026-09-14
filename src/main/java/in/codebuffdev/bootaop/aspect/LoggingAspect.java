package in.codebuffdev.bootaop.aspect;

import in.codebuffdev.bootaop.dto.Student;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LoggingAspect {

    @Before("execution(in.codebuffdev.bootaop.dto.Student in.codebuffdev.bootaop.service.StudentService.createStudent(in.codebuffdev.bootaop.dto.Student))")
    public void logBeforeMethod(JoinPoint joinPoint) {

        // The only way to stop the control goto the target class is by Throwing an exception
        // Here we can get the info about the method to be called & even change the args
        Object[] args = joinPoint.getArgs();
        System.out.println(Arrays.toString(args));
        Student std = (Student) args[0]; // we need a modifiable obj here record won't work
        std.setName("Modified name");
        System.out.println("Student is going to be saved is " + std);
    }

    @AfterReturning(
            pointcut = "execution(in.codebuffdev.bootaop.dto.Student in.codebuffdev.bootaop.service.StudentService.createStudent(in.codebuffdev.bootaop.dto.Student))",
            returning = "result" // tells Spring to put the target method's return value into the advice method's parameter named X.
    )
    public void logAfterReturning(Student result) {
        System.out.println("Target class returned result = " + result);
        // we can return a value but not recommended
        // result.setName("Again Modified name");
        // return result;
    }

    @AfterThrowing(value = "execution(in.codebuffdev.bootaop.dto.Student in.codebuffdev.bootaop.service.StudentService.createStudent(in.codebuffdev.bootaop.dto.Student))" , throwing = "exception")
    public void logAfterThrowing(Throwable exception){
        System.out.println("Target class throwing exception cause = " + exception.getMessage());
    }

    @After("execution(* in.codebuffdev.bootaop.service.StudentService.createStudent(..))")
    public void logAfter() {
        System.out.println("Always After execution");
    }

    @Around("execution(* in.codebuffdev.bootaop.service.StudentService.createStudent(..))")
    public Student logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // before
        // change method param
        Object[] args = proceedingJoinPoint.getArgs();
        Student s = (Student) args[0];
        s.setAge(1);

        // abort target class call
        Object returnValue = proceedingJoinPoint.proceed(args);

        // after
        // change return value
        Student sSaved = (Student) returnValue;
        sSaved.setName("Modified name Around");
        return sSaved;
    }
}
