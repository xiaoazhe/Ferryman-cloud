package com.ferry.core.file.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class Config extends AbstractDO {
    private String configKey;
    private String configValue;
}
