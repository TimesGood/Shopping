# MVPApplication
基于MVP架构的安卓开发框架，其中基于原始组件拓展功能，可直接使用，是开发更加简便。
可当做参考，自己做自定义View也有一定的收获
# 第三方技术
okhttp #网络请求  
retrofit #配合okhttp网络请求  
gson #Gson解析json  
rxjava #流式异步请求  
glide #图片渲染  
PhotoView #图片预览  
MPAndroidChart #图表  
lottie #动画加载  
autodispose #生命周期  
zxing-android-embedded # 二维码扫描  
dagger2 #依赖注入  

# 使用案例
```java
// Activity
public class DemoActivity extends BaseActivity<DemoPresenter> implements DemoContract.View , View.OnClickListener {
    Button btn_test;
    @Inject
    Permission permission;

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {

    }


    @Override
    public void setupActivityComponent(@NonNull @NotNull AppComponent appComponent) {
        DaggerDemoComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
}

    @Override
    public int getLayoutId() {
        return R.layout.activity_demo;
    }

    @Override
    public void initView(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        btn_test = findViewById(R.id.btn_test);
        btn_test.setOnClickListener(this);
    }

    @Override
    public void initData(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
    }
    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onClick(View view) {
        //调用P层方法，发起请求
        mPresenter.requestTest();
    }
}
//Fragment
public class DemoFragment extends BaseFragment<DemoPresenter> implements DemoContract.View{
    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerDemoComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_demo;
    }

    @Override
    public boolean useEventBus() {
        return false;
    }

    @Override
    public void initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}

// Contract
public interface DemoContract {
    interface Model extends IModel {
        Observable<CommonResult<TokenVo>> requestTest();
    }
    interface View extends IView {
    }
}
// Presenter
@ActivityScope
public class DemoPresenter extends BasePresenter<DemoContract.View, DemoContract.Model> {

    @Inject
    public DemoPresenter(DemoContract.Model model, DemoContract.View view) {
        super(model, view);
    }

    public void requestTest(){
        mModel.requestTest()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .to(mView.bindAutoDispose())
                .subscribe(new Observer<CommonResult<TokenVo>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        //订阅时
                    }

                    @Override
                    public void onNext(@NonNull CommonResult<TokenVo> tokenVoCommonResult) {
                        //执行任务完成
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        //执行任务出错
                    }

                    @Override
                    public void onComplete() {
                        //
                    }
                });
    }
}
// Model
@ActivityScope
public class DemoModel extends BaseModel implements DemoContract.Model {
    
    @Inject
    public DemoModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    public Observable<CommonResult<TokenVo>> requestTest(){
        return mRepositoryManager.obtainRetrofitService(ApiService.class)
                .login("test","123456");
    }

}
// ApiServer
public interface ApiService {

    //登录
    @POST("/sso/login")
    Observable<CommonResult<TokenVo>> login(@Query("username") String username,@Query("password") String password);

}
```
