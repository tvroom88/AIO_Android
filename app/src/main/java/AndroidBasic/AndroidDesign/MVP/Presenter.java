package AndroidBasic.AndroidDesign.MVP;

// Presenter에서는 View와 Model들을 다 불러와 주는듯.
public class Presenter implements Contract.Presenter, Contract.Model.OnFinishedListener{
    // creating object of View Interface
    private Contract.View mainView;

    // creating object of Model Interface
    private Contract.Model model;

    // instantiating the objects of View and Model Interface
    public Presenter(Contract.View mainView, Contract.Model model) {
        this.mainView = mainView;
        this.model = model;
    }

    // 버튼 클릭시 취해지는 행동.
    // Model에서 정의된 getNextCourse에서는 onFinished를 써서 nextCourse를 불러옴.
    @Override
    public void onButtonClick() {
        // 버튼을 처음 눌렀을때는 progress를 보여준다.
        if (mainView != null) {
            mainView.showProgress();
        }
        //일정시간 이후 course이름이 지정되고, progress는 숨겨진다.
        model.getNextCourse(this);
    }

    // mainView가 가지고 있는 setString과 hideProgress를 사용.
    @Override
    public void onFinished(String string) {
        if (mainView != null) {
            mainView.setString(string);
            mainView.hideProgress();
        }
    }

    // mainView와 연결을 끊는듯.
    @Override
    public void onDestroy() {
        mainView = null;
    }
}
