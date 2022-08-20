package AndroidBasic.IntentAndBundle;

import android.os.Parcel;
import android.os.Parcelable;

public class SimpleData implements Parcelable {

    int number;
    String message;
    String message2;

    public SimpleData(int num, String msg, String msg2) {
        number = num;
        message = msg;
        message2 = msg2;
    }

    // 받았을때도 데이터를 받아와야 하기때문에 이부분 추가
    public SimpleData(Parcel src) {
        number = src.readInt();
        message = src.readString();
        message2 = src.readString();
    }

    //Creator가 없다면 데이터를 넘기게 되더라고 Creator가 없다는 Exception을 뿌려주기 때문에 필히 작성을 해줘야 하는 부분
    public static final Creator CREATOR = new Creator() {

        public SimpleData createFromParcel(Parcel in) {
            return new SimpleData(in);
        }

        public SimpleData[] newArray(int size) {
            return new SimpleData[size];
        }

    };

    public int describeContents() {
        return 0;
    }

    // writeToParcel은 객체가 직렬화되어 보내지기 이전에 데이터를 직렬화시켜주는 메소드
    // dest에 순차적으로 Class 내부에 있는 데이터들을 저장시켜놓습니다.
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(number);
        dest.writeString(message);
        dest.writeString(message2);
    }

}