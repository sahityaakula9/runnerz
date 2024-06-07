package dev.danvega.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {
    private List<Run> runs = new ArrayList<>();
    private static final Logger log= LoggerFactory.getLogger(RunRepository.class);
    private final JdbcClient jdbcClient;
    public RunRepository(JdbcClient jdbcClient){

        this.jdbcClient = jdbcClient;
    }
    public List<Run> findAll(){
        return jdbcClient.sql("select * from run")
                .query(Run.class)
                .list();
    }
    public Optional<Run> findById(Integer id) {
        return jdbcClient.sql("SELECT * FROM Run WHERE id = :id")
                .param("id", id)
                .query(Run.class)
                .optional();
    }
    public void create (Run run){
        var update = jdbcClient.sql("INSERT INTO Run(id,title,started_on,completed_on,miles,location) values(?,?,?,?,?,?)")
                .params(List.of(run.id(), run.title(),run.startedOn(),run.completedOn(),run.miles(),run.location().toString() ))
                .update();
        Assert.state(update == 1,"failed to create run"+ run.title());  //like a print statement
    }
    public void update(Run run,Integer id){
        var update = jdbcClient.sql("update run set title= ?,started_on = ?,completed_on = ?,miles = ?,location = ? where id= ?" )
                .params(List.of(run.title(),run.startedOn(),run.completedOn(),run.miles(),run.location().toString(), id))
                .update();
        Assert.state(update ==1,"failed to update run"+ run.id());
    }
    public void delete(Integer id){
        var update = jdbcClient.sql("DELETE From Run WHERE id = :x")
                .param("x",id)
                .update();
        Assert.state(update == 1,"failed to delete" + id);
    }
    public int count(){
        return jdbcClient.sql("select * from run")
                .query()
                .listOfRows()
                .size();
    }
    public void saveAll(List<Run> runs){
        runs.stream().forEach(this::create);
    }

    public List<Run> findByLocation(String location ){
        return jdbcClient.sql("select * from run where location = :location")
                .param("location", location)
                .query(Run.class)
                .list();
    }

    /* List<Run> findAll(){
        return runs;
    }
    Optional<Run> findById(Integer id){
          return runs.stream()
                .filter(run -> run.id() == id)
                .findFirst();
    }
    void create(Run run){     //setter method for Create
        runs.add(run);

    }
    void update(Run run,Integer id){
        Optional<Run> existingRun = findById(id);
        if (existingRun.isPresent()){
            runs.set(runs.indexOf(existingRun.get()),run);
        }
    }
    void delete(Integer id){
        runs.removeIf(run -> run.id().equals(id));
    }
    @PostConstruct
private void init(){
        runs.add(new Run(1,
                "Monday",
                LocalDateTime.now(),
                LocalDateTime.now().plus(30, ChronoUnit.MINUTES),
                10,
                Location.OUTDOOR));


        runs.add(new Run(2,
                "Tuedsay",
                LocalDateTime.now(),
                LocalDateTime.now().plus(40,ChronoUnit.MINUTES),
                20,
                Location.INDOOR
        ));
}
*/

}
