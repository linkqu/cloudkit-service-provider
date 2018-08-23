//package net.cloudkit.service.provider.configurations;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Conditional;
//
///**
// * ConditionConfiguration
// * @ConditionalOnBean（仅仅在当前上下文中存在某个对象时，才会实例化一个Bean）
// * @ConditionalOnClass（某个class位于类路径上，才会实例化一个Bean）
// * @ConditionalOnExpression（当表达式为true的时候，才会实例化一个Bean）
// * @ConditionalOnMissingBean（仅仅在当前上下文中不存在某个对象时，才会实例化一个Bean）
// * @ConditionalOnMissingClass（某个class类路径上不存在的时候，才会实例化一个Bean）
// * @ConditionalOnNotWebApplication（不是web应用）
// *
// * @author hongquanli <hongquanli@qq.com>
// * @version 1.0 2018/8/23 17:02
// */
//public class ConditionConfiguration {
//
//    /**
//     * matches方法返回true的，就运行哪个方法
//     * 通过@Condition注解，符合Windows条件则实例化 windowsService
//     */
//    @Bean
//    @Conditional(WindowsCondition.class)
//    public WindowsService windowsService(){
//        return new WindowsServiceImpl();
//    }
//
//    /**
//     * 通过@Condition注解,符合Linux条件则实例化 linuxService
//     */
//    @Bean
//    @Conditional(LinuxCondition.class)
//    public LinuxService linuxService(){
//        return new LinuxServiceImpl();
//    }
//}
