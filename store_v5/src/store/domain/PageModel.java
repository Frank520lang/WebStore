package store.domain;

import java.util.List;

public class PageModel {
    private int currentPageNum; // 当前页
    private int pageSize = 5; // 每页显示的条数
    private int totalRecords; // 总记录条数

    private int totalPageNum; // 总页数,通过计算出来的
    private int startindex; // 每页开始的索引
    private int prePageNum; // 上一页
    private int nextPageNum; // 下一页

    private int startPage; // 开始页码
    private int endPage; // 结束页码

    private List list; // 记录商品信息
    private String url; // 记录商品信息

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PageModel() {
        super();
        // TODO 自动生成的构造函数存根
    }

    public int getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(int currentPageNum) {
        this.currentPageNum = currentPageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(int totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public int getStartindex() {
        return startindex;
    }

    public void setStartindex(int startindex) {
        this.startindex = startindex;
    }

    public int getPrePageNum() {
        prePageNum = currentPageNum - 1;
        if (prePageNum < 1) {
            prePageNum = currentPageNum;
        }
        return prePageNum;
    }

    public void setPrePageNum(int prePageNum) {
        this.prePageNum = prePageNum;
    }

    public int getNextPageNum() {
        nextPageNum = currentPageNum + 1;
        if (nextPageNum > totalPageNum) {
            nextPageNum = totalPageNum;
        }
        return nextPageNum;
    }

    public void setNextPageNum(int nextPageNum) {
        this.nextPageNum = nextPageNum;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    // 计算开始索引,总页数,开始页码,结束页码
    public PageModel(int currentPageNum, int pageSize, int totalRecords) {
        super();
        this.currentPageNum = currentPageNum;
        this.pageSize = pageSize;
        this.totalRecords = totalRecords;

        // 计算开始索引的的索引值
        startindex = (currentPageNum - 1) * pageSize;

        // 计算总页数
        totalPageNum = totalRecords % pageSize == 0 ? (totalRecords / pageSize) : (totalRecords / pageSize + 1);

        // 开始页码
        startPage = currentPageNum - 4; // 5
        endPage = currentPageNum + 4; // 13
        // 看看总页数够不够9页
        if (totalPageNum > 9) {
            // 超过了9页
            if (startPage < 1) {
                startPage = 1;
                endPage = startPage + 8;
            }
            if (endPage > totalPageNum) {
                endPage = totalPageNum;
                startPage = endPage - 8;
            }
        } else {
            // 不够9页
            startPage = 1;
            endPage = totalPageNum;
        }

    }

    @Override
    public String toString() {
        return "PageModel [currentPageNum=" + currentPageNum + ", pageSize=" + pageSize + ", totalRecords="
            + totalRecords + ", totalPageNum=" + totalPageNum + ", startindex=" + startindex + ", prePageNum="
            + prePageNum + ", nextPageNum=" + nextPageNum + ", startPage=" + startPage + ", endPage=" + endPage
            + ", list=" + list + "]";
    }

}
