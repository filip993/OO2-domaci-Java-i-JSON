package json;

public class Cities {
    private String name;
    private String country;
    private String subcountry;
    private int geonamesid;

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getSubcountry() {
        return subcountry;
    }

    public int getGeonamesid() {
        return geonamesid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setSubcountry(String subcountry) {
        this.subcountry = subcountry;
    }

    public void setGeonamesid(int geonamesid) {
        this.geonamesid = geonamesid;
    }

    public String formatString(String s) {
        s.replaceAll("\\s","");
        return s;
    }

    @Override
    public String toString() {
        return name;
    }


}
