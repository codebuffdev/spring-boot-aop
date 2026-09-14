# boot-aop

## Advice Annotations 
1. @Before("execution(in.codebuffdev.bootaop.dto.Student in.codebuffdev.bootaop.service.StudentService.createStudent(in.codebuffdev.bootaop.dto.Student))")
2.  @AfterReturning(
    pointcut = "execution(in.codebuffdev.bootaop.dto.Student in.codebuffdev.bootaop.service.StudentService.createStudent(in.codebuffdev.bootaop.dto.Student))",
    returning = "result" // tells Spring to put the target method's return value into the advice method's parameter named X.
    )
3. @AfterThrowing(value = "execution(in.codebuffdev.bootaop.dto.Student in.codebuffdev.bootaop.service.StudentService.createStudent(in.codebuffdev.bootaop.dto.Student))" , throwing = "exception")
   public void logAfterThrowing(Throwable exception){
   System.out.println("Target class throwing exception cause = " + exception.getMessage());
   }
4. @After("execution(* in.codebuffdev.bootaop.service.StudentService.createStudent(..))")
5.  @Around("execution(* in.codebuffdev.bootaop.service.StudentService.createStudent(..))")