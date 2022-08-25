package AndroidBasic.Network.Retrofit2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @SerialzedNameJSON으로 serialize 될 때 매칭되는 이름을 명시하는 목적으로 사용되는 field 마킹 어노테이션이다.
 * @Exposeobject 중 해당 값이 null일 경우, json으로 만들 필드를 자동 생략해 준다.
 * 출처: https://giyatto.tistory.com/92 [행복은 습관입니다.:티스토리]
 */
public class  data_model {
    @SerializedName("userId")
    @Expose
    private String userId;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("title")
    @Expose
    private String title;

//    @SerializedName("body")
//    @Expose
//    private String body;


    public String getUserId(){
        return userId;
    }

    public String getID(){
        return id;
    }

    public String getTitle(){
        return title;
    }

//    public String getBody(){
//        return body;
//    }
}
