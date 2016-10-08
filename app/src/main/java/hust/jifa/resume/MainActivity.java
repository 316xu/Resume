package hust.jifa.resume;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.widget.FrameLayout;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity {

    CircleImageView mAvatarView;
    FrameLayout mAvatarContainerView;
    TextView mNameView;
    View mButtonLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1000);
            }
        }

        mAvatarView = (CircleImageView) findViewById(R.id.avatar);
        mAvatarContainerView = (FrameLayout) findViewById(R.id.avatar_container);
        mNameView = (TextView) findViewById(R.id.name);
        mButtonLayout = findViewById(R.id.button_layout);

        mAvatarContainerView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mAvatarContainerView.setOnClickListener(null);
                expand(mAvatarContainerView);
                findViewById(R.id.start).startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpha_hide));
            }
        });
    }

    public  void expand(final View v){
    final int startHeight = v.getMeasuredHeight();
    final int startWidth = v.getMeasuredWidth();
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int targetHeight = getWindow().getDecorView().getHeight();
        final int targetWidth =  getWindow().getDecorView().getWidth();

        mAvatarView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale));
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = (int) (startHeight + (targetHeight - startHeight) * interpolatedTime);
                v.getLayoutParams().width = (int) (startWidth + (targetWidth - startWidth) * interpolatedTime);
                v.requestLayout();
                if (interpolatedTime == 1f) {
                    showContent();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration(1500);
        v.startAnimation(a);
    }

    public void showContent() {
        mNameView.setVisibility(View.VISIBLE);
        mButtonLayout.setVisibility(View.VISIBLE);
        AlphaAnimation animation1 = new AlphaAnimation(0.2f, 1.0f);

        animation1.setDuration(1000);
        animation1.setFillAfter(true);
        mNameView.startAnimation(animation1);
        mButtonLayout.startAnimation(animation1);
    }

    public void onEducationBackgroundClick(View view) {
        Intent intent = new Intent(this, EducationBackground.class);
        startActivity(intent);
    }

    public void onSkillClick(View view) {
        Intent intent = new Intent(this, SkillActivity.class);
        startActivity(intent);
    }

    public void onExperienceClick(View view) {
        Intent intent = new Intent(this, ExperienceActivity.class);
        startActivity(intent);
    }

    public void onOtherClick(View view) {
        Intent intent = new Intent(this, OtherActivity.class);
        startActivity(intent);
    }
}
