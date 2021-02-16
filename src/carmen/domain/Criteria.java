package carmen.domain;

public class Criteria {
	
	private int page;//??¬ pageλ²νΈ
	private int perPageNum;//page ?Ή λ³΄μ¬μ£Όλ board κ°μ
	
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
	
	public int getPageStart(){ // ??¬ ??΄μ§?? μ²? row λ²νΈ
		return (this.page-1)*perPageNum;
	}
	
	@Override
	public String toString(){
		return "Criteria [page="+page+",perPageNum="+perPageNum+
				"]";
	}
}

















