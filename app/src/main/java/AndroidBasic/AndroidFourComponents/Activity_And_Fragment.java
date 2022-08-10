package AndroidBasic.AndroidFourComponents;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.aio_android.R;

/**
 * Fragment를 추가시키는 방법은 크게 2가지이다.
 * (1) 액티비티의 xml 파일에 바로 추가 시키는 방법 -> activity_and_fragment.xml 확인
 * (2) 또는 프로그래밍 방식으로 프래그먼트를 기존의 ViewGroup에 추가합니다.
 */

public class Activity_And_Fragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_and_fragment);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        FragmentB fragment = new FragmentB();
        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

    }
}