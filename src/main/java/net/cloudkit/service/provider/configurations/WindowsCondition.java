package net.cloudkit.service.provider.configurations;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2018/8/23 17:00
 */
public class WindowsCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata){
        return context.getEnvironment().getProperty("os.name").contains("Windows");
    }

}
