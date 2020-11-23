package com.mercury.demo.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.Log;
import android.util.Property;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mercury.demo.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.transition.Transition;
import androidx.transition.TransitionValues;

public class ChangeTextTransition extends Transition {

    /**
     * 自定义 transition
     * 以下3个方法就是记录起始的值 以及创建动画
     * 定义你关心的属性值；
     * 官方建议属性定义的规则为：package_name:transition_class:property_name.
     */


    private static String PROTEXT_TEXT = "com.mercury.demo:changeText:text";
    private static String PROTEXT_TEXT_COLOR = "com.mercury.demo:changeText:color";
    private static String PROTEXT_TEXT_SIZE = "com.mercury.demo:changeText:size";
    private static String PROTEXT_TEXT_LEVEL =  "com.mercury.demo:changeText:level";



    @Override
    public void captureStartValues(@NonNull TransitionValues transitionValues) {
             captureValues(transitionValues);
    }

    @Override
    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    private void captureValues(TransitionValues transitionValues){
        if(transitionValues == null || !(transitionValues.view instanceof TextView)) return;
        TextView view = (TextView) transitionValues.view;
        transitionValues.values.put(PROTEXT_TEXT,view.getText());
        transitionValues.values.put(PROTEXT_TEXT_COLOR,view.getCurrentTextColor());
        transitionValues.values.put(PROTEXT_TEXT_SIZE,view.getTextSize());
        transitionValues.values.put(PROTEXT_TEXT_LEVEL,view.getTag(R.id.type_face_level));

    }

    @Nullable
    @Override
    public Animator createAnimator(@NonNull ViewGroup sceneRoot, @Nullable TransitionValues startValues, @Nullable TransitionValues endValues) {
        if(startValues == null || endValues == null){
            return null;
        }

        if(!(endValues.view instanceof  TextView)){
            return super.createAnimator(sceneRoot, startValues, endValues);
        }

        TextView endView = (TextView) endValues.view;
        CharSequence startText = (CharSequence) startValues.values.get(PROTEXT_TEXT);
        CharSequence endText =  (CharSequence) endValues.values.get(PROTEXT_TEXT);
        int startTextColor = (int) startValues.values.get(PROTEXT_TEXT_COLOR);
        int endTextColor = (int) endValues.values.get(PROTEXT_TEXT_COLOR);
        float startTextSize = (float) startValues.values.get(PROTEXT_TEXT_SIZE);
        float endTextSize = (float) endValues.values.get(PROTEXT_TEXT_SIZE);
        Object startTag = startValues.values.get(PROTEXT_TEXT_LEVEL);
        int startTextLevel = 1;
        if(startTag instanceof  Integer){
            startTextLevel = (int) startTag;
        }
        Object endTag = endValues.values.get(PROTEXT_TEXT_LEVEL);
        int endTextLevel = 1;
        if (startTag instanceof Integer) {
            endTextLevel = (int) endTag;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        if (!TextUtils.equals(startText, endText)) {
            animatorSet.playTogether(createTextChangeAnimator(endView, startText, endText));
        }
        if (startTextColor != endTextColor) {
            animatorSet.playTogether(createColorChangeAnimator(endView, startTextColor, endTextColor));
        }
        if (startTextSize != endTextSize) {
            animatorSet.playTogether(createSizeChangeAnimator(endView, startTextSize, endTextSize));
        }
        if (startTextLevel != endTextLevel) {
            animatorSet.playTogether(createTypefaceChangeAnimator(endView, startTextLevel, endTextLevel));
        }
        return animatorSet;
}

    private Animator createTextChangeAnimator(final TextView endView, final CharSequence startText, final CharSequence endText) {
        ValueAnimator animator = ValueAnimator.ofFloat(0, 1f);
        animator.setDuration(300);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                if (animatedValue <= 0.5f) {
                    endView.setText(startText);
                    float ratio = (0.5f - animatedValue) / 0.5f;
                    endView.setAlpha(ratio);
                } else {
                    endView.setText(endText);
                    float ratio = (animatedValue - 0.5f) / 0.5f;
                    endView.setAlpha(ratio);
                }
            }
        });
        return animator;
    }


    private Animator createColorChangeAnimator(final TextView endView, final int startTextColor, final int endTextColor) {
        ObjectAnimator animator = ObjectAnimator.ofArgb(endView, new TextColorProperty(), startTextColor, endTextColor);
        animator.setDuration(300);
        return animator;
    }

    private Animator createSizeChangeAnimator(final TextView endView, final float startTextSize, final float endTextSize) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(endView, new TextSizeProperty(), startTextSize, endTextSize);
        animator.setDuration(300);
        Log.i("xiaweizi::", "createSizeChangeAnimator: ");
        return animator;
    }

    private Animator createTypefaceChangeAnimator(final TextView endView, final int startTextLevel, final int endTextSizeLevel) {
        ObjectAnimator animator = ObjectAnimator.ofInt(endView, new TextTypeFaceProperty(), startTextLevel, endTextSizeLevel);
        animator.setDuration(300);
        return animator;
    }



        private class TextColorProperty extends Property<TextView,Integer>{

            /**
             * A constructor that takes an identifying name and {@link #getType() type} for the property.
             *

             */
            public TextColorProperty() {
                super(Integer.class, "textColor");
            }

            @Override
            public void set(TextView object, Integer value) {
                object.setTextColor(value);
            }

            @Override
            public Integer get(TextView object) {
                return object.getCurrentTextColor();
            }
        }

        private class TextSizeProperty extends Property<TextView,Float>{

            /**
             * A constructor that takes an identifying name and {@link #getType() type} for the property.
             *

             */
            public TextSizeProperty() {
                super(Float.class, "textSzie");
            }

            @Override
            public Float get(TextView object) {
                return object.getTextSize();
            }

            @Override
            public void set(TextView object, Float value) {
                object.setTextSize(TypedValue.COMPLEX_UNIT_PX,value);
            }
        }


        private class TextTypeFaceProperty extends Property<TextView,Integer>{

            /**
             * A constructor that takes an identifying name and {@link #getType() type} for the property.
             *

             */
            public TextTypeFaceProperty() {
                super(Integer.class, "typeface");
            }

            @Override
            public void set(TextView object, Integer value) {
                object.setTag(R.id.type_face_level,value);
                if(value == 1){
                    object.setTypeface(Typeface.create("sans-serif-thin",Typeface.NORMAL));
                }else if(value == 2){
                    object.setTypeface(Typeface.create("sans-serif-light",Typeface.NORMAL));
                } else if (value == 3) {
                    object.setTypeface(Typeface.create("sans-serif-regular", Typeface.NORMAL));
                } else if (value == 4) {
                    object.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
                } else if (value == 5) {
                    object.setTypeface(Typeface.create("sans-serif-black", Typeface.NORMAL));
                }
            }

            @Override
            public Integer get(TextView object) {
                return (Integer) object.getTag(R.id.type_face_level);
            }
        }
}
