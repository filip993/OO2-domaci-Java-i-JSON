package json;


public class Country {

    private String Name;
    private String Code;


    public Country() {
        Name = "";
        Code = "";
    }


    public String getCode() {
        return Code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setCode(String code) {
        this.Code = code;
    }

    @Override
    public String toString() {
        return Name;
    }
}
