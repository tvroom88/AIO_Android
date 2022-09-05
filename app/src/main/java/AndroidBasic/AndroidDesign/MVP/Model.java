package AndroidBasic.AndroidDesign.MVP;

import android.os.Handler;
import android.os.Looper;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//모델에는 Data들과 데이터들을 처리하는 역할들
public class Model implements Contract.Model{

    private int idx = 0;

    // 여기사 TextView에 보여줄 자료 관리
    private List<String> arrayList = Arrays.asList(
            "classA",
            "classB",
            "classC",
            "classD",
            "classE"
    );

    // 버튼을 클릭시 다음 클래스를 가져오는 부분. 1200의 딜래이 시간을 둠.
    // onFinished는 Presenter에 선언되어 있음.
    @Override
    public void getNextCourse(final OnFinishedListener listener) {
        new Handler(Looper.getMainLooper()).postDelayed(() -> listener.onFinished(getRandomString()), 400);
    }

    // 랜덤 번호 찍히는 부분
    private String getRandomString() {
        if (idx >= arrayList.size()) {
            idx = 0;
        }
        return arrayList.get(idx++);
    }
}
