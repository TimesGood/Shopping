# 该混淆作用于library
#ProGuard（混淆）三大作用
#一、压缩，默认开启，减少应用体积，移除未被使用的类和成员
#-dontshrink 关闭压缩
#二、优化，默认开启在字节码级别执行优化，让应用运行的更快
#-dontoptimize 关闭优化
#三、混淆，默认开启，增大反编译难度，类和类成员会被随机命名，除非用keep保护
#-dontobfuscate 关闭混淆
#如果不需要保持类名，只需要把该类下的特定方法不被混淆就好，那就用-keepclassmembers，官网表述
#**************************************************************************************
#*保留                       防止被移除或者被重命名             防止被重命名                 *
#*类和类成员                  -keep	                      -keepnames                  *
#*仅类成员	                -keepclassmembers	          -keepclassmembernames       *
#*如果拥有某成员，保留类和类成员	-keepclasseswithmembers	      -keepclasseswithmembernames *
#**************************************************************************************
#*********************示例****************************
#一星表示保持该包下的类名，而子包下的类名还是会被混淆，二星表示把本包和所包含之下子包的类名都保持
#如果还想保持类之内的东西不被混淆就需要{*;}
#-keep class cn.hadcn.test.*
#-keep class cn.hadcn.test.**
#-keep class cn.hadcn.test.**{*;}
#还可以使用java语法规则避免混淆
#-keep public class * extends android.app.Activity
#如果要保持XXX.class的内部类YYY.class中所有public方法不被混淆
#-keep class com.?.?.XXX$YYY {public *;}
#如果一个类中不希望保持全部内容不被混淆，希望保持类中的特定内容，可以：
#<init>;     //匹配所有构造器
#<fields>;   //匹配所有域
#<methods>;  //匹配所有方法方法
#还可以加入参数
#-keep class cn.hadcn.test.One {
#   public <init>(org.json.JSONObject);
#}
-optimizationpasses 5 # 代码混淆的压缩比例，值介于0-7，默认5
-dontusemixedcaseclassnames # 混淆后类型都为小写
-verbose # 混淆时记录日志
-printseeds seeds.txt # 列出未混淆的类和成员
-printusage ussed.txt # 列出从apk中删除的代码
-printmapping mapping.txt # 生成原类名与混淆后类名的映射文件mapping.txt
-optimizations !code/simplification/artithmetic,!field # 指定混淆算法
-keepattributes *Annotation*,Signature # 避免混淆注解、内部类、泛型、匿名类，可选：*Annotation*,InnerClasses,Signature,EnclosingMethod

#################glide###############
-keep public class * implements com.bumptech.glide.module.AppGlideModule
-keep public class * implements com.bumptech.glide.module.LibraryGlideModule

#枚举
-keepclassmembers enum com.**.enums.* {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
#jni方法不可混淆，方法名需与native方法保持一致
-keepclasseswithmembernames class * { # 保持native方法不被混淆
    native <methods>;
}
#gson
-keep class com.google.gson.** {*;}