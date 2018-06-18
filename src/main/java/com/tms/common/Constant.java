package com.tms.common;

public class Constant {

    public enum OrderState {
        TEMP, UNALLOCATED, TRANSPORTING, COMPLETE, INVALID
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

    public enum DeliverOrderState {
        UNALLOCATED, UNLOAD, TRANSPORTING, COMPLETE, CANCEL
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
        MALE, FEMALE
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

        private PayChannel(String channel, String desc) {
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
