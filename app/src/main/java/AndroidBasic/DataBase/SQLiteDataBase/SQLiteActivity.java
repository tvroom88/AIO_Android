package AndroidBasic.DataBase.SQLiteDataBase;

import android.os.Bundle;
import android.widget.Toast;
import com.example.aio_android.BaseActivity;
import com.example.aio_android.databinding.DatabaseSqliteBinding;

/**
 * SQLite로 구현한 데이터 베이스 예제 (Student 정보 넣는 부분 구현)
 * (1) DatabaseHelper - insert, delete, update, load (CRUD) 부분 구현
 * (2) SQLiteActivity - SQLite 데이터베이스를 불러와서 사용.
 *
 * Student에서 major 부분은 spinner로 구현함
 * onDestory에 db내용 다 지워버리게 저장함
 */
public class SQLiteActivity extends BaseActivity {

    private DatabaseSqliteBinding binding;
    final String title = "SQLite 데이터 베이스";

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DatabaseSqliteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setToolbar(binding.layout.toolbar, binding.layout.toolbarImage, binding.layout.tooblarTitle, title);

        // 데이터 베이스
        dbHelper = new DatabaseHelper(this);

        // 데이터 추가
        binding.insertBtn.setOnClickListener(v -> {
            insertData();
        });

        // 데이터 지우기 (name 으로)
        binding.deleteBtn.setOnClickListener(v ->{
            delete(); 
        });

        // 데이터 업데이트 (name 으로)
        binding.updateBtn.setOnClickListener(v ->{
            upDate();
        });

        // 모든 데이터 불로오기
        binding.loadDataBtn.setOnClickListener(v -> {
            loadData();
        });
    }

    public void insertData() {
        String name = binding.dbNameEdit.getText().toString();
        String ageStr = binding.dbAgeEdit.getText().toString();
        int age = ageStr.equals("") ? 0 : Integer.parseInt(ageStr);
        String major = binding.dbMajorSpinner.getSelectedItem().toString();

        boolean flag = dbHelper.insert(name, age, major);
        if (flag)
            Toast.makeText(this, "데이터 추가 성공", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "데이터 추가 실패", Toast.LENGTH_SHORT).show();
    }

    public void delete(){
        String name = binding.dbNameEdit.getText().toString();
        int flag = dbHelper.delete(name);
        if (flag!=0)
            Toast.makeText(this, "데이터가 " + flag + "개 지워졌습니다", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "지워진 데이터가 없습니다", Toast.LENGTH_SHORT).show();
    }

    public void upDate(){
        String name = binding.dbNameEdit.getText().toString();
        String ageStr = binding.dbAgeEdit.getText().toString();
        int age = ageStr.equals("") ? 0 : Integer.parseInt(ageStr);
        String major = binding.dbMajorSpinner.getSelectedItem().toString();

        int flag = dbHelper.update(name, age, major);
        if (flag!=0)
            Toast.makeText(this, "데이터 " + flag  + "개 업데이트 했습니다.", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "해당하는 데어터가 없습니다.", Toast.LENGTH_SHORT).show();
    }

    public void loadData() {
        StringBuffer res = dbHelper.getAllData();
        binding.dbResText.setText(res.toString());
    }

    @Override
    protected void onDestroy() {
        dbHelper.deleteAll();
        dbHelper.close();
        super.onDestroy();
    }
}