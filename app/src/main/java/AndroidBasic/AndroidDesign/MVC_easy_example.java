package AndroidBasic.AndroidDesign;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.aio_android.R;

public class MVC_easy_example extends AppCompatActivity {

    private Model model;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //View 부분
        setContentView(R.layout.mvc_easy_example);
        textView = findViewById(R.id.mvc_textview1);

        //Controller 부분
        model = new Model();
        textView.setOnClickListener(view -> {
            //모델에서 가지고온 sayHi부분
            textView.setText(model.sayHi());
        });
    }

    //Model 부분
    class Model {
        String sayHi(){
            return "Come from model";
        }
    }
}
