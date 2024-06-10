public class AnalizesPaga {
    private int priceFromAnalizesPageInt;
    private int totalPriceFromCartInt;

    /**
     * - Есть две переменные: priceFromAnalizesPageInt (сохраненная сумма) и
     * totalPriceFromCartInt (сумма в корзине)
     * - Исходя из условий больше\меньше\равно выводится соответсвующее сообщение в
     * консоль (больше или меньше) или вызвать ошибку, если равно
     * 
     * @param priceFromAnalizesPageInt Цена со страницы услуги Анализы
     * @param totalPriceFromCartInt    Итоговая цена со страницы Корзина
     */

    public AnalizesPaga(int priceFromAnalizesPageInt, int totalPriceFromCartInt) {

        this.priceFromAnalizesPageInt = priceFromAnalizesPageInt;
        this.totalPriceFromCartInt = totalPriceFromCartInt;

        if (totalPriceFromCartInt > priceFromAnalizesPageInt) {
            System.out.println("Сумма в корзине больше сохраненной суммы");

        } else if (totalPriceFromCartInt < priceFromAnalizesPageInt) {
            System.out.println("Сумма в корзине меньше сохраненной суммы");
        } else {
            throw new AssertionError("Сумма в корзине равна сохраненной сумме, что не должно быть");
        }
    }

    public int getPriceFromAnalizesPageInt() {
        return priceFromAnalizesPageInt;
    }

    public void setPriceFromAnalizesPageInt(int priceFromAnalizesPageInt) {
        this.priceFromAnalizesPageInt = priceFromAnalizesPageInt;
    }

    public int getTotalPriceFromCartInt() {
        return totalPriceFromCartInt;
    }

    public void setTotalPriceFromCartInt(int totalPriceFromCartInt) {
        this.totalPriceFromCartInt = totalPriceFromCartInt;
    }

}
