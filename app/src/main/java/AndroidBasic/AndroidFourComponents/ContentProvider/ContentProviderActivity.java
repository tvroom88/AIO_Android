package AndroidBasic.AndroidFourComponents.ContentProvider;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.aio.aio_android.BaseActivity;
import com.example.aio_android.R;
import com.example.aio_android.databinding.ContentProviderBinding;

/**
 *  Content Provider 예제
 *  (1) MyContentProvider.java : ContentProvider과 sqlite 부분 구현되어있음.
 *  (2) ContentProviderActivity : getContentResolver를 통해 ContentProvider로 접근해서
 *      insert, query, update, delete 사용방법 구현
 */
public class ContentProviderActivity extends BaseActivity {

    Uri CONTENT_URI = Uri.parse("content://com.demo.user.provider/users");

    final String title = "Content Provider 에제";
    private ContentProviderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ContentProviderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setToolbar(binding.layout.toolbar, binding.layout.toolbarImage, binding.layout.tooblarTitle, title);

        binding.insertButton.setOnClickListener(this::insertUser);
        binding.loadButton.setOnClickListener(this::loadData);
        binding.updateButton.setOnClickListener(this::updateUser);
        binding.removeAllButton.setOnClickListener(this::removeAll);
        binding.removeOneButton.setOnClickListener(this::removeOne);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }

    // 데이터 저장하기
    public void insertUser(View view) {
        String str = binding.textName.getText().toString();
        if(str.equals("")){
            binding.res.setText("EditText에 이름을 적어주세요");
        }else{
            ContentValues values = new ContentValues();
            values.put(MyContentProvider.name, str);
            getContentResolver().insert(CONTENT_URI, values);
            binding.res.setText("새로운 기록이 잘 들어갔습니다 - " + str);
        }
    }

    //모든 데이터 지우기
    public void removeAll(View view) {
        // 2번째는 where라서 null 처리 하면 모든 데이터 지우는걸로 됨.
        getContentResolver().delete(MyContentProvider.CONTENT_URI, null, null);
        binding.res.setText("모든 내용이 잘 지워졌습니다.");
    }

    // 유저 데이터 하나 지우기
    public void removeOne(View view) {
        String str = binding.textName.getText().toString();
        if(str.equals("")){
            binding.res.setText("EditText에 이름을 적어주세요");
        }else {
            //지울 특성 고리는 부분 : (1)id (2)name 중에 여기서는 name 고름
            String selection = "name = ?";
            String nameStr = ((EditText) findViewById(R.id.textName)).getText().toString();
            String[] selectionArgs = new String[]{nameStr};
            int count = getContentResolver().delete(CONTENT_URI, selection, selectionArgs);
            binding.res.setText("delete 결과 : " + count);
        }
    }

    // 유저 데이터 업데이트하기. 맞는 데이터가 없다면 업데이트 안함.
    public void updateUser(View view){
        String str = binding.textName.getText().toString();
        if(str.equals("")){
            binding.res.setText("EditText에 이름을 적어주세요");
        }else{
            String selection = "name = ?";
            String[] strArr = str.split(" ");

            if(strArr.length != 2){
                binding.res.setText("지울이름과 새로운이름을 띄어쓰기를 포함해 적어주세요. 앞에 단어는 지울단어이고 뒤에 단어는 새로운 단어입니다");
            }else{
                String[] selectionArgs = new String[] {strArr[0]};
                ContentValues updateValue = new ContentValues();
                updateValue.put(MyContentProvider.name, strArr[1]);
                int count = getContentResolver().update(CONTENT_URI, updateValue, selection, selectionArgs);
                binding.res.setText("update 결과 : " + count);
            }
        }
    }

    // 저장되어있는 모든 데이터 불러오기
    @SuppressLint("Range")
    public void loadData(View view) {
        Cursor cursor = getContentResolver().query(CONTENT_URI, null, null, null, null);

        if(cursor.moveToFirst()) {
            StringBuilder strBuild=new StringBuilder();
            while (!cursor.isAfterLast()) {
                String id = cursor.getString(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                strBuild.append("\n").append(id).append("-").append(name);
                cursor.moveToNext();
            }
            binding.res.setText(strBuild);
        }
        else {
            binding.res.setText("기록이 없습니다");
        }
    }
}