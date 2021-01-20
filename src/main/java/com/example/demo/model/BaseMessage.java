package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * description:BaseMessage
 * create: 2020/12/21 17:48
 *
 * @author MingXin.Nie
 * @version 1.0
 */
@Data
public class BaseMessage implements Serializable {

    private String tenantId;

}
