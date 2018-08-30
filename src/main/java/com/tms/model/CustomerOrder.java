package com.tms.model;

import com.tms.common.Constant;
import com.tms.controller.vo.request.PostOrderDto;
import com.tms.controller.vo.response.LocationResponseVo;
import com.tms.util.IDGen;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by szj on 2017/12/5.
 */
@Entity
@Data
public class CustomerOrder extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn
    private Payment payment;
    @Embedded
    private PostOrderDto.PaymentDto subPayment;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerOrder", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Cargo> cargoes;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Location from;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Location to;
    @OneToMany(mappedBy = "customerOrder", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<DeliverOrder> deliverOrders;
    private Constant.DeliverType deliverType;
    private String customerOrderNo;
    private Double distance;
    private Constant.OrderState state;
    private Constant.OrderSource source;//订单来源
    private String customerRemark;//用户备注
    private String innerRemark;//内部备注

    public CustomerOrder() {
    }

    public static List<CustomerOrder> buildOrders(PostOrderDto postOrderDto, Payment payment) {
        List<CustomerOrder> domains = new ArrayList<>();
        for (PostOrderDto.OrderDto orderDto : postOrderDto.getOrders()) {
            CustomerOrder domain = new CustomerOrder();
            if (postOrderDto.getPayment().getPayType().equals(Constant.PayType.SENDER_PAY)) {
                domain.state = Constant.OrderState.NOT_PAID;
            } else {
                domain.state = Constant.OrderState.NOT_DISTRIBUTED;
            }
            domain.from = new Location(postOrderDto.getFrom());
            domain.to = new Location(orderDto.getTo());
            domain.customerOrderNo = genOrderNo();
            domain.cargoes = Cargo.formatCargoes(orderDto.getCargoes(), domain);
            domain.deliverType = Constant.DeliverType.SAME_CITY;
            //TODO 异地需要拆多段运单 现在默认同城
//            domain.deliverType = domain.from.getCity().equals(domain.to.getCity()) ? Constant.DeliverType.SAME_CITY : Constant.DeliverType.NATIONAL;
            domain.distance = orderDto.getDistance();
            domain.preInsert();
            domain.setPayment(payment);
//            orderDto
            BigDecimal payPrice = orderDto.getPayment().getDeliverPrice().add(orderDto.getPayment().getInsurancePrice());
            orderDto.getPayment().setPayPrice(payPrice);
            domain.setSubPayment(orderDto.getPayment());
            domains.add(domain);
        }
        return domains;
    }

    //TODO 计算运费
//    private BigDecimal countPrice(CreateOrderRequestVo customerOrderDetail) {
//        return new BigDecimal("1000");
//    }

    private static String genOrderNo() {
        return "C" + IDGen.nextId();
    }

}
