package AndroidBasic.DataBase.RoomDataBase;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.example.aio_android.BaseActivity;
import com.example.aio_android.databinding.DatabaseRoomBinding;

import java.util.List;


/**
 * Room 라이브러리로 구현한 데이터 베이스 예제 (Student 정보 넣는 부분 구현)
 * (1) Entity (Student.java) : 데이터베이스의 Table을 표현합니다
 * (2) DAO (StudentDao) : 데이터베이스 접근에 사용되는 추상 메소드들을 포함합니다.
 * (3) Database : RoomDatabase를 확장하는 추상클래스여야 한다. 주석 내에 DB와 연결된 Entity의 목록을 포함해야 한다.
 *
 */
public class RoomDBActivity extends BaseActivity {

    private DatabaseRoomBinding binding;
    final String title = "Room 데이터 베이스";

    //RoomDatabase 불러오기
    private  StudentDB studentDB;

    // 현재 눌리는 버튼이 어떤버튼인지 알기위한 flag -> 1-insert 2-delete 3.update 4-load
    int flag;

    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DatabaseRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setToolbar(binding.layout.toolbar, binding.layout.toolbarImage, binding.layout.tooblarTitle, title);

        studentDB = StudentDB.getInstance(this);

        InsertRunnable insertRunnable = new InsertRunnable();

        binding.insertBtn.setOnClickListener(v -> {
            flag = 1;
            Thread t = new Thread(insertRunnable);
            t.start();
        });

        binding.deleteBtn.setOnClickListener(v ->{
            flag = 2;
            Thread t = new Thread(insertRunnable);
            t.start();

        });

        binding.updateBtn.setOnClickListener(v ->{
            flag = 3;
            Thread t = new Thread(insertRunnable);
            t.start();
        });


        //데이터 불로오기 버튼 누를떄
        binding.loadDataBtn.setOnClickListener(v -> {
            flag = 4;
            Thread t = new Thread(insertRunnable);
            t.start();
        });
    }

    class InsertRunnable implements Runnable {
        @Override
        public void run() {

            StringBuffer sb = new StringBuffer();
            if (flag == 1) {
                insertData();         // 데이터 베이스
            } else if (flag == 2) {
                delete();
            } else if (flag == 3) {
                sb  = upDate();
            } else if (flag == 4) {
                sb = loadData();
            }
            StringBuffer finalSb = sb;
            handler.post(() -> binding.dbResText.setText(finalSb.toString()));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        StudentDB.destroyInstance();
        studentDB = null;
    }

    public void insertData() {
        String name = binding.dbNameEdit.getText().toString();
        String ageStr = binding.dbAgeEdit.getText().toString();
        int age = ageStr.equals("") ? 0 : Integer.parseInt(ageStr);
        String major = binding.dbMajorEdit.getText().toString();

        Student std = new Student(name, age, major);
        studentDB.studentDao().insertAll(std);
    }


    public void delete(){
        String name = binding.dbNameEdit.getText().toString();
        studentDB.studentDao().deleteByName(name);
    }

    public StringBuffer upDate(){
        String name = binding.dbNameEdit.getText().toString();
        String ageStr = binding.dbAgeEdit.getText().toString();
        int age = ageStr.equals("") ? 0 : Integer.parseInt(ageStr);
        String major = binding.dbMajorEdit.getText().toString();
        List<Student> list = studentDB.studentDao().getDataByName(name);

        //-----------------
        StringBuffer sb = new StringBuffer();

        if(list.size() == 0){
            sb.append("아무 자료도 없습니다");
        }else{
            for (Student curStd : list) {
                curStd.setAge(age);
                curStd.setMajor(major);
                studentDB.studentDao().updateUser(curStd);
            }
            sb.append("데어터가 ").append(list.size()).append(" 개 지워졌습니다.");
        }
        return sb;
    }

    public StringBuffer loadData() {
        List<Student> list =  studentDB.studentDao().getAll();

        StringBuffer sb = new StringBuffer();

        if(list.size() == 0){
            sb.append("아무 자료도 없습니다");
        }else{
            for (Student curStd : list) {
                sb.append("id    : ").append(curStd.getId()).append("\n");
                sb.append("name  : ").append(curStd.getSName()).append("\n");
                sb.append("age   : ").append(curStd.getAge()).append("\n");
                sb.append("major : ").append(curStd.getMajor()).append("\n\n");
            }
        }

        return sb;
    }
}
