package carmen.domain;

public class SearchCriteria extends Criteria{
	
	private String searchType;
	private String keyword;
	private String ttr_cat;
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getTtr_cat() {
		return ttr_cat;
	}
	public void setTtr_cat(String ttr_cat) {
		this.ttr_cat = ttr_cat;
	}
	public SearchCriteria(){}
	public SearchCriteria(String searchType, String keyword, String ttr_cat) {
		super();
		this.searchType = searchType;
		this.keyword = keyword;
		this.ttr_cat = ttr_cat;
	}
	@Override
	public String toString() {
		return "SearchCriteria [searchType=" + searchType + ", keyword="
				+ keyword + ", ttr_cat=" + ttr_cat + "]";
	};
}










