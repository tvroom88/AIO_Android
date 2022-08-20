package AndroidBasic.Handler;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.example.aio_android.BaseActivity;
import com.example.aio_android.R;
import com.example.aio_android.databinding.HandlerBinding;

import java.util.ArrayList;

/**
 * Handler 예제
 * (1) 멀티 스레드 사용
 * (2) Counter 예제 추가
 * (3)
 */
public class HandlerActivity extends BaseActivity {

    HandlerBinding binding;
    final String title = "핸들러 예제";

    //Counter 숫자를 위한 변수
    int value = 0;

    // Animation Index 넘버 변수
    int animatonIdx = 0;

    Handler handler = new Handler(Looper.getMainLooper());

    //Thread
    BackgroundThread thread;
    AnimThread aniThread;

    // thread를 하나만 실행시켜주기위한 boolean
    boolean threadExist = false;
    boolean aniThreadExist = false;

    //Animation의 List
    ArrayList<Drawable> drawableList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = HandlerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Toolbar 세팅
        setToolbar(binding.layout.toolbar, binding.layout.toolbarImage, binding.layout.tooblarTitle, title);

        // Animation의 List에 이미지들 체운 부분
        fillImage(drawableList);

        // 첫번째 Counter 부분 세팅
        binding.handlerText1.setText("value 값 : " + value);

        binding.handlerButton1.setOnClickListener(v -> {

            if(!threadExist){
                thread = new BackgroundThread(true);
                thread.start();
                threadExist = true;
            }
        });

        binding.handlerButton2.setOnClickListener(v -> {
            if(threadExist){
                thread.setFlag(false);
                threadExist = false;
            }
        });

        binding.handlerButton3.setOnClickListener(v -> {
            value = 0;
            binding.handlerText1.setText("value 값 : " + value);

        });
        // -- 첫번째 Counter 부분 세팅 완료 --

        // 두번째 animation 예제 부분
        binding.handlerImage1.setImageDrawable(drawableList.get(0));

        binding.handlerButton4.setOnClickListener(v -> {
            if(!aniThreadExist){
                aniThread = new AnimThread(true);
                aniThread.start();
                aniThreadExist = true;
            }

        });

        binding.handlerButton5.setOnClickListener(v -> {
            if(aniThreadExist){
                aniThread.setFlag(false);
                aniThreadExist = false;
            }
        });

        binding.handlerButton6.setOnClickListener(v -> {
            animatonIdx = 0;
            final Drawable drawable = drawableList.get(animatonIdx);
            binding.handlerImage1.setImageDrawable(drawable);
        });
        // -- 두번째 animation 예제 부분 완료 --
    }

    // Counter를 위한 Thread
    class BackgroundThread extends Thread {
        boolean flag;
        BackgroundThread(boolean flag){
            this.flag = flag;
        }
        public void run() {

            for (int i = 0; i < 100; i++) {
                if(!flag) break;
                handler.post(() -> binding.handlerText1.setText("value 값 : " + value));
                value += 1;

                try {
                    Thread.sleep(1000);
                } catch(Exception ignored) {}

            }
        }

        public void setFlag(boolean flag){
            this.flag = flag;
        }
    }

    // Animation 예제를 위한 Thread
    class AnimThread extends Thread {
        boolean flag;
        AnimThread(boolean flag){
            this.flag = flag;
        }
        public void run() {

            for (int i = 0; i < 300; i++) {
                if(!flag) break;
                final Drawable drawable = drawableList.get(animatonIdx);
                handler.post(() -> binding.handlerImage1.setImageDrawable(drawable));
                animatonIdx += 1;
                if (animatonIdx >= drawableList.size()) {
                    animatonIdx = 0;
                }

                try {
                    Thread.sleep(150);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        public void setFlag(boolean flag){
            this.flag = flag;
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void fillImage(ArrayList<Drawable> drawableList){
        Resources res = getResources();
        drawableList.add(res.getDrawable(R.drawable.arrow1, null));
        drawableList.add(res.getDrawable(R.drawable.arrow2, null));
        drawableList.add(res.getDrawable(R.drawable.arrow3, null));
        drawableList.add(res.getDrawable(R.drawable.arrow4, null));
        drawableList.add(res.getDrawable(R.drawable.arrow5, null));
        drawableList.add(res.getDrawable(R.drawable.arrow6, null));
        drawableList.add(res.getDrawable(R.drawable.arrow7, null));
    }
}