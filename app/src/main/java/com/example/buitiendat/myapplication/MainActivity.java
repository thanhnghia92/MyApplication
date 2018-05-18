package com.example.buitiendat.myapplication;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daasuu.ei.Ease;
import com.daasuu.ei.EasingInterpolator;

import java.util.ArrayList;
import java.util.List;

import recyclerview.animators.CustomItemAnimator;
import recyclerview.animators.SlideInDownAnimator;


public class MainActivity extends AppCompatActivity {

    RecyclerView rcv;
    AmimationAdapter adapter;

    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcv = (RecyclerView) findViewById(R.id.rcv);
        frameLayout = (FrameLayout) findViewById(R.id.framelayout);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rcv.setLayoutManager(mLayoutManager);
//        SlideInDownAnimator slideInDownAnimator=new SlideInDownAnimator(new Interpolator() {
//            @Override
//            public float getInterpolation(float input) {
//                return (float) (Math.pow(2, (-10 * input)) * Math.sin(((2* Math.PI) * (input - (0.3f/4)))/0.3f) + 1);
//            }
//        });
//        rcv.setItemAnimator(slideInDownAnimator);
        CustomItemAnimator customItemAnimator=new CustomItemAnimator();
        customItemAnimator.setDefaultInterpolator(new Interpolator() {
            @Override
            public float getInterpolation(float input) {
                return (float) (Math.pow(2, (-10 * input)) * Math.sin(((2* Math.PI) * (input - (0.3f/4)))/0.3f) + 1);
            }
        });
        rcv.setItemAnimator(customItemAnimator);
        rcv.getItemAnimator().setAddDuration(1000);
        rcv.getItemAnimator().setMoveDuration(1000);
       // rcv.getItemAnimator().setChangeDuration(1000);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i + ":item");
        }
        adapter = new AmimationAdapter(list);
        rcv.setAdapter(adapter);

        TextView textView = findViewById(R.id.number);
        textView.setText(-1 + "");

        final View viewDropDown = findViewById(R.id.drop_down_view);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) viewDropDown.getLayoutParams();
        params.setMargins(0, -300, 0, 0);

        Button buttonStart = findViewById(R.id.start);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.mList.add(0,"i "+0);
                adapter.notifyItemInserted(0);

                rcv.scrollToPosition(0);
//                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) viewDropDown.getLayoutParams();
//                params.setMargins(0, -300, 0, 0);
//                viewDropDown.setVisibility(View.VISIBLE);
//                ObjectAnimator animator = ObjectAnimator.ofFloat(viewDropDown, "translationY", 0, 300);
//                animator.setInterpolator(new EasingInterpolator(Ease.LINEAR));
//                animator.setStartDelay(100);
//                animator.setDuration(1000);
//                animator.start();
//                animator.addListener(new Animator.AnimatorListener() {
//                    @Override
//                    public void onAnimationStart(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        viewDropDown.setVisibility(View.GONE);
//                        adapter.isLoad = true;
//                        //adapter.notifyDataSetChanged();
//                       // adapter.notifyItemChanged(0);//notifyDataSetChanged();
//                        adapter.notifyItemInserted(0);
//                       // adapter.notifyItemChanged(2);
//                      //  adapter.notifyItemChanged(3);
//                    }
//
//                    @Override
//                    public void onAnimationCancel(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animator animation) {
//
//                    }
//                });
            }
        });
    }

    class AmimationAdapter extends RecyclerView.Adapter<AmimationAdapter.MyViewHolder> {

        private List<String> mList;
        public boolean isLoad = false;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public ImageView img;
            public ImageView imgBorder;
            public ImageView imgFavorite;
            public RelativeLayout rlItem;
            TextView mNumber;

            public MyViewHolder(View view) {
                super(view);
                img = (ImageView) view.findViewById(R.id.img);
                imgBorder = (ImageView) view.findViewById(R.id.img_border);
                rlItem = (RelativeLayout) view.findViewById(R.id.innerLayer);
                imgFavorite = (ImageView) view.findViewById(R.id.play);
                mNumber = (TextView) view.findViewById(R.id.number);
            }
        }

        public AmimationAdapter(List<String> list) {
            this.mList = list;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(MainActivity.this)
                    .inflate(R.layout.item, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
//            if (position == 4) {
//                Glide.with(MainActivity.this)
//                        .load(R.drawable.test)
//                        .into(holder.img);
//            } else if (position % 2 == 0) {
            if (!isLoad) {
                Glide.with(MainActivity.this)
                        .load(R.drawable.img2)
                        .into(holder.img);
            } else if (position == 0) {
                Glide.with(MainActivity.this)
                        .load(R.drawable.item)
                        .into(holder.img);
            } else {
                Glide.with(MainActivity.this)
                        .load(R.drawable.img2)
                        .into(holder.img);
            }
//            } else {
//                Glide.with(MainActivity.this)
//                        .load(R.drawable.item)
//                        .into(holder.img);
//            }


            // Bounce animation ..........................
//            if (isLoad) {
//                setBounceAnimation(holder.itemView, position + 1);
//            }

            holder.mNumber.setText(mList.get(position) + "");

            holder.imgFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Favorite animation ..........................
                    setFavoriteAnimation(holder.imgBorder, holder.rlItem, holder.imgFavorite, holder.img);
                }
            });

            holder.rlItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    DetailFragment detailFragment = new DetailFragment();
                    Bundle bundl = new Bundle();
                    bundl.putInt("top", holder.itemView.getTop());
                    bundl.putInt("position", position);
                    detailFragment.setArguments(bundl);
                    getSupportFragmentManager().beginTransaction().add(R.id.framelayout, detailFragment).addToBackStack("test").commit();
                    setTranslate(holder.itemView, holder.itemView.getTop(), false);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }


    // Bounce animation ..........................
    private void setBounceAnimation(View targetView, int position) {

        ObjectAnimator animator = ObjectAnimator.ofFloat(targetView, "translationY", 0, 300 * (1 - position / 10.0f), 0);
        animator.setInterpolator(new EasingInterpolator(Ease.SWING));
        //animator.setStartDelay(100);
        animator.setDuration(1000);
        animator.start();
    }

    // Favorite animation ..........................
    private void setFavoriteAnimation(final View borderView, final View itemView, final View favoriteView, final ImageView imageView) {
        Animation animation = AnimationUtils.loadAnimation(getApplication(), R.anim.translate);
        itemView.startAnimation(animation);
        scaleFavoriteView(favoriteView);
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ResizeAnimation mResizeAnimation = new ResizeAnimation(itemView);
                mResizeAnimation.setDuration(300);
                mResizeAnimation.setStartParams(1, itemView.getHeight(), 1, itemView.getWidth());
                ColorMatrixColorFilter filter = new ColorMatrixColorFilter(new float[]{
                        0.5f, 0.5f, 0.5f, 0, 0,
                        0.5f, 0.5f, 0.5f, 0, 0,
                        0.5f, 0.5f, 0.5f, 0, 0,
                        0, 0, 0, 1, 0
                });
                imageView.getDrawable().setColorFilter(filter);
                itemView.startAnimation(mResizeAnimation);
                mResizeAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        borderView.setVisibility(View.VISIBLE);
                        scaleBorderView(borderView);
                        favoriteView.setBackgroundResource(R.drawable.favorite);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void scaleBorderView(View v) {
        Animation anim = AnimationUtils.loadAnimation(getApplication(), R.anim.scale_border);
        anim.setFillAfter(true);
        v.startAnimation(anim);
    }

    public void scaleFavoriteView(View v) {
        Animation anim = AnimationUtils.loadAnimation(getApplication(), R.anim.scale_favorite);
        v.startAnimation(anim);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    private void setTranslate(final View v, int top, boolean isFillAfter) {
        TranslateAnimation animate = new TranslateAnimation(v.getX(), v.getX(), 0, -top);
        animate.setDuration(500);
        animate.setFillAfter(isFillAfter);
        v.startAnimation(animate);
    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().popBackStackImmediate()) {
            getSupportFragmentManager().popBackStack();
        } else
            super.onBackPressed();
    }
}
