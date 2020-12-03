package store.domain;

public class Category {
    private String cid;
    private String cname;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Category() {
        super();
        // TODO 自动生成的构造函数存根
    }

    public Category(String cid, String cname) {
        super();
        this.cid = cid;
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "Category [cid=" + cid + ", cname=" + cname + "]";
    }

}
