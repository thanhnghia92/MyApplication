package com.example.buitiendat.myapplication;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SizeReadyCallback;

/**
 * Created by bui.tien.dat on 4/4/18.
 */

public class DetailFragment extends Fragment {

    RelativeLayout rlDetail;
    ImageView imgDetail;
    View detailBackground;
    RelativeLayout rlAnimation;
    LinearLayout llDetail;
    int top = 0;
    int pos = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail, container, false);
        rlDetail = (RelativeLayout) v.findViewById(R.id.rl_detail);
        imgDetail = (ImageView) v.findViewById(R.id.img_detail);
        detailBackground = (View) v.findViewById(R.id.detail_background);
        rlAnimation = (RelativeLayout) v.findViewById(R.id.innerLayer);
        llDetail = (LinearLayout) v.findViewById(R.id.ll_detail);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle b = getArguments();
        top = b.getInt("top");
        pos = b.getInt("position");
        setOpenDetail(pos);
    }

    private void setOpenDetail(int position) {
        if (position == 4) {
            Glide.with(this)
                    .load("https://static.toiimg.com/photo/59146158/Microsoft-Surface-Phone.jpg")
                    .into(imgDetail);
//            Glide.with(this).load("https://static.toiimg.com/photo/59146158/Microsoft-Surface-Phone.jpg")
//                    .into(imgDetail)
//                    .getSize(new SizeReadyCallback() {
//                        @Override
//                        public void onSizeReady(int width, int height) {
//                            Log.e("DAT", ">>>>>>>" + width + "//" + height);
//                        }
//                    });
        } else if (position % 2 == 0) {
            Glide.with(this)
                    .load(R.drawable.img2)
                    .into(imgDetail);
        } else {
            Glide.with(this)
                    .load(R.drawable.item)
                    .into(imgDetail);
        }

        rlAnimation.setAnimation(null);
        detailBackground.setAnimation(null);
        rlDetail.setVisibility(View.GONE);
        rlAnimation.setVisibility(View.GONE);
        detailBackground.setVisibility(View.GONE);

        rlAnimation.setTranslationY(top);

        rlAnimation.setVisibility(View.VISIBLE);
        rlDetail.setVisibility(View.VISIBLE);
        detailBackground.setVisibility(View.VISIBLE);

        setTranslate(rlAnimation, top, true);
        setAnphaDetail(detailBackground);

    }

    private void setAnphaDetail(View view) {
        Animation animation = new AlphaAnimation(0f, 1f);
        animation.setFillAfter(true);
        animation.setDuration(500);
        view.startAnimation(animation);
    }

    private void setTranslate(final View v, int top, boolean isFillAfter) {
        TranslateAnimation animate = new TranslateAnimation(v.getX(), v.getX(), 0, -top);
        animate.setDuration(500);
        animate.setFillAfter(isFillAfter);
        v.startAnimation(animate);

        final ResizeAnimation mResizeAnimation = new ResizeAnimation(v);
        imgDetail.postDelayed(new Runnable() {
            @Override
            public void run() {
                v.setTranslationY(0);
                v.setAnimation(null);
                mResizeAnimation.setStartParams(imgDetail.getHeight(), imgDetail.getHeight() + 350, false);
                mResizeAnimation.setDuration(300);
                v.startAnimation(mResizeAnimation);
                llDetail.setVisibility(View.VISIBLE);
                setAnphaDetail(llDetail);
            }
        }, 500);


    }
}