package com.tms.model;


import com.tms.common.Constant;
import com.tms.util.IDGen;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.nio.DoubleBuffer;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
public class DeliverOrder extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String deliverOrderNo;//运单No
    @OneToOne
    @JoinColumn
    private Location from;
    @OneToOne
    @JoinColumn
    private Location to;
    private Date startTime;
    private Date endTime;
    @OneToOne
    @JoinColumn
    private Driver driver;
    @OneToOne
    @JoinColumn
    private Vehicle vehicle;
    @ManyToOne
    @JoinColumn(name = "customer_order_id")
    private CustomerOrder customerOrder;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deliverOrder", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<DeliverCargo> cargoes;

    private Constant.OrderState deliverOrderState;

    private Integer sequence;
    private Double distance;

    public DeliverOrder(CustomerOrder customerOrder, Integer sequence) {
        this.deliverOrderNo = genOrderNo();
        this.sequence = sequence;
        this.from = customerOrder.getFrom();
        this.to = customerOrder.getTo();
        this.customerOrder = customerOrder;
        this.deliverOrderState = customerOrder.getState();
        List<DeliverCargo> deliveryCargoes = customerOrder.getCargoes().stream().map(cargo -> {
            DeliverCargo cargo1 = new DeliverCargo();
            BeanUtils.copyProperties(cargo, cargo1, "id");
            cargo1.setDeliverOrder(this);
            return cargo1;
        }).collect(Collectors.toList());
        this.setCargoes(deliveryCargoes);
        preInsert();
    }

    private String genOrderNo() {
        return "D" + IDGen.nextId();
    }
}
