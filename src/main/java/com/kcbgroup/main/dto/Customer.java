package com.kcbgroup.main.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @ AUTHOR MKOECH
 **/
@Data
@Builder
public class Customer {
    private int id;
    private String name;
}
