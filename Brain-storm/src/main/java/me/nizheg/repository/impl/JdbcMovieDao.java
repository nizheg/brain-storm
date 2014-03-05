package me.nizheg.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import me.nizheg.domain.Movie;
import me.nizheg.domain.Word;
import me.nizheg.repository.MovieDao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class JdbcMovieDao implements MovieDao {

	private JdbcTemplate template;
	private final MovieMapper movieMapper = new MovieMapper();

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
	public List<Movie> searchByMask(String mask) {
		return template.query("SELECT id, title FROM movie WHERE title like ?", new Object[] { mask }, movieMapper);
	}

	@Override
	public List<? extends Word> getAnagrams(String value, Integer length) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<? extends Word> getAccurateAnagrams(String value) {
		throw new UnsupportedOperationException();
	}

	private static class MovieMapper implements RowMapper<Movie> {
		@Override
		public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
			String value = rs.getString("title");
			Long id = rs.getLong("id");
			return new Movie(id, value);
		}
	}
}
