package fcb.batch;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StoredProcedureApp {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args) {
		StoredProcedureApp app = new StoredProcedureApp();
		app.run();
	}
	
	private void run() {
		Format dateFormart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/batch/jobs/job-stored-procedure.xml")) {
			logger.info("Start: {}", dateFormart.format(new Date()));

			JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
			Job job = (Job) context.getBean("storedProcedureJob");
			JobExecution execution = jobLauncher.run(job, new JobParameters());
			
			logger.info("Exit Status: {}", execution.getStatus());
			logger.info("Exit Status: {}", execution.getAllFailureExceptions());
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("End: {}", dateFormart.format(new Date()));
		logger.info("Done");
	}
}