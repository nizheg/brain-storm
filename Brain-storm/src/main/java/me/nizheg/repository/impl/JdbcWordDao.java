package me.nizheg.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import me.nizheg.domain.Word;
import me.nizheg.repository.WordDao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class JdbcWordDao implements WordDao {

	private JdbcTemplate template;
	private final WordMapper wordMapper = new WordMapper();

	public void setDataSource(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	@Override
	public Word getWord(String value) {
		return template.queryForObject("select id, nom_singl from word where nom_singl = ?", new Object[] { value },
				wordMapper);
	}

	@Override
	public Word getWord(Long id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void saveWord(Word word) {
		throw new UnsupportedOperationException();

	}

	@Override
	public List<Word> searchByMask(String mask) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<? extends Word> getAnagrams(String value) {
		return template
				.query(
						"SELECT * FROM (SELECT id, nom_singl FROM word where string_to_array(nom_singl, null, null) <@ string_to_array(?,null,null))"
								+ "as t WHERE is_array_include(string_to_array(?, null, null), string_to_array(nom_singl, null, null)) "
								+ "order by nom_singl", new Object[] { value, value }, wordMapper);

	}

	@Override
	public List<? extends Word> getAccurateAnagrams(String value) {
		return template.query("SELECT id, nom_singl FROM word "
				+ "where array_sort(string_to_array(nom_singl, null, null)) = array_sort(string_to_array(?,null,null)) "
				+ "order by nom_singl", new Object[] { value }, wordMapper);
	}

	private static class WordMapper implements RowMapper<Word> {

		@Override
		public Word mapRow(ResultSet rs, int rowNum) throws SQLException {
			Long id = rs.getLong("id");
			String value = rs.getString("nom_singl");
			return new Word(id, value);
		}
	}
}
