package me.nizheg.web.backing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class WordTypeFilter {

	private Map<String, WordType> wordTypes = Collections.emptyMap();
	private final Log logger = LogFactory.getLog(getClass());

	public void setWordTypes(Map<String, WordType> wordTypes) {
		if (wordTypes != null) {
			this.wordTypes = wordTypes;
		}
	}

	public Map<String, ?> process(Collection<String> enabledTypes) {
		if (enabledTypes == null) {
			enabledTypes = Collections.emptyList();
		}
		List<WordType> result = new ArrayList<WordType>(wordTypes.size());
		for (Entry<String, WordType> wordType : wordTypes.entrySet()) {
			try {
				WordType type = wordType.getValue().clone();
				if (enabledTypes.contains(wordType.getKey())) {
					type.setChecked(true);
				}
				result.add(type);
			} catch (CloneNotSupportedException e) {
				logger.error(WordType.class + " is not cloneable", e);
			}
		}
		Map<String, List<WordType>> model = new HashMap<String, List<WordType>>();
		model.put("wordTypes", result);
		return model;
	}
}
