package AndroidExampleModel;

import com.example.aio_android.BuildConfig;
import java.util.Arrays;
import java.util.List;

// 버전 정보다 Model을 따로 만들기 애매할경우 여기에 다 넣어줄 예정
public class MainActivity_Model {

    public final double version = 1.0;

    //Singleton Pattern 적용------------
    private MainActivity_Model() { }

    private static class SingletonHolder {
        public static final MainActivity_Model INSTANCE = new MainActivity_Model();
    }
    public static MainActivity_Model getInstance() {
        return MainActivity_Model.SingletonHolder.INSTANCE;
    }
    //Singleton Pattern 적용 완료------------

    List<String> helpContents = Arrays.asList(
            "AIO Android는 제 블로그에 정리한 안드로이드 기초 지식을 구현안 안드로이드 예제 앱입니다.",
            "1. 안드로이드 기초편 - 기술면접에 나올수 있는 기초 지식을 위주로 나열된 예제입니다.",
            "2. 안드로이드 심화편 - 곧 추가 시킬 예정입니다.");

    public List<String> getContentList() {
        return helpContents;
    }


    List<String> infoContents = Arrays.asList(
            "만든이: Jaehong Lee" + '\n' +
                    "버전: " + (double) BuildConfig.VERSION_CODE + '\n' +
                    "참고자료 : 블로그 정리 자료 " + '\n' +
                    "(아래 버튼으로 바로 이동할수 있습니다)"
    );

    public List<String> getInfoContentsList() {
        return infoContents;
    }
}
