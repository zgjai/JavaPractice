package practice.GuavaTest;

import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.hash.Funnel;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

/**
 * Created by zhangguijiang on 2018/2/22.
 */
public class HashingTest {
    final static HashFunction hf = Hashing.murmur3_32();
    final static Funnel<Product> productFunnel = (Funnel<Product>) (product, into) -> {
        into.putString(product.title, Charsets.UTF_8);
        product.imgList.forEach(img -> into.putString(img, Charsets.UTF_8));
    };

    public static void main(String[] args) {
        Product product = new Product("佛山富湾湖酒店270元", Lists.newArrayList(
                "https://img1.qunarzz.com/order/comp/1711/6c/315ffe931a5e9202.jp",
                "https://img1.qunarzz.com/order/comp/1711/6c/315ffe9311drfer2.jpg",
                "https://img1.qunarzz.com/order/comp/1711/6c/315ffe9grtgtre2.jpg"));
        System.out.println(Integer.toUnsignedLong(hf.newHasher().putObject(product, productFunnel).hash().hashCode()));
    }

    static class Product {
        String title;
        List<String> imgList;

        Product(String title, List<String> imgList) {
            this.title = title;
            this.imgList = imgList;
        }
    }
}
