package hust.jifa.resume;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * Created by jfxu on 16/10/2.
 */
public class SkillActivity extends Activity {

    LinearLayout mLayout;
    TextView mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill);
        mLayout = (LinearLayout) findViewById(R.id.process);
        mContentView = (TextView) findViewById(R.id.content);
        setListener();
    }

    private void setListener() {
        final ViewTreeObserver observer = getWindow().getDecorView().getViewTreeObserver();

        observer.addOnGlobalLayoutListener(

                new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        TestHandler testHandler = new TestHandler(mLayout);
                        testHandler.sendEmptyMessage(0);

                        Snackbar snackbar = Snackbar.make(mLayout, "点击查看详细信息", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                }
        );
    }

    public void onSkillClicK(View view) {
        String s = "主要擅长java和android，有近2年的经验了，可以全栈工作\n热爱学习新的技术，像前端的知识，react-native，响应式编程，unity3d之类的都尝试过\n原型图，PS之类的东西也耍过";
        switch (view.getId()) {
            case R.id.c:
                s = "编程入门语言，掌握程度良好，但是只用来做过课设";
                break;
            case R.id.java:
                s = "第一门主动去学的语言，到目前为止用的最多的，可以很熟练的使用";
                break;
            case R.id.android:
                s = "主要研究方向，平时做过很多的项目，日记，聊天，天气，桌面宠物等很多小项目，有过android实习经历，能够实现各种应用效果，可以做自动化测试，对于一些机制也有着比较深入的了解";
                break;
            case R.id.kotlin:
                s ="一门类java语言，jetbrain 公司推出，当时出于兴趣学了一下，不过当时由于刚出来还有很多bug，只用这个写过一个项目";
                break;
            case R.id.react_native:
                s = "facebook 出的一款，用 js 来写 native app，可以动态编译，目前正在学习";
                break;
            case R.id.php:
                s = "开发的时候有时候需要用到服务器，然后就学了一下，掌握程度大概是需要看着api写";
                break;
            case R.id.nodejs:
                s = "第二次实习做全栈，服务器用的 nodejs，一般用都没什么问题";
                break;
            case R.id.cocos:
                s = "第二次实习做全栈，客户端用的cocos creator，可以很熟练的使用";
                break;
            case R.id.other:
                s = "我始终相信技术只是手段，需要什么用什么，以后需要什么都会去认真学习";
                break;
            default:
                break;
        }

        mContentView.setText(s);
    }

    private static class TestHandler extends Handler{
        WeakReference<ViewGroup> reference;

        private TestHandler(ViewGroup viewGroup) {
            reference = new WeakReference<>(viewGroup);
        }
        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);
            int pos = msg.what;
            ViewGroup viewGroup = reference.get();

            int count = viewGroup.getChildCount();
            if (count == pos) {
                return;
            }
            View v = viewGroup.getChildAt(pos);

            if (v instanceof ViewGroup) {
                TestHandler testHandler = new TestHandler((ViewGroup) v);
                testHandler.sendEmptyMessage(0);
            }
            v.setVisibility(View.VISIBLE);
            v.startAnimation(AnimationUtils.loadAnimation(viewGroup.getContext(), R.anim.alpha_show));
            sendEmptyMessageDelayed(pos + 1, 500);
        }
    }
}
