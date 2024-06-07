package dev.danvega.runnerz.run;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
@Component
public class RunJasonDataLoader implements CommandLineRunner {
    public static final Logger log = LoggerFactory.getLogger(RunJasonDataLoader.class);
    private final RunRepository runRepository;
    private final ObjectMapper objectMapper;


    public RunJasonDataLoader(RunRepository runRepository , ObjectMapper objectMapper){
        this.runRepository = runRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if(runRepository.count() == 0){
            try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/runs.json")){
                Runs allruns =  objectMapper.readValue(inputStream, Runs.class);
                log.info("reading {} runs from json data and saving to database collection", allruns.runs().size());
                runRepository.saveAll(allruns.runs());

            }  catch (IOException e){
                throw new RuntimeException("failed to read data", e);
            }
        }
        else {
            log.info("Not loading runs from json data because collection contains data ");

        }
    }
}
