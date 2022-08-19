package AndroidBasic.AndroidFourComponents.BroadcastReceiver;

import android.content.Intent;
import android.os.Bundle;
import com.example.aio_android.BaseActivity;
import com.example.aio_android.databinding.BroadcastSmsBinding;

public class Broadcast_SMS_Activity extends BaseActivity {

    final String title = "BroadCast Receiver 예제";
    private BroadcastSmsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = BroadcastSmsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setToolbar(binding.layout.toolbar, binding.layout.toolbarImage, binding.layout.tooblarTitle, title);

        binding.button.setOnClickListener(view -> finish());

        Intent passedIntent = getIntent();
        processIntent(passedIntent);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        processIntent(intent);
        super.onNewIntent(intent);
    }

    private void processIntent(Intent intent) {
        if (intent != null) {
            String sender = intent.getStringExtra("sender");
            String contents = intent.getStringExtra("contents");
            String receivedDate = intent.getStringExtra("receivedDate");

            binding.editText.setText(sender);
            binding.editText2.setText(contents);
            binding.editText3.setText(receivedDate);
        }
    }
}