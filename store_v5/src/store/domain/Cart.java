package store.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    // 总计
    private Double total = (double)0;

    // map集合存放购物项
    private Map<String, CartItem> map = new HashMap<String, CartItem>();

    // 将购物项添加到购物车
    public void addItemToCart(CartItem item) {
        String pid = item.getProduct().getPid();
        if (map.containsKey(pid)) {
            CartItem old = map.get(pid);
            item.setCount(old.getCount() + item.getCount());
        }
        map.put(pid, item);
    }

    // 从购物车中移除单个购物项
    public void delCartItem(String pid) {
        map.remove(pid);
    }

    // 清空购物车
    public void clear() {
        map.clear();
    }

    // 遍历map集合,获取到购物项
    public Collection<CartItem> getCartItem() {
        return map.values();
    }

    // 计算总计
    public Double getTotal() {
        total = (double)0;
        for (CartItem item : map.values()) {
            total = total + item.getSubTotal();
        }
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Map<String, CartItem> getMap() {
        return map;
    }

    public void setMap(Map<String, CartItem> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "Cart [total=" + total + ", map=" + map + "]";
    }

}
