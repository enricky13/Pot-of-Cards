package com.example.ygocardsearch.splashFragment;


import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.ygocardsearch.FragmentToFragment;
import com.example.ygocardsearch.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplashPage extends Fragment {
    private ImageView splashImage;
    private CountDownTimer countDownTimerForLaunch;
    private FragmentToFragment fragmentToFragment;

    public SplashPage() {
        // Required empty public constructor
    }

    public static SplashPage newInstance(){
        SplashPage splashPage = new SplashPage();
        return splashPage;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        splashImage = view.findViewById(R.id.splash_image);
        countDownTimerForLaunch = new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                fragmentToFragment.startAppFragment();
            }
        };//Dont forget to .start to countdown
        Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.flip_animation);
        splashImage.startAnimation(animation);

        countDownTimerForLaunch.start();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentToFragment = (FragmentToFragment) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentToFragment = null;
    }
}
