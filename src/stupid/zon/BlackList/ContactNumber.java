package stupid.zon.BlackList;

/**
 * Created by Zon on 15/10/2015.
 */
public class ContactNumber {
    private String name;
    private String number;
    private String type;
    private boolean isOn = false;

    public boolean isOn() {
        return isOn;
    }

    public void setIsOn(boolean isOn) {
        this.isOn = isOn;
        this.type = isOn ? "YES" : "NO";
    }

    public ContactNumber() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ContactNumber(String name, String number, String type) {
        this.name = name;
        this.number = number;
        this.type = type;
        this.isOn = (type.equals("YES"));
    }
}
