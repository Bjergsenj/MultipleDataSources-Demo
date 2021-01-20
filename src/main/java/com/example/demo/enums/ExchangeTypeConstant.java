package com.example.demo.enums;

/**
 * description:ExchangeConstant
 * create: 2020/12/18 16:40
 *
 * @author MingXin.Nie
 * @version 1.0
 */
public class ExchangeTypeConstant {

    private ExchangeTypeConstant() {
    }

    public static final String DIRECT = "direct";
    public static final String FANOUT = "fanout";
    public static final String HEADERS = "headers";
    public static final String TOPIC = "topic";
    public static final String DELAY = "x-delayed-message";

}
