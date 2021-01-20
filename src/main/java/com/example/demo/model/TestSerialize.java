package com.example.demo.model;

import com.example.demo.annotation.TestUrlStringSer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * description:TestMessage
 * create: 2020/12/18 17:44
 *
 * @author MingXin.Nie
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestSerialize implements Serializable {

    @TestUrlStringSer(w = "q", h = "w", q = "e", a = "r")
    private String id;

    @TestUrlStringSer(w = "1", h = "2", q = "3", a = "4")
    private String name;
}
