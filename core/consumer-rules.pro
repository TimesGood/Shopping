# 该混淆配置会作用于全局
#框架程序员自定义的类会用到反射创建对象，需要避免混淆
-keep public class * implements com.example.core.base.delegate.ConfigModule