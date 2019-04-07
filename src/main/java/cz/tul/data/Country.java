package cz.tul.data;




public class Country {

    private String code;
    private String name;

    public Country() {

    }

    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Country[code=" + this.code + ", name=" + this.name + "]";
    }


}
