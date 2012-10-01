package br.app.sisau.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author Herick
 */
public class JobTeste implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        System.out.println("Job executado com sucesso às " + df.format(new Date()));
    }
}