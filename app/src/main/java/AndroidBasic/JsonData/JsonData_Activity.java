package AndroidBasic.JsonData;

import android.os.Build;
import android.os.Bundle;
import com.example.aio_android.BaseActivity;
import com.example.aio_android.databinding.JsonDataBinding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Json Data와 객체를 처리하는 예제
 *  (1) Gson 사용
 *  (2) a. JSONObject -> Object, b. JSONArray -> Object List, c. Object -> JSONObject, d. Object List -> JSONArray
 */
public class JsonData_Activity extends BaseActivity {

    int count = 1;
    private JsonDataBinding binding;
    final String title = "Json데이터 처리 예제";
    String jsonString = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = JsonDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setToolbar(binding.layout.toolbar, binding.layout.toolbarImage, binding.layout.tooblarTitle, title);


        binding.jsonBtn1.setOnClickListener(v-> {
            convertObjectToJSON();
        });

        binding.jsonBtn2.setOnClickListener(v -> {
            try {
                convertJsonToObject();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });

        binding.jsonBtn3.setOnClickListener(v -> {
            convertMultipleObjectToJson();
        });

        binding.jsonBtn4.setOnClickListener(v -> {
            try {
                convertJsonToObjectList();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });
    }


    // Java 객체를 JSON 객체로 변환
    public void convertObjectToJSON() {
        String name = binding.jsonEdit1.getText().toString();
        String email = binding.jsonEdit2.getText().toString();
        Employee employee = new Employee(count, name, email);

        binding.resultText1.setText(employee.toString());

        Gson gson = new Gson();
        jsonString = gson.toJson(employee);
        binding.resultText2.setText(jsonString);
    }

    // JSON 객체를 Java 객체로 변환
    public void convertJsonToObject() throws JSONException {
        String name = binding.jsonEdit1.getText().toString();
        String email = binding.jsonEdit2.getText().toString();
        JSONObject josnOb = new JSONObject();
        josnOb.put("id", count);
        josnOb.put("name", name);
        josnOb.put("email", email);

        jsonString = josnOb.toString();
        binding.resultText1.setText(jsonString);

        Gson gson = new Gson();
        Employee employee = gson.fromJson(jsonString, Employee.class);
        binding.resultText2.setText(employee.toString());
    }

    // Java List를 JSON으로 변환
    public void convertMultipleObjectToJson(){
        StringBuilder sb = new StringBuilder();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Employee employee1 = new Employee(count++, "Michael", "123@naver.com");
        Employee employee2 = new Employee(count++, "Blamire", "abc@google.com");

        String name = binding.jsonEdit1.getText().toString();
        String email = binding.jsonEdit2.getText().toString();
        Employee employee3 = new Employee(count++, name, email);

        List<Employee> employees = Arrays.asList(employee1, employee2, employee3);

        for(Employee employee : employees)
            sb.append(employee.toString());

        binding.resultText1.setText(sb.toString());

        jsonString = gson.toJson(employees);
        binding.resultText2.setText(jsonString);

    }

    // Json Array를 Object List로
    public void convertJsonToObjectList() throws JSONException {

        // --- JsonArray 만드는 부분 ---
        String name = binding.jsonEdit1.getText().toString();
        String email = binding.jsonEdit2.getText().toString();
        JSONArray jsonArray = new JSONArray();
        JSONObject josnOb1 = new JSONObject();
        josnOb1.put("id", count++);
        josnOb1.put("name", "Michael");
        josnOb1.put("email", "123@naver.com");

        JSONObject josnOb2 = new JSONObject();
        josnOb2.put("id", count++);
        josnOb2.put("name", "Blamire");
        josnOb2.put("email", "abc@google.com");

        JSONObject josnOb3 = new JSONObject();
        josnOb3.put("id", count++);
        josnOb3.put("name", name);
        josnOb3.put("email", email);

        jsonArray.put(0, josnOb1);
        jsonArray.put(1, josnOb2);
        jsonArray.put(2, josnOb3);

        jsonString = jsonArray.toString();
        binding.resultText1.setText(jsonString);

        //  convert JsonArray To Object List
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type listType = new TypeToken<ArrayList<Employee>>(){}.getType();
        List<Employee> list = gson.fromJson(jsonString, listType);

        StringBuilder sb = new StringBuilder();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            list.forEach((n) -> sb.append(n.toString()));
        }

        binding.resultText2.setText(sb.toString());
    }
}
