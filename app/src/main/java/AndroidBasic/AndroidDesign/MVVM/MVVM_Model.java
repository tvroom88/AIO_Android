package AndroidBasic.AndroidDesign.MVVM;

public class MVVM_Model {

    private String text;

    MVVM_Model(){
        text = "아직 적은 내용이 없습니다";
    }

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }

}
