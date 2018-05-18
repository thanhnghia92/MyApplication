package recyclerview.animators;

/**
 * Copyright (C) 2018 Wasabeef
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.animation.ObjectAnimator;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.animation.Interpolator;

import com.daasuu.ei.Ease;
import com.daasuu.ei.EasingInterpolator;


public class SlideInDownAnimator extends BaseItemAnimator {

  public SlideInDownAnimator() {

  }

  public SlideInDownAnimator(Interpolator interpolator) {
    mInterpolator = interpolator;
  }

  @Override
  protected void animateRemoveImpl(final RecyclerView.ViewHolder holder) {
    Log.i("dcm","animateRemoveImpl");
    ViewCompat.animate(holder.itemView)
        .translationY(-holder.itemView.getHeight())
        .alpha(0)
        .setDuration(getRemoveDuration())
        .setInterpolator(mInterpolator)
        .setListener(new DefaultRemoveVpaListener(holder))
        .setStartDelay(getRemoveDelay(holder))
        .start();
  }

  @Override
  protected void preAnimateAddImpl(RecyclerView.ViewHolder holder) {
    Log.i("dcm","preAnimateAddImpl");
    holder.itemView.setTranslationY(-holder.itemView.getHeight());
  }

  @Override
  protected void preAnimateRemoveImpl(RecyclerView.ViewHolder holder) {
    Log.i("dcm","preAnimateRemoveImpl");
    super.preAnimateRemoveImpl(holder);
  }

  @Override
  protected void animateAddImpl(final RecyclerView.ViewHolder holder) {
    Log.i("dcm","animateAddImpl");
    ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(holder.itemView, "translationY", 0, 60, 0);
    objectAnimator.setInterpolator(new EasingInterpolator(Ease.SWING));
    //animator.setStartDelay(100);
    objectAnimator.setDuration(getAddDuration());
    objectAnimator.start();
//    ViewCompat.animate(holder.itemView)
//        .translationY(0)
//        .alpha(1)
//        .setDuration(getAddDuration())
//        .setInterpolator(mInterpolator)
//        .setListener(new DefaultAddVpaListener(holder))
//        .setStartDelay(getAddDelay(holder))
//        .start();
  }
}
