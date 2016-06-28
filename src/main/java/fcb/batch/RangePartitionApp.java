package fcb.batch;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RangePartitionApp {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args) {
		RangePartitionApp app = new RangePartitionApp();
		app.run();
	}
	
	private void run() {
		Format dateFormart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/batch/jobs/job-partition.xml")) {
			logger.info("Start: {}", dateFormart.format(new Date()));

			JobParametersBuilder paramBuilder = new JobParametersBuilder();
			paramBuilder.addString("startDate", "2016-3-14");
			paramBuilder.addString("endDate", "2016-3-16");

			JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
			Job job = (Job) context.getBean("partitionJob");
			JobExecution execution = jobLauncher.run(job, paramBuilder.toJobParameters());
			
			logger.info("Exit Status: {}", execution.getStatus());
			logger.info("Exit Status: {}", execution.getAllFailureExceptions());
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("End: {}", dateFormart.format(new Date()));
		logger.info("Done");
	}
}