package com.example.aio_android;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.aio_android.databinding.CustomDialogBinding;

import java.util.List;

public class CustomDialog extends Dialog {

    private CustomDialogBinding binding;

    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    public CustomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CustomDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = CustomDialogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.cancelButton.setOnClickListener(v-> {
            dismiss();
        });
    }

    public void setTitle(String title){
        binding.customDialogTitle.setText(title);
    }

    public void addTexts(List<String> textList){

        for(int i=0; i<textList.size(); i++){
            TextView tv1 = new TextView(getContext());
            tv1.setText(textList.get(i));
            addTextView(tv1);
        }
    }

    public void addTextView(View view){
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params2.setMargins(0,dp(10), 0, dp(10));  // 왼쪽, 위, 오른쪽, 아래 순서입니다.
        view.setLayoutParams(params2);
        binding.linearLayout1.addView(view);

    }

    // 크기를 dp 단위로 바꿔주는 메소드
    private int dp(int val){
        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        return Math.round(val * dm.density);
    }



}



