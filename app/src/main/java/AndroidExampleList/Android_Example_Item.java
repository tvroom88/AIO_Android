package AndroidExampleList;

public class Android_Example_Item {

    private final int number;
    private final String title;
    private final boolean isContent;

    public Android_Example_Item(int num, String title, boolean isContent){
        this.number = num;
        this.title = title;
        this.isContent = isContent;
    }

    public int getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public boolean isContent() {
        return isContent;
    }

}
