package org.cantaloupe;

import java.util.LinkedList;
import java.util.List;

import org.cantaloupe.json.SortOrder;

public class SortDef {

	private String relyOn;

	public String getRelyOn() {
		return relyOn;
	}

	public List<SortIndicator> getSortList() {
		return sortList;
	}

	private List<SortIndicator> sortList = new LinkedList<SortIndicator>();

	public static SortDef sort() {
		return new SortDef();
	}

	public SortDef by(String path, SortOrder desc) {
		sortList.add(new SortIndicator(path, desc));
		return this;
	}

	public SortDef relyOn(String relyOn) {
		this.relyOn = relyOn;
		return this;
	}
}

class SortIndicator {
	String path;
	SortOrder order;

	public String getPath() {
		return path;
	}

	public SortOrder getOrder() {
		return order;
	}

	public SortIndicator(String path, SortOrder desc) {
		super();
		this.path = path;
		this.order = desc;
	}

}
