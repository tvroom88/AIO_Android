package AndroidBasic.DiverseLayout;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import com.example.aio_android.R;
import com.example.aio_android.databinding.DivlayoutFragmentBBinding;


public class LayoutFragmentB extends Fragment {

    private DivlayoutFragmentBBinding binding;
    LinearLayout mainLinearLayout;
    Context mContext;
    int imageIndex = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DivlayoutFragmentBBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mContext = getContext();

        // 메인 LinearLayout
        mainLinearLayout = binding.LinearLayout1;

        // 1. LinearLayout 추가
        AddLinearLayoutExample();

        // 2. ConstraintLayout 추가
        AddConstraintLayout();

        // 3. TableLayout 추가
        AddTableLayout();

        // 4.
        AddFrameLayout();

        return root;
    }

    // 1. LinearLayout 예제
    private void AddLinearLayoutExample(){
        LinearLayout subLinearLayout = new LinearLayout(mContext);

        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dp(80)
        );
        params1.setMargins(0,dp(10),0,0);  // 왼쪽, 위, 오른쪽, 아래 순서입니다.

        subLinearLayout.setLayoutParams(params1);
        subLinearLayout.setOrientation(LinearLayout.VERTICAL);
        subLinearLayout.setBackgroundColor(Color.parseColor("#FF9966"));

        TextView tv1 = new TextView(mContext);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        tv1.setLayoutParams(params2);
        tv1.setText("LinearLayout 부분");
        tv1.setGravity(Gravity.CENTER);

        subLinearLayout.addView(tv1);
        mainLinearLayout.addView(subLinearLayout);
    }

    // 2. ConstraintLayout 예제
    private void AddConstraintLayout() {
        ConstraintLayout subConstraintLayout = new ConstraintLayout(mContext);
        ConstraintLayout.LayoutParams params1 = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT, dp(80));
        params1.setMargins(0, dp(20), 0, 0);  // 왼쪽, 위, 오른쪽, 아래 순서입니다.
        subConstraintLayout.setLayoutParams(params1);
        subConstraintLayout.setBackgroundColor(Color.parseColor("#66FFCC"));

        TextView tv1 = new TextView(mContext);
        ConstraintLayout.LayoutParams params2 = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        params2.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        params2.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        params2.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        tv1.setLayoutParams(params2);
        tv1.setText("ConstraintLayout 부분 View를 겹칠수도 있습니다.");

        subConstraintLayout.addView(tv1);

        ConstraintLayout.LayoutParams params3 = new ConstraintLayout.LayoutParams(
                dp(50), dp(50));
        params3.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        params3.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        params3.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        params3.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        params3.setMargins(0, dp(10),  dp(30), 0);  // 왼쪽, 위, 오른쪽, 아래 순서입니다.

        TextView tv2 = new TextView(mContext);
        tv2.setLayoutParams(params3);
        tv2.setBackgroundResource(R.drawable.rectangle_border);
        tv2.setText("A");
        tv2.setGravity(Gravity.CENTER);

        ConstraintLayout.LayoutParams params4 = new ConstraintLayout.LayoutParams(
                dp(50), dp(50));
        params4.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        params4.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        params4.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        params4.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        params4.setMargins(0, dp(10),  dp(30), 0);  // 왼쪽, 위, 오른쪽, 아래 순서입니다.

        params4.setMargins(dp(30), dp(10),  0, 0);  // 왼쪽, 위, 오른쪽, 아래 순서입니다.
        TextView tv3 = new TextView(mContext);
        tv3.setLayoutParams(params4);
        tv3.setBackgroundResource(R.drawable.rectangle_border);
        tv3.setText("B");
        tv3.setGravity(Gravity.CENTER);

        subConstraintLayout.addView(tv2);
        subConstraintLayout.addView(tv3);

        mainLinearLayout.addView(subConstraintLayout);
    }


    // 3. TableLayout 부분
    private void AddTableLayout() {
        TableLayout subTableLayout = new TableLayout(mContext);
        TableLayout.LayoutParams params1 = new TableLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        params1.setMargins(0, dp(20), 0, 0);  // 왼쪽, 위, 오른쪽, 아래 순서입니다.
        subTableLayout.setLayoutParams(params1);
        subTableLayout.setBackgroundColor(Color.parseColor("#99FF00"));
        subTableLayout.setStretchAllColumns(true);

        TextView tv1 = new TextView(mContext);
        TableLayout.LayoutParams params2 = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
        params2.setMargins(0, dp(10), 0, dp(10));  // 왼쪽, 위, 오른쪽, 아래 순서입니다.

        tv1.setLayoutParams(params2);
        tv1.setText("TableLayout 부분");
        tv1.setGravity(Gravity.CENTER);
        subTableLayout.addView(tv1);

        TableRow.LayoutParams params3 = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);

        TableRow.LayoutParams params4 = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        params4.setMargins(dp(10), 0, dp(10), dp(10));  // 왼쪽, 위, 오른쪽, 아래 순서입니다.

        int count = 1;
        for(int i=0; i<2; i++){
            TableRow tableRow = new TableRow(mContext);  //TableRow 생성
            tableRow.setLayoutParams(params3);
            for(int j=0; j<3; j++){
                // TableRow에 버튼 추가
                Button btn = new Button(mContext);
                btn.setLayoutParams(params4);
                btn.setBackgroundResource(R.drawable.rectangle_border);
                btn.setText(String.valueOf(count++));
                tableRow.addView(btn);
            }
            subTableLayout.addView(tableRow);
        }
        mainLinearLayout.addView(subTableLayout);
    }

    //4. FrameLayout 예제
    private void AddFrameLayout(){
        FrameLayout subLinearLayout = new FrameLayout(mContext);

        FrameLayout.LayoutParams params1 = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                dp(240)
        );
        params1.setMargins(0,dp(10),0,0);  // 왼쪽, 위, 오른쪽, 아래 순서입니다.

        subLinearLayout.setLayoutParams(params1);
        subLinearLayout.setBackgroundColor(Color.parseColor("#CC00FF"));

        TextView tv1 = new TextView(mContext);
        FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        );
        params2.setMargins(0,dp(10),0,dp(10));  // 왼쪽, 위, 오른쪽, 아래 순서입니다.
        params2.gravity = Gravity.CENTER_HORIZONTAL;

        tv1.setLayoutParams(params2);
        tv1.setText("FrameLayout 부분");

        ImageView iv1 = new ImageView(mContext);
        ImageView iv2 = new ImageView(mContext);
        ImageView iv3 = new ImageView(mContext);
        FrameLayout.LayoutParams params3 = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        );
        params3.gravity = Gravity.CENTER;
        iv1.setLayoutParams(params3);
        iv2.setLayoutParams(params3);
        iv3.setLayoutParams(params3);

        iv1.setForegroundGravity(Gravity.CENTER);
        iv2.setForegroundGravity(Gravity.CENTER);
        iv3.setForegroundGravity(Gravity.CENTER);

        iv1.setImageResource(R.drawable.one_icon);
        iv2.setImageResource(R.drawable.two_icon);
        iv3.setImageResource(R.drawable.three_icon);

        iv2.setVisibility(View.INVISIBLE);
        iv3.setVisibility(View.INVISIBLE);

        FrameLayout.LayoutParams params4 = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        );
        params4.gravity = Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL;
        Button btn = new Button(mContext);
        btn.setLayoutParams(params4);
        btn.setText("이미지 바꾸기");

        subLinearLayout.addView(tv1);
        subLinearLayout.addView(iv1);
        subLinearLayout.addView(iv2);
        subLinearLayout.addView(iv3);
        subLinearLayout.addView(btn);

        btn.setOnClickListener(v->{
            changeImage(iv1,iv2,iv3);
        });

        mainLinearLayout.addView(subLinearLayout);
    }

    // 크기를 dp 단위로 바꿔주는 메소드
    private int dp(int val){
        DisplayMetrics dm = getResources().getDisplayMetrics();
        return Math.round(val * dm.density);
    }

    // 이미지 바꿔주는 메소드
    private void changeImage(ImageView iv1, ImageView iv2, ImageView iv3) {
        imageIndex++;
        if(imageIndex > 2){
            imageIndex = 0;
        }
        if (imageIndex == 0) {
            iv1.setVisibility(View.VISIBLE);
            iv2.setVisibility(View.INVISIBLE);
            iv3.setVisibility(View.INVISIBLE);
        } else if (imageIndex == 1) {
            iv1.setVisibility(View.INVISIBLE);
            iv2.setVisibility(View.VISIBLE);
            iv3.setVisibility(View.INVISIBLE);
        } else if (imageIndex == 2){
            iv1.setVisibility(View.INVISIBLE);
            iv2.setVisibility(View.INVISIBLE);
            iv3.setVisibility(View.VISIBLE);
        }
    }
}