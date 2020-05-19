package com.solplatform.factorys.component;

import com.solplatform.factorys.component.impl.DubboProcessor;
import com.solplatform.factorys.component.impl.HttpProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 组件工厂类
 *
 * @author sol
 * @create 2020-05-08  10:34 上午
 */
@Component
public class ComponentFactory {

    @Autowired
    HttpProcessor httpProcessor;
    @Autowired
    DubboProcessor dubboProcessor;

    public ComponentProcessor getComponent(String componentType) {
        if ("http".equals (componentType)) {
            return httpProcessor;
        } else if ("dubbo".equals (componentType)) {
            return dubboProcessor;
        } else {
            return null;
        }
    }
}
