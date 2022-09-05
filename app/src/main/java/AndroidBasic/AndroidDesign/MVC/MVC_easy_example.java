package AndroidBasic.AndroidDesign.MVC;

import android.os.Bundle;
import android.view.View;
import com.aio.aio_android.BaseActivity;
import com.example.aio_android.R;
import com.example.aio_android.databinding.MvcEasyExampleBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *  MVC pattern 예제
 */
public class MVC_easy_example extends BaseActivity implements Observer, View.OnClickListener {

    private MvcEasyExampleBinding mBinding;
    final String TITLE = "MVC pattern";

    // creating object of Model class
    private Model myModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = MvcEasyExampleBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        setToolbar(mBinding.layout.toolbar, mBinding.layout.toolbarImage, mBinding.layout.tooblarTitle, TITLE);

        //View 부분
        // creating relationship between the observable Model and the observer Activity
        myModel = new Model();
        myModel.addObserver(this);

        // transfer the control to Onclick() method when a button is clicked by passing zxargument "this"
        mBinding.button.setOnClickListener(this);
        mBinding.button2.setOnClickListener(this);
        mBinding.button3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.button:
                myModel.setValueAtIndex(0);
                break;

            case R.id.button2:
                myModel.setValueAtIndex(1);
                break;

            case R.id.button3:
                myModel.setValueAtIndex(2);
                break;
        }
    }

    // function to update the view after the values are modified by the model
    // Model 부분에서  setChanged(); notifyObservers(); 를 사용해 서 데이터 업데이트 되고
    // 이부분에서 자동으로 데이터가 바뀔경우 업데이트 되는것 같음.
    @Override
    public void update(Observable o, Object arg) {
        // changing text of the buttons
        // according to updated values
        mBinding.button.setText("Count: "+  myModel.getValueAtIndex(0));
        mBinding.button2.setText("Count: "+ myModel.getValueAtIndex(1));
        mBinding.button3.setText("Count: "+ myModel.getValueAtIndex(2));
    }


    // Model 부분 따로 만들어주기 귀찮아서 여기에 만듬.
    public class Model extends Observable {

        // declaring a list of integer
        private List<Integer> list;

        // constructor to initialize the list
        public Model(){
            // reserving the space for list elements
            list = new ArrayList<Integer>(3);

            // adding elements into the list
            list.add(0);
            list.add(0);
            list.add(0);
        }

        // defining getter and setter functions

        // function to return appropriate count
        // value at correct index
        public int getValueAtIndex(final int the_index) throws IndexOutOfBoundsException{
            return list.get(the_index);
        }

        // function to make changes in the activity button's
        // count value when user touch it
        public void setValueAtIndex(final int the_index) throws IndexOutOfBoundsException{
            list.set(the_index, list.get(the_index) + 1);
            setChanged();
            notifyObservers();
        }

    }
}
