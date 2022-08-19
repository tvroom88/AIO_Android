package AndroidBasic.AndroidFourComponents.Service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.example.aio_android.R;

public class ServiceA extends Service {

    CheckPackageNameThread checkPackageNameThread;
    NotificationCompat.Builder notification;
    NotificationManager mNotificationManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // PendingIntent를 이용하면 포그라운드 서비스 상태에서 알림을 누르면 앱의 MainActivity를 다시 열게 된다.
        Intent testIntent = new Intent(getApplicationContext(), ServiceActivity.class);
        testIntent.putExtra("flag", 0);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, testIntent, PendingIntent.FLAG_IMMUTABLE);


        // Yes를 누를 경우
        Intent yesIntent = new Intent(getApplicationContext(), ServiceActivity.class);
        yesIntent.putExtra("flag", 1);
        PendingIntent yesPendingIntent = PendingIntent.getActivity(this, 0, yesIntent, PendingIntent.FLAG_IMMUTABLE);


        // No를 누를 경우
        Intent noIntent = new Intent(getApplicationContext(), ServiceActivity.class);
        noIntent.putExtra("flag", 2);
        PendingIntent noPendingIntent = PendingIntent.getActivity(this, 0, noIntent, PendingIntent.FLAG_IMMUTABLE);

        // Dismiss Button
        Intent buttonIntent = new Intent(getApplicationContext(), AutoDismissReceiver.class);
        buttonIntent.putExtra("flag",3);
        PendingIntent btPendingIntent = PendingIntent.getBroadcast(this, 0, buttonIntent,PendingIntent.FLAG_IMMUTABLE);

        // 오래오 윗버젼일 때는 아래와 같이 채널을 만들어 Notification과 연결해야 한다.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("channel", "play!!",
                    NotificationManager.IMPORTANCE_DEFAULT);

            // Notification과 채널 연걸
            mNotificationManager = ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE));
            mNotificationManager.createNotificationChannel(channel);

            // Notification 세팅
            notification = new NotificationCompat.Builder(getApplicationContext(), "channel")
                    .setSmallIcon(R.drawable.book)
                    .setContentTitle("포어그라운드로 실행중")
                    .setContentIntent(pendingIntent)
                    .setContentText("포어그라운드로 실행중입니다..")
                    .setAutoCancel(true)
                    .addAction(R.drawable.android_example_right, "Yes", yesPendingIntent)
                    .addAction(R.drawable.android_example_right, "No", noPendingIntent);
//                    .addAction(R.drawable.android_example_right, "Dismiss", btPendingIntent);

            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
            // id 값은 0보다 큰 양수가 들어가야 한다.
            mNotificationManager.notify(1, notification.build());
            // foreground에서 시작

            startForeground(1, notification.build());
        }

        // 이전 포스트에서 패키지 이름을 2초마다 가져오는 스레드를 서비스에서 실행해준다. 서비스가 실행되면 이 스레드도 같이 실행된다.
//        checkPackageNameThread = new CheckPackageNameThread();
//        checkPackageNameThread.start();

        return START_STICKY;
    }

    private class CheckPackageNameThread extends Thread{

        public void run(){
            while(true){
                // runOnUiThread를 이용해 UI 스레드에 해당 작업을 큐에 넣어 알림의 내용 UI를 변경할 수 있게 해준다.
                ServiceActivity.mActivity.runOnUiThread(() -> {
//                        notification.setContentText(getPackageName(getApplicationContext()));
                    notification.setContentText("notification");
                    mNotificationManager.notify(1, notification.build());
                });
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
