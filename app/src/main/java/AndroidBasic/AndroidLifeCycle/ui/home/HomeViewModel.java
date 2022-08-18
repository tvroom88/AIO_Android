package AndroidBasic.AndroidLifeCycle.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String> mText2;


    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Activity LifeCycle Image");

        mText2 = new MutableLiveData<>();
        mText2.setValue("LifeCycle Log Screenshot");
    }

    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<String> getText2() {
        return mText2;
    }

}