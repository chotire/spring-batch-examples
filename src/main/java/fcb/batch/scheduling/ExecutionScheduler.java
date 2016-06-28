package fcb.batch.scheduling;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;

public class ExecutionScheduler {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private JobLauncher jobLauncher;
	private Job job;
	
	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public void run() {
		Format dateFormart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.info("Executed: ", job.getName());

		try {
			JobParametersBuilder paramBuilder = new JobParametersBuilder();
			paramBuilder.addString("startDate", "2016-3-14");
			paramBuilder.addString("endDate", "2016-3-16");

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