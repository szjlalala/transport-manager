package com.tms.common;

public class Constant {
    /*NOT_PAID : "待支付",
    NOT_DISTRIBUTED : "待分配",
    NOT_RECEIVED : "待接货",
    ONBOARD : "配送中",
    COMPLETED : "送货完成",
    CONFIRMED : "确认收货",
    INVALID : "已取消",
    SPLITTED : "已拆分"*/

    public enum OrderState {
        NOT_PAID, NOT_DISTRIBUTED, NOT_RECEIVED, ONBOARD, COMPLETED, CONFIRMED, INVALID,SPLITTED
    }

    public enum OrderSource {
        PC, WAP, BACK, IOS, ANDROID
    }

    public enum DeliverType {
        SAME_CITY, NATIONAL
    }

    public enum PayState {
        UNPAY, COMPLETE, PARTPAY;

    }

    public enum PayType {
        SENDER_PAY, RECEIVER_PAY, SENDER_ORDER_PAY;

    }

    public enum AttendanceType {
        ON, OFF
    }

    public enum CargoType {
        NORMAL, FRAGILE,FROZEN
    }

    public enum LocationType {
        CUSTOMER, DISTRIBUTION
    }
    public enum MsgType {
        NEW_ORDER
    }

    public enum MsgAuth {
        CUSTOMER, MANAGER, DRIVER
    }

    public enum AllocateType{
        AUTO,MANUAL,COMPETE
    }
    public enum Gender {
        MALE("男"), FEMALE("女");
        private String desc;
        Gender(String desc){
            this.desc = desc;
        }
        public static Gender getInstance(String desc){
            for(Gender gender : Gender.class.getEnumConstants()){
                if(desc.equals(gender.getDesc())){
                    return gender;
                }
            }
            return null;
        }
        public String getDesc() {
            return desc;
        }
    }

    public enum DriverState {
        ON, OFF
    }
    public enum VehicleState {
        ON, OFF
    }

    public enum PayChannel {
        ALIPAY("ALIPAY", "支付宝"),
        WECHATPAY("WECHATPAY", "微信(APP)"),
        POS("POS", "POS"),
        CASH("CASH", "现金");

        private String channel;
        private String desc;

        PayChannel(String channel, String desc) {
            this.channel = channel;
            this.desc = desc;
        }

        public String getChannel() {
            return this.channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }
    }
}
