package store.domain;

public class CartItem {

    private Product product;// 商品
    private int count;// 数量
    private Double subTotal;// 小计

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Double getSubTotal() {
        return product.getShop_price() * count;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public String toString() {
        return "CartItem [product=" + product + ", count=" + count + ", subTotal=" + subTotal + "]";
    }

}
