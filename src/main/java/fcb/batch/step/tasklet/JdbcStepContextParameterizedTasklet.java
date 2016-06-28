package fcb.batch.step.tasklet;

import javax.sql.DataSource;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class JdbcStepContextParameterizedTasklet implements Tasklet {
	private NamedParameterJdbcOperations namedParameterJdbcOperations;
	
    private String sql;

	public void setDataSource(DataSource dataSource) {
		if (namedParameterJdbcOperations == null) {
			namedParameterJdbcOperations = new NamedParameterJdbcTemplate(dataSource);
		}
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		namedParameterJdbcOperations.update(sql, chunkContext.getStepContext().getJobParameters());
		return RepeatStatus.FINISHED;
	}
}