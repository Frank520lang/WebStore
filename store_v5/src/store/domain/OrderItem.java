package store.domain;

public class OrderItem {
    // 属性尽量用包装类
    private String itemid; // 订单项id
    private Integer quanyity; // 数量
    private Double total; // 小计
    private Product product; // product对象,用于获取product对象的属性
    private Orders orders; // order对象,订单项关联的订单表

    @Override
    public String toString() {
        return "OrderItem [itemid=" + itemid + ", quanyity=" + quanyity + ", total=" + total + ", product=" + product
            + ", orders=" + orders + "]";
    }

    public OrderItem() {
        super();
        // TODO 自动生成的构造函数存根
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public Integer getQuanyity() {
        return quanyity;
    }

    public void setQuanyity(Integer quanyity) {
        this.quanyity = quanyity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
}
