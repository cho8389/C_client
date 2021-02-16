package carmen.domain;

public class Criteria {
	
	private int page;//?˜„?¬ pageë²ˆí˜¸
	private int perPageNum;//page ?‹¹ ë³´ì—¬ì£¼ëŠ” board ê°œìˆ˜
	
	public Criteria(){
		this.page=1;
		this.perPageNum=10;
	}
	
	public void setPage(int page){
		if(page<=0){
			this.page=1;
		}else{
			this.page=page;
		}
	}
	
	public void setPerPageNum(int perPageNum){
		if(perPageNum<=0||perPageNum>100){
			this.perPageNum=10;
		}else{
			this.perPageNum=perPageNum;
		}
	}
	
	public int getPage(){
		return this.page;
	}
	
	public int getPerPageNum(){
		return this.perPageNum;
	}
	
	public int getPageStart(){ // ?˜„?¬ ?˜?´ì§??— ì²? row ë²ˆí˜¸
		return (this.page-1)*perPageNum;
	}
	
	@Override
	public String toString(){
		return "Criteria [page="+page+",perPageNum="+perPageNum+
				"]";
	}
}

















