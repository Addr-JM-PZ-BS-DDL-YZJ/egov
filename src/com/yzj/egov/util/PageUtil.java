package com.yzj.egov.util;

import jdk.nashorn.internal.codegen.ClassEmitter;

import java.util.List;

/**
 * 分页工具类
 */
public class PageUtil {

    //当前页
    private int pagenow;
    //上一页
    private int prev;
    //下一页
    private int next;
    //首页
    private int first;
    //尾页
    private int last;
    //总页数
    private int totalPage;
    //总条数
    private int totalCount;


    //每页查询个数
    private int pagesize;

    private List list;

    public PageUtil(){}

    public PageUtil(int pagenow,List list,int pagesize){
        this.pagenow = pagenow;
        this.list = list;
        this.pagesize = pagesize;
        //计算分页信息
        pageInfo();
    }
    /*
        计算分页信息
     */
    private void pageInfo(){
        //上一页
        prev = pagenow-1==first?first:pagenow-1;
        //下一页
        next = pagenow+1==last?last:pagenow+1;

    }

    public int getPagenow() {
        return pagenow;
    }

    public void setPagenow(int pagenow) {
        this.pagenow = pagenow;
    }

    public int getPrev() {
        return prev;
    }

    public void setPrev(int prev) {
        this.prev = prev;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {

        this.totalCount = totalCount;
        //总页数
        totalPage = totalCount%pagesize==0?totalCount/pagesize:totalCount/pagesize+1;
        //首页
        first = 1;
        //尾页
        last = totalPage;

    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public String getPageSql(String sql){
        int begin = (pagenow-1)*pagesize;
        int end = pagesize;
        return sql + " limit " +begin + "," +end;
    }
}
