package com.eltov.air.core.util;

public class Pagination {
	
	private Integer pageno; // 현재페이지
	private Integer pageStart;
	private Integer pageEnd;
	private Integer totalCount;
	private Integer totalPage;
	private Integer pagesize; //page_size
	private String pageHtml;
	
	public int getTotalCount() {
		return totalCount;
	}

	//totalCount가 set 되면 setTotalPage 호출 - setTotalPage가 totalCount를 이용해 totalPage를 계산 후 set.
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		this.setTotalPage();
	}

	public int getTotalPage() {
		return totalPage;
	}
	
	// 마지막 파라미터인 totalPage까지 받으면 파라미터 계산을 완료하고 페이징 html을 만든다
	private void setTotalPage() {
		Integer totalCount = this.getTotalCount();
		Integer totalPage = (int) (Math.ceil(totalCount / (double) pagesize));
		this.totalPage = totalPage;
		
		this.setPageHtml(this.pageno, this.pagesize, this.totalPage, "");
	}

	
	public Pagination() {
		this.pageno = 1;
		this.pagesize = 10;
	}
	
	public Pagination(Integer page, Integer pagesize) {
		this.pageno = page;
		this.pagesize = pagesize;
	}
	
	public void setPageno(Integer pageno) {
		if(pageno == null || pageno<=0) {
			this.pageno = 1;
			return;
		}
		this.pageno = pageno;
	}
	
	public Integer getPageno() {
		return this.pageno;
	}
	
	public Integer getPageStart() {
		this.pageStart = (this.pageno -1) * pagesize +1;
		return this.pageStart;
	}
	
	public Integer getPageEnd() {
		this.pageEnd = this.pageStart + pagesize -1;
		return this.pageEnd;
	}
	
	public Integer getPagesize() {
		return this.pagesize;
	}
	
	public void setPagesize(Integer pagesize) {
		if(pagesize == null || pagesize < 10) {
			pagesize = 10;
		}
		this.pagesize = pagesize;
	}
	
	public String getPageHtml() {
		return this.pageHtml;
	}
	
	public void setPageHtml(Integer i_page, Integer pagesize,Integer page_cnt,String path){
        this.pageHtml = setPageHtml(i_page,pagesize,page_cnt,path,"");
    }

    public String setPageHtml(Integer i_page, Integer pagesize,Integer page_cnt,String path,String fnc_name){
        String ret_str="";
        String javascript_name = "setPageSubmit";

        if(fnc_name.equals("")) javascript_name = "setPageSubmit";
        else javascript_name = fnc_name;

        int block_page = ((i_page-1)/10) * (10) + 1;//현재 블럭

        if(!fnc_name.equals("")) javascript_name = fnc_name;

        if(i_page==1){
            ret_str = "<a href='#' class=\"paging_none\">처음</a> ";
        }else{
            ret_str = "<a href='javascript:"+javascript_name+"(1);' class=\"paging_none\">처음</a> ";
        }

        if(block_page==1){
            ret_str += "<a href='#' class=\"paging_none\">이전 10</a> ";
        }else{
            ret_str += "<a href='javascript:"+javascript_name+"("+(block_page-10)+");' class=\"paging_none\">이전 10</a> ";
        }

        int  i = 0;
        while(i<10 && block_page <= page_cnt){
            if(i == 0){
                if (block_page == i_page){
                    ret_str +="<a href=\"#\" class=\"paging_select\">" + block_page + "</a> ";
                }else {
                    ret_str +="<a href=\"javascript:"+javascript_name+"(" + block_page + ")\" class=\"paging_none\">" + block_page + "</a> ";
                }
            }else{
                if (block_page == i_page){
                    ret_str +="<a href=\"#\" class=\"paging_select\">" + block_page + "</a> ";
                }else {
                    ret_str +="<a href=\"javascript:"+javascript_name+"(" + block_page + ")\" class=\"paging_none\">" + block_page + "</a> ";
                }
            }
            block_page++;
            i++;
        }

        if(block_page > page_cnt){
            ret_str += "<a href='#' class=\"paging_none\">다음 10</a> ";
        }else{
            ret_str += "<a href='javascript:setPageSubmit("+block_page+");' class=\"paging_none\">다음 10</a> ";
        }

        if(i_page==page_cnt){
            ret_str += "<a href='#' class=\"paging_none\">마지막</a> ";
        }else{
            ret_str += "<a href='javascript:"+javascript_name+"(" + page_cnt + ");' class=\"paging_none\">마지막</a> ";
        }

        return ret_str;
    }
}
