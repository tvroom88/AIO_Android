package AndroidBasic.AndroidDesign.MVVM;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MVVM_ViewModel extends ViewModel {

    private MVVM_Model model;
    private MutableLiveData<String> curTxt;

    public MVVM_ViewModel(){
        this.model = new MVVM_Model();
    }

    // model에 있는 text 데이터 업데이트
    public void setCurrentTxt(String str) {
        this.model.setText(str);
    }

    // model text가 업데이트 될경우 불러오기
    public MutableLiveData<String> getCurrentTxt() {
        if (curTxt == null) {
            curTxt = new MutableLiveData<>();
        }
        curTxt.setValue(model.getText());
        return curTxt;
    }

}
