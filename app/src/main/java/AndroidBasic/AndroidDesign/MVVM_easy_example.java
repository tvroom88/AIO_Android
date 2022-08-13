package AndroidBasic.AndroidDesign;

import android.app.Activity;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.aio_android.R;

public class MVVM_easy_example extends AppCompatActivity {
    private ViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mvvm_easy_example);

        viewModel = new ViewModel(MVVM_easy_example.this);
    }

    class Model {
        String sayHi(){
            return "Come from model";
        }
    }

    class ViewModel {
        private Activity activity;
        private Model model;
        private TextView textView;

        ViewModel(Activity activity){
            this.activity = activity;
            this.model = new Model();
            initView();
        }

        private void initView(){
            //View의 표현과 Model과의 상호작용
            textView = activity.findViewById(R.id.mvvm_textview1);
            textView.setOnClickListener(view -> {
                textView.setText(model.sayHi());
            });
        }
    }
}

