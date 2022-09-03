package AndroidBasic.DataBase.Realm;

import android.os.Bundle;
import com.aio.aio_android.BaseActivity;
import com.example.aio_android.databinding.DatabaseRealmBinding;
import io.realm.Realm;
import io.realm.RealmConfiguration;

import java.util.List;

/**
 * Realm 데이터베이스 예제
 * (1) app 레벨의 build.gradle에 플러그인 추가 -> id "realm-android"
 * (2) project 레벨의 build.gradle에 의존성 추가 -> classpath 'io.realm:realm-gradle-plugin:10.8.0'
 */
public class RealmActivity extends BaseActivity {

    private DatabaseRealmBinding mBinding;
    final String TITLE = "Realm 데이터베이스";

    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DatabaseRealmBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        setToolbar(mBinding.layout.toolbar, mBinding.layout.toolbarImage, mBinding.layout.tooblarTitle, TITLE);


        mRealm = Realm.getDefaultInstance();

        mBinding.insertBtn.setOnClickListener(v -> {
            insert();
        });

        // 이름기준으로 삭제
        mBinding.deleteBtn.setOnClickListener(v ->{
            delete();
        });

        // 모든 내용 삭
        mBinding.deleteAllBtn.setOnClickListener(v -> {
            deleteAll();
        });

        mBinding.updateBtn.setOnClickListener(v -> {
            update();
        });
        //데이터 불로오기 버튼 누를떄
        mBinding.loadDataBtn.setOnClickListener(v -> {
            mBinding.dbResText.setText(loadAllData().toString());
        });
    }


    //Realm에 객체(데이터) 저장
    private void insert(){
        String name = mBinding.dbNameEdit.getText().toString();
        String ageStr = mBinding.dbAgeEdit.getText().toString();
        int age = ageStr.equals("") ? 0 : Integer.parseInt(ageStr);
        String major = mBinding.dbMajorEdit.getText().toString();


        Number maxId = mRealm.where(StudentA.class).max("id");
        int nextId = (maxId == null) ? 1 : maxId.intValue() + 1;

        StudentA std = new StudentA(name, age, major);
        std.setId(nextId);
        // 2가지 방법
        // (1) 첫번쨰 방법
        mRealm.beginTransaction();
        StudentA realmStudent = mRealm.copyToRealm(std);
        mRealm.commitTransaction();

        // (2) 두번째 방법

    }

    public void delete(){
        String name = mBinding.dbNameEdit.getText().toString();
        mRealm.beginTransaction();
        mRealm.where(StudentA.class).equalTo("Sname", name).findAll().deleteAllFromRealm();
        mRealm.commitTransaction();
    }

    private void deleteAll(){
        mRealm.beginTransaction();
        mRealm.where(StudentA.class).findAll().deleteAllFromRealm();
        mRealm.commitTransaction();

//        executeTransaction을 쓰려면 (a) UI Thread에서 쓰기 않던가 (b) .allowWritesOnUiThread(true)를 RealmDB에 추가
//       mRealm.executeTransaction(realm -> realm.delete(StudentA.class));
    }

    private void update(){
        String name = mBinding.dbNameEdit.getText().toString();
        String ageStr = mBinding.dbAgeEdit.getText().toString();
        int age = ageStr.equals("") ? 0 : Integer.parseInt(ageStr);
        String major = mBinding.dbMajorEdit.getText().toString();

        mRealm.beginTransaction();
        StudentA std = mRealm.where(StudentA.class).equalTo("Sname", name).findFirst();
        std.setSName(name);
        std.setAge(age);
        std.setMajor(major);
        mRealm.commitTransaction();
    }

    private StringBuffer loadAllData(){
        List<StudentA> list =  mRealm.where(StudentA.class).findAll();
        StringBuffer sb = new StringBuffer();

        if(list.size() == 0){
            sb.append("아무 자료도 없습니다");
        }else{
            for (StudentA curStd : list) {
                sb.append("id    : ").append(curStd.getId()).append("\n");
                sb.append("name  : ").append(curStd.getSName()).append("\n");
                sb.append("age   : ").append(curStd.getAge()).append("\n");
                sb.append("major : ").append(curStd.getMajor()).append("\n\n");
            }
        }
        return sb;
    }

//    private boolean deleteRealm(){
////        mRealm.de
//    }
}