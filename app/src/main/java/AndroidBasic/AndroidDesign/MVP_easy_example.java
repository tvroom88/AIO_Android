package AndroidBasic.AndroidDesign;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.aio_android.R;

public class MVP_easy_example extends AppCompatActivity implements Presenter.View {

    private Presenter presenter;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mvp_easy_example);

        presenter = new PrsneterImpl(MVP_easy_example.this);

        textView = findViewById(R.id.mvp_textview1);
        textView.setOnClickListener(view -> {
            presenter.confirm();
        });
    }

    @Override
    public void setCustomText(String text) {
        textView.setText(text);
    }


    //Model 부분
    class Model {
        String sayHi(){
            return "Come from model";
        }
    }

    //PresenterImpl (Presenter의 구현체)
    class PrsneterImpl implements Presenter{
        private final Presenter.View view;
        private final Model model;

        PrsneterImpl(View view){
            this.view = view;
            this.model = new Model();
        }

        @Override
        public void confirm() {
            if(view != null){
                view.setCustomText(model.sayHi());
            }
        }
    }

}

//Presenter(단순 인터페이스)
interface Presenter {
    void confirm();

    // View 부분에서 가져올 부분.
    interface View{
        void setCustomText(String text);
    }
}