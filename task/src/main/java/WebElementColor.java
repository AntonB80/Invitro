public enum WebElementColor {

    clorRed("rgb(255, 255, 255) none repeat scroll 0% 0% / auto padding-box border-box");

    private String color;

    WebElementColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }
}
