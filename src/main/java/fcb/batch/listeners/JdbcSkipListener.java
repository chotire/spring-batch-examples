package fcb.batch.listeners;

import javax.sql.DataSource;

import org.springframework.batch.core.SkipListener;
import org.springframework.batch.item.database.ItemSqlParameterSourceProvider;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class JdbcSkipListener<T, S> implements SkipListener<T, S> {
	private NamedParameterJdbcOperations namedParameterJdbcOperations;
	private ItemSqlParameterSourceProvider<S> itemSqlParameterSourceProvider;
	
    private String sql;

	public void setDataSource(DataSource dataSource) {
		if (namedParameterJdbcOperations == null) {
			namedParameterJdbcOperations = new NamedParameterJdbcTemplate(dataSource);
		}
	}

	public void setItemSqlParameterSourceProvider(ItemSqlParameterSourceProvider<S> itemSqlParameterSourceProvider) {
		this.itemSqlParameterSourceProvider = itemSqlParameterSourceProvider;
	}
	
	public String getSql() {
		return sql;
	}
	
	public void setSql(String sql) {
		this.sql = sql;
	}
	
	@Override
	public void onSkipInRead(Throwable t) {
		// do nothing
	}

	@Override
	public void onSkipInWrite(S item, Throwable t) {
		namedParameterJdbcOperations.update(sql, itemSqlParameterSourceProvider.createSqlParameterSource(item));
	}

	@Override
	public void onSkipInProcess(T item, Throwable t) {
		// do nothing
	}
}