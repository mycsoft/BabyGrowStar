package cn.mycsoft.babygrowstar.act;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;
import com.umeng.update.UpdateStatus;

import cn.mycsoft.babygrowstar.R;
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

    private ImageView ball;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_home);

        numberView = (TextView) findViewById(R.id.star_number);
        todayNumberView = (TextView) findViewById(R.id.star_numberToday);
        ball = (ImageView) findViewById(R.id.image_ball);
//        final View controlsView = findViewById(R.id.fullscreen_content_controls);
//        final View contentView = findViewById(R.id.fullscreen_content);

        // Set up an instance of SystemUiHider to control the system UI for
        // this activity.
//        mSystemUiHider = SystemUiHider.getInstance(this, contentView, HIDER_FLAGS);
//        mSystemUiHider.setup();
//        mSystemUiHider
//                .setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
//                    // Cached values.
//                    int mControlsHeight;
//                    int mShortAnimTime;
//
//                    @Override
//                    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
//                    public void onVisibilityChange(boolean visible) {
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
//                            // If the ViewPropertyAnimator API is available
//                            // (Honeycomb MR2 and later), use it to animate the
//                            // in-layout UI controls at the bottom of the
//                            // screen.
//                            if (mControlsHeight == 0) {
//                                mControlsHeight = controlsView.getHeight();
//                            }
//                            if (mShortAnimTime == 0) {
//                                mShortAnimTime = getResources().getInteger(
//                                        android.R.integer.config_shortAnimTime);
//                            }
//                            controlsView.animate()
//                                    .translationY(visible ? 0 : mControlsHeight)
//                                    .setDuration(mShortAnimTime);
//                        } else {
//                            // If the ViewPropertyAnimator APIs aren't
//                            // available, simply show or hide the in-layout UI
//                            // controls.
//                            controlsView.setVisibility(visible ? View.VISIBLE : View.GONE);
//                        }
//
//                        if (visible && AUTO_HIDE) {
//                            // Schedule a hide().
////                            delayedHide(AUTO_HIDE_DELAY_MILLIS);
//                        }
//                    }
//                });

        // Set up the user interaction to manually show or hide the system UI.
//        contentView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (TOGGLE_ON_CLICK) {
//                    mSystemUiHider.toggle();
//                } else {
//                    mSystemUiHider.show();
//                }
//            }
//        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);


        UmengUpdateAgent.setUpdateAutoPopup(false);
        UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {
            @Override
            public void onUpdateReturned(int updateStatus, UpdateResponse updateInfo) {
                Context mContext = HomeActivity.this;
                switch (updateStatus) {
                    case UpdateStatus.Yes: // has update
                        ball.setVisibility(View.VISIBLE);
                        UmengUpdateAgent.showUpdateDialog(mContext, updateInfo);
                        break;
//                    case UpdateStatus.No: // has no update
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


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

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

//    Handler mHideHandler = new Handler();
//    Runnable mHideRunnable = new Runnable() {
//        @Override
//        public void run() {
//            mSystemUiHider.hide();
//        }
//    };

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

    public void update(View v) {
        UmengUpdateAgent.forceUpdate(this);
//        update();
    }

    private void update() {

    }

//    @Override
//    public void onStarSelected(Long id) {
//        AddActivity.startForEdit(this, id, 2000);
//    }

    @Override
    public void onStarSelected(StarRecord star) {
        starApp.openEditAct(this, star);
    }

    //============= 任务清单 ======================
    //实现保存添加星星
    //======== 数据库设计 ==========
    //使用记录
    //

    //TODO 实现使用星星
    //TODO 实现友盟SDK

    //TODO 实现记录查看
    //TODO 实现密码设置
    //TODO 实现保存到百度云盘

}
