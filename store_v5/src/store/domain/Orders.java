package store.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Orders {
    private String oid; // 订单id
    private Date ordertime; // 订单生成时间
    private Double total; // 总计
    private Integer state; // 订单状态

    private String address; // 收货地址
    private String name; // 收货人姓名
    private String telephone; // 收货人电话号码

    private User user; // 订单表关联的user

    List<OrderItem> list = new ArrayList<OrderItem>(); // list集合,用于存放订单项

    @Override
    public String toString() {
        return "Orders [oid=" + oid + ", ordertime=" + ordertime + ", total=" + total + ", state=" + state
            + ", address=" + address + ", name=" + name + ", telephone=" + telephone + ", user=" + user + ", list="
            + list + "]";
    }

    public Orders() {
        super();
        // TODO 自动生成的构造函数存根
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getList() {
        return list;
    }

    public void setList(List<OrderItem> list) {
        this.list = list;
    }

}
