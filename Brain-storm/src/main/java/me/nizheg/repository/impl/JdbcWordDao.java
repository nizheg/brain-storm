package me.nizheg.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		return template.queryForObject("SELECT id, nom_singl FROM word WHERE nom_singl = ?", new Object[] { value },
				wordMapper);
	}

	@Override
	public Word getWord(Long id) {
		return template.queryForObject("SELECT id, nom_singl FROM word WHERE id = ?", new Object[] { id }, wordMapper);
	}

	@Override
	public Word saveWord(Word word) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Word> searchByMask(String mask) {
		return template.query("SELECT id, nom_singl FROM word WHERE nom_singl LIKE ?", new Object[] { mask }, wordMapper);
	}

	@Override
	public List<? extends Word> getAnagrams(String value, Integer length) {
		if (length != null && length == value.length()) {
			return getAccurateAnagrams(value);
		}
		List<Object> args = new ArrayList<Object>();
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT * FROM (");
		sqlBuilder.append("\n\tSELECT id, nom_singl ");
		sqlBuilder.append("\n\tFROM word");
		sqlBuilder.append("\n\tWHERE true");
		if (length != null) {
			sqlBuilder.append("\n\t\tAND length(nom_singl) = ?");
			args.add(length);
		}
		if (length == null || length.intValue() < value.length()) {
			sqlBuilder.append("\n\t\tAND string_to_array(nom_singl, null, null) <@ string_to_array(?, null, null)");
			args.add(value);
		} else {
			sqlBuilder.append("\n\t\tAND string_to_array(nom_singl, null, null) @> string_to_array(?, null, null)");
			args.add(value);
		}
		sqlBuilder.append(") as t");
		if (length == null || length.intValue() < value.length()) {
			sqlBuilder
					.append("\nWHERE is_array_include(string_to_array(?, null, null), string_to_array(nom_singl, null, null))");
			args.add(value);
		} else {
			sqlBuilder
					.append("\nWHERE is_array_include(string_to_array(nom_singl, null, null), string_to_array(?, null, null))");
			args.add(value);
		}
		sqlBuilder.append("ORDER BY nom_singl");
		return template.query(sqlBuilder.toString(), args.toArray(), wordMapper);

	}

	@Override
	public List<? extends Word> getAccurateAnagrams(String value) {
		return template.query("SELECT id, nom_singl FROM word "
				+ "where array_sort(string_to_array(nom_singl, null, null)) = array_sort(string_to_array(?, null, null)) "
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
