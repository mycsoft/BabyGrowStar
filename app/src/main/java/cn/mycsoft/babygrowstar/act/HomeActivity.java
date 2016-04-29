package cn.mycsoft.babygrowstar.act;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qihoo.appstore.updatelib.UpdateManager;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;
import com.umeng.update.UpdateStatus;

import cn.mycsoft.babygrowstar.R;
import cn.mycsoft.babygrowstar.StarService;
import cn.mycsoft.babygrowstar.entity.StarRecord;
import cn.mycsoft.babygrowstar.frg.PayItemFragment;
import cn.mycsoft.babygrowstar.util.SystemUiHider;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class HomeActivity extends AbstractActivity implements PayItemFragment.OnFragmentInteractionListener {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = false;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * If set, will toggle the system UI visibility upon interaction. Otherwise,
     * will show the system UI visibility upon interaction.
     */
    private static final boolean TOGGLE_ON_CLICK = true;

    /**
     * The flags to pass to {@link SystemUiHider#getInstance}.
     */
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };
    /**
     * The instance of the {@link SystemUiHider} for this activity.
     */
//    private SystemUiHider mSystemUiHider;

    private TextView numberView;
    private TextView todayNumberView;
    //设置的通知泡泡
    private TextView popView;

    private ImageView ball;

    /**
     * 反馈变化接收器。
     */
    private FeedbackChangeReceiver feedbackReciver = new FeedbackChangeReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_home);

        numberView = (TextView) findViewById(R.id.star_number);
        todayNumberView = (TextView) findViewById(R.id.star_numberToday);
        ball = (ImageView) findViewById(R.id.image_ball);
        popView = (TextView) findViewById(R.id.pop);


        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);

        //自动更新
        upgrade();

    }

    /**
     * 使用友盟升级。
     */
    private void upgradeByUmeng() {
        UmengUpdateAgent.setUpdateAutoPopup(false);
        UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {
            @Override
            public void onUpdateReturned(int updateStatus, UpdateResponse updateInfo) {
                Context mContext = HomeActivity.this;
                switch (updateStatus) {
                    case UpdateStatus.Yes: // has upgrade
                        ball.setVisibility(View.VISIBLE);
                        UmengUpdateAgent.showUpdateDialog(mContext, updateInfo);
                        break;
//                    case UpdateStatus.No: // has no upgrade
//                        Toast.makeText(mContext, "没有更新", Toast.LENGTH_SHORT).show();
//                        break;
//                    case UpdateStatus.NoneWifi: // none wifi
//                        Toast.makeText(mContext, "没有wifi连接， 只在wifi下更新", Toast.LENGTH_SHORT).show();
//                        break;
//                    case UpdateStatus.Timeout: // time out
//                        Toast.makeText(mContext, "超时", Toast.LENGTH_SHORT).show();
//                        break;
                }
            }
        });
        UmengUpdateAgent.update(this);
    }


    /**
     * 使用360升级；
     */
    private void upgradeBy360() {
//        UpdateManager.setTestMode(8);
        UpdateManager.checkUpdate(this);
    }

//    /**
//     * 使用应用宝升级；
//     */
//    private void upgradeByYinYongBao() {
////        //该管理类提供下载client和下载设置client的获取接口
////        TMAssistantDownloadManager downloadManager = TMAssistantDownloadManager.getInstance(getApplicationContext());
////
////        //通过管理器创建下载设置对象settingClient
////        TMAssistantDownloadSettingClient settingClient = downloadManager.getDownloadSDKSettingClient();
////        settingClient.setDownloadSDKWifiOnly(true); //允许只在wifi下下载，默认值为false
////        settingClient.setDownloadSDKMaxTaskNum(3);  //允许同时下载最大任务数为3，默认值为5
////
//        ApkUpdateManager updateManager = ApkUpdateManager.getInstance();
//        updateManager.init(this);
//        updateManager.checkUpdate(Arrays.asList("cn.mycsoft.babygrowstar"));
//    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }


    /**
     * 打开添加星星记录列表。
     *
     * @param view
     */
    public void openInputList(View view) {
        startActivity(new Intent(this, InputListAct.class));
    }

    /**
     * 打开设置画面
     *
     * @param view
     */
    public void openSettingView(View view) {

        startActivity(new Intent(this, SettingsActivity.class));
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
//        mHideHandler.removeCallbacks(mHideRunnable);
//        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }


    @Override
    protected void onResume() {
        super.onResume();
        int c = getController().queryStarTotal();
        int today = getController().queryStarTodayTotal();
        numberView.setText(String.valueOf(c));
        todayNumberView.setText(String.valueOf(today));

        //注册反馈接收器。
        registerReceiver(feedbackReciver, new IntentFilter(StarService.ACTION_FEEDBACK));
    }

    @Override
    protected void onPause() {
        //注销反馈接收器。
        unregisterReceiver(feedbackReciver);
        super.onPause();

    }

    public void openAdd(View v) {
//        startActivity(new Intent(this,AddActivity.class));
        getStarApp().openFastAddStar(this, 1000);
//        AddActivity.startForAdd(this, 1000);
    }

    public void openRedeem(View v) {
//        startActivity(new Intent(this,AddActivity.class));
        AddRedeemActivity.startForAdd(this, 1000);
//        Toast.makeText(this, "代码测试中...", Toast.LENGTH_SHORT);
    }

    public void upgrade(View v) {
//        UmengUpdateAgent.forceUpdate(this);
        upgrade();

    }

    private void upgrade() {
        upgradeBy360();
    }

//    @Override
//    public void onStarSelected(Long id) {
//        AddActivity.startForEdit(this, id, 2000);
//    }

    @Override
    public void onStarSelected(StarRecord star) {


        starApp.openEditAct(this, star);
    }

    public void openFeedbackView(View view) {
        //清理通知
        popView.setText(null);
        popView.setVisibility(View.INVISIBLE);

        getStarApp().opentFeedback(this);
    }


    /**
     * 反馈变化接收器。
     */
    public class FeedbackChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int count = intent.getIntExtra("unread", 0);
            if (count == 0) {
                popView.setText(null);
                popView.setVisibility(View.INVISIBLE);
            } else {

                popView.setText(count > 9 ? "9+" : String.valueOf(count));
                popView.setVisibility(View.VISIBLE);
            }

        }
    }

    //============= 任务清单 ======================
    //实现保存添加星星
    //======== 数据库设计 ==========
    //使用记录
    //

    //TODO 实现密码设置
    //TODO 实现保存到百度云盘

}
