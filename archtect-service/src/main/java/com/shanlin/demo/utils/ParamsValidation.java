package com.shanlin.demo.utils;

import org.springframework.util.Assert;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * 用于entity/dto/vo等参数校验，支持级联对象<br>
 * 
 * @Null 被注释的元素必须为 null
 * @NotNull 被注释的元素必须不为 null
 * @AssertTrue 被注释的元素必须为 true
 * @AssertFalse 被注释的元素必须为 false
 * @Min(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 * @Max(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 * @DecimalMin(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 * @DecimalMax(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 * @Size(max=, min=) 被注释的元素的大小必须在指定的范围内
 * @Digits (integer, fraction) 被注释的元素必须是一个数字，其值必须在可接受的范围内
 * @Past 被注释的元素必须是一个过去的日期
 * @Future 被注释的元素必须是一个将来的日期
 * @Pattern(regex=,flag=) 被注释的元素必须符合指定的正则表达式
 * @Valid 级联对象，<b>注：</b>使用此注解之前，请先使用NotNull,否则当级联对象为null时，不能校验。<br>
 * 
 * Hibernate Validator 附加的 constraint<br>
 * @NotBlank 验证字符串非null，且长度必须大于0
 * @Email 被注释的元素必须是电子邮箱地址
 * @Length(min=,max=) 被注释的字符串的大小必须在指定的范围内
 * @NotEmpty 被注释的字符串的必须非空
 * @Range(min=,max=,message=) 被注释的元素必须在合适的范围内
 * 
 * @author shazl
 */
public class ParamsValidation{
    private LocalValidatorFactoryBean validationFactory;
    /**
     * 功能描述: 校验参数<br>
     *
     * @param json  需转为对象的json数据
     * @param targetClass 所有需要的对象类型
     * @return 所有错误信息
     */
    public <T> Errors validate(String json, Class<T> targetClass){
        Assert.notNull(json, "json参数不能为空");
        Assert.notNull(targetClass, "targetClass不能为空");
        
        T t = JsonUtil.fromJson(json, targetClass);
        
        return this.validate(t);
    }

    /**
     * 功能描述: 校验参数<br>
     *
     * @param obj 需校验数据的对象，不可为空
     * @return 所有错误信息
     */
    public <T> Errors validate(T obj){
        Assert.notNull(obj);
        
        Errors errors = new BeanPropertyBindingResult(obj, obj.getClass().getName());
        ValidationUtils.invokeValidator(validationFactory, obj, errors);
        
        return errors;
    }
    
    public void setValidationFactory(LocalValidatorFactoryBean validationFactory) {
        this.validationFactory = validationFactory;
    }
}
