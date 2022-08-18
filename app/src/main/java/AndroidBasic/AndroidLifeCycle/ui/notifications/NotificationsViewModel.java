package AndroidBasic.AndroidLifeCycle.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotificationsViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String> mText2;

    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Main Activity에서 Sub Activity 호출했을때 라이프사이클");

        mText2 = new MutableLiveData<>();
        mText2.setValue("[Main] onPause()\n" +
                "[Sub] onCreate()\n" +
                "[Sub] onStart()\n" +
                "[Sub] onResume()\n" +
                "[Main] onStop()\n" +
                "…\n" +
                "[Sub] onPause()\n" +
                "[Main] onRestart()\n" +
                "[Main] onStart()\n" +
                "[Main] onResume()\n" +
                "[Sub] onStop()\n" +
                "[Sub] onDestroy()\n");

    }

    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<String> getText2() {
        return mText2;
    }
}