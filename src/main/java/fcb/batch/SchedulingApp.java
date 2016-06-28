package fcb.batch;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SchedulingApp {
	public static void main(String[] args) {
		@SuppressWarnings({"unused", "resource"})
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/batch/jobs/job-scheduling.xml");
	}
}