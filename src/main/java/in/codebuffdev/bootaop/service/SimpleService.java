package in.codebuffdev.bootaop.service;

import in.codebuffdev.bootaop.annotations.TrackExecutionTime;
import org.springframework.stereotype.Service;

@Service
public class SimpleService {

    @TrackExecutionTime(
            warnAfter = 1500,
            operation = "returning dummy response to caller"
    )
    public String doSimpleTask(){
        // mimicking it is taking min 2sec
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Done";
    }
}
