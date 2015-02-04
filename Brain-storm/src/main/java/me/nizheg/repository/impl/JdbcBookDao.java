package me.nizheg.repository.impl;

import me.nizheg.domain.Book;
import me.nizheg.domain.Word;
import me.nizheg.repository.MovieDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcBookDao implements MovieDao {

	private JdbcTemplate template;
	private final BookMapper bookMapper = new BookMapper();

	public void setDataSource(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	@Override
	public Word getWord(String value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Word getWord(Long id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Word saveWord(Word word) {
		throw new UnsupportedOperationException();

	}

	@Override
	public List<Book> searchByMask(String mask) {
		return template.query("SELECT id, title FROM book WHERE title LIKE ?", new Object[] { mask }, bookMapper);
	}

	@Override
	public List<? extends Word> getAnagrams(String value, Integer length) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<? extends Word> getAccurateAnagrams(String value) {
		throw new UnsupportedOperationException();
	}

	// FIXME: implement arrays as type and filter
	@Override
	public List<? extends Word> getDismemberment(List<String[]> parts, int length) {

		StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT id, title FROM book WHERE regexp_replace(title, '[^а-я]', '', 'g') IN (");
        sqlBuilder.append("\nSELECT regexp_replace(title, '[^а-я]', '', 'g')");
        sqlBuilder.append("\nFROM book");
        sqlBuilder.append("\nWHERE length(regexp_replace(title, '[^а-я]', '', 'g')) = ?");
        sqlBuilder.append("\nINTERSECT");
        sqlBuilder.append("\nSELECT ");
        for (int i = 0; i < parts.size(); ++i) {
            if (i > 0) {
                sqlBuilder.append(" || ");
            }
            sqlBuilder.append("p" + i);
        }
        sqlBuilder.append("\nFROM");
		for (int i = 0; i < parts.size(); ++i) {
            if (i > 0) {
                sqlBuilder.append(",");
            }
			sqlBuilder.append("\nunnest(ARRAY[");
			StringBuilder piBuilder = new StringBuilder();
			String[] subParts = parts.get(i);
			for (int j = 0; j < subParts.length; ++j) {
				if (piBuilder.length() > 0) {
					piBuilder.append(",");
				}
				piBuilder.append("'" + subParts[j] + "'");
			}
			sqlBuilder.append(piBuilder.toString());
			sqlBuilder.append("]) as p" + i);
		}
        sqlBuilder.append(")");
		return template.query(sqlBuilder.toString(), new Object[]{length}, bookMapper);
	}

	private static class BookMapper implements RowMapper<Book> {
		@Override
		public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
			String value = rs.getString("title");
			Long id = rs.getLong("id");
			return new Book(id, value);
		}
	}
}
