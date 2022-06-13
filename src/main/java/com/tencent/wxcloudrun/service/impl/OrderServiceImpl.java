package com.tencent.wxcloudrun.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tencent.wxcloudrun.dto.OrderQueryDto;
import com.tencent.wxcloudrun.dto.OrderRequestDto;
import com.tencent.wxcloudrun.dto.OrderRequestSkuDto;
import com.tencent.wxcloudrun.entity.*;
import com.tencent.wxcloudrun.enums.OrderStatus;
import com.tencent.wxcloudrun.mapper.*;
import com.tencent.wxcloudrun.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.utils.LocalCache;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.tencent.wxcloudrun.enums.OrderStatus.COMPLETE;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author quinn
 * @since 2022-05-26
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private OrderSkuServiceImpl orderSkuService;

    @Autowired
    private ShopSkuMapper shopSkuMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private WxPayServiceImpl wxPayService;


    @Transactional
    public Object createOrder(OrderRequestDto orderRequestDto) {
        //添加订单
        Address address = addressMapper.selectById(orderRequestDto.getAddressId());
        Integer userId = LocalCache.getInt("userId");
        String outTradeNo = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss") + RandomUtil.randomInt(1, 100);

        Order order = new Order();
        order.setOrderNo(outTradeNo);
        order.setUserId(userId);
        order.setStatus(OrderStatus.UNPAY);
        order.setFreightPrice(new BigDecimal(orderRequestDto.getFreightPrice()));
        order.setPhone(address.getPhone());
        order.setConsignee(address.getConsignee());
        order.setMono(orderRequestDto.getMono());
        order.setOrderTime(DateTime.now());
        order.setPrice(new BigDecimal(orderRequestDto.getTotalPrice()));
        order.setPayPrice(new BigDecimal(orderRequestDto.getTotalPrice()).add(new BigDecimal(orderRequestDto.getFreightPrice())));

        this.baseMapper.insert(order);

        //消库存
        List<OrderRequestSkuDto> skuList = orderRequestDto.getSkuList();
        List<OrderSku> orderSkus = new ArrayList<>();
        List<Integer> skuIds = skuList.stream().map(OrderRequestSkuDto::getSkuId).collect(Collectors.toList());
        skuList.forEach(skuDto -> {
            ShopSku shopSku = shopSkuMapper.selectById(skuDto.getSkuId());
            shopSku.setStock(shopSku.getStock() - skuDto.getNum());
            shopSkuMapper.updateById(shopSku);

            //添加商品订单
            OrderSku orderSku = new OrderSku();
            orderSku.setOrderId(order.getId());
            orderSku.setOrderNo(outTradeNo);
            orderSku.setSkuId(skuDto.getSkuId());
            orderSku.setSkuTitle(shopSku.getTitle());
            orderSku.setNum(skuDto.getNum());
            orderSku.setPrice(new BigDecimal(skuDto.getPrice()));
            orderSku.setImg(shopSku.getImg());
            orderSku.setUnit(shopSku.getUnit());
            orderSku.setWeight(shopSku.getWeight());
            orderSkus.add(orderSku);
        });

        orderSkuService.saveBatch(orderSkus);

        if (orderRequestDto.getTakeWay().equals("cart")) {
            cartMapper.delete(new QueryWrapper<Cart>().lambda().eq(Cart::getUserId, userId).in(Cart::getSkuId, skuIds));
        }

        try {
            return wxPayService.jsApiPay(LocalCache.get("openId"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outTradeNo;

    }

    public Page<Order> getOrderPage(OrderQueryDto orderQueryDto) {
        return this.baseMapper.selectPage(orderQueryDto.getPagePlus(), new QueryWrapper<Order>().lambda().eq(Order::getOrderNo, orderQueryDto.getOutTradeNo()));
    }

    public List<Order> orderList(OrderQueryDto orderQueryDto) {
        int userId = LocalCache.getInt("userId");

        List<Order> orders = this.baseMapper.selectList(new QueryWrapper<Order>().lambda().eq(Order::getUserId, userId).eq(Order::getOrderNo, orderQueryDto.getOutTradeNo()));

        return orders;
    }

    public JSONObject getOrderDetail(String outTradeNo) {
        return wxPayService.queryOrder(outTradeNo);
    }

    public BigDecimal  countFreight(String code, BigDecimal actualPrice){
        ArrayList<String> codes = new ArrayList<>();


        if(actualPrice.compareTo(new BigDecimal("99")) == 1){
            return new BigDecimal("0.00");
        }

        if(codes.contains(code)){
            return new BigDecimal("8.00");
        }

        return new BigDecimal("15.00");

    }


    public void prepay() {

    }

    public void confirm(String orderNo) {
        this.update(new UpdateWrapper<Order>().lambda().eq(Order::getOrderNo, orderNo)
                .set(Order::getStatus, COMPLETE));
    }


    private List<Order> allOrderPage(OrderQueryDto orderQueryDto){
//        this.baseMapper.
        return null;

    }

}
