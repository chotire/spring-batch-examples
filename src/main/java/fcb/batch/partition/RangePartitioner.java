package fcb.batch.partition;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

public class RangePartitioner implements Partitioner {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private int range = 10;
	
	public void setRange(int range) {
		this.range = range;
	}
	
	@Override
	public Map<String, ExecutionContext> partition(int gridSize) {
		Map<String, ExecutionContext> result = new HashMap<String, ExecutionContext>();
		int minValue = 1;
		int maxValue = range;

		for (int i = 1; i <= gridSize; i++) {
			logger.debug("\nStarting: Thread{}", i);
			logger.debug("minValue: {}", minValue);
			logger.debug("maxValue: {}", maxValue);

			ExecutionContext value = new ExecutionContext();
			value.putInt("minValue", minValue);
			value.putInt("maxValue", maxValue);
			value.putString("name", "Thread" + i);

			result.put("partition" + i, value);

			minValue = maxValue + 1;
			maxValue += range;
		}
		return result;
	}
}