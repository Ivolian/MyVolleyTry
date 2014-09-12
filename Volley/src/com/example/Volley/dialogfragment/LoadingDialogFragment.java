package com.example.Volley.dialogfragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.*;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.Volley.R;


public class LoadingDialogFragment extends DialogFragment {

    private String message = "加载中...";

    public LoadingDialogFragment() {

    }

    public LoadingDialogFragment(String message) {

        this.message = message;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.loading_dialog);
        setCancelable(false);
    }

    @Override
    public void onStart() {

        float dialogMargin = 240.5f;

        Window window = getDialog().getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        window.setLayout(getScreenWidthPx() - (int) dpToPx(dialogMargin), layoutParams.height);

        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.loading_dialogfragment, container, false);

        ImageView ivSpinner = (ImageView) rootView.findViewById(R.id.ivSpinner);
        Animation loadingAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.loading);
        ivSpinner.startAnimation(loadingAnimation);

        TextView tvMessage = (TextView) rootView.findViewById(R.id.tvMessage);
        tvMessage.setText(message);

        return rootView;
    }

    // ================== 私有方法 ==================

    private float dpToPx(float dp) {

        DisplayMetrics metrics = getActivity().getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
    }

    private int getScreenWidthPx() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

}