# 4. создать шаг на запоминание суммы продукта на странице анализы
# https://www.invitro.ru/analizes/for-doctors/,
# добавить продукт в корзину со страницы анализы
# https://www.invitro.ru/analizes/for-doctors/,
# создать шаг сравнивающий сумму в корзине с запомненной суммой. Исходя из
# условий больше\меньше\равно 10000р вывести сообщение в консоль (больше или
# меньше) или вызвать ошибку, если равно. Реализовать передачу запомненной
# суммы между шагами
Feature: Comparison of the price

  Scenario Outline: Comparison of the price
    Given Open analysis page "https://www.invitro.ru/analizes/for-doctors/"
    When Take the cost analysis 'Glycated hemoglobin' and added to cart
    And Going to the cart
    And Take the cost of analysis 'Glycated hemoglobin'
    Then Comparison the prices of the analysis in the cart with the price of the analysis from the analysis page

# 5. создать параметризованный шаг
# When Ввести код анализа в поле поиска 1515
# где вместо "1515" можно указать любое значение и данное значение будет указываться в поисковую строку

Feature: Entering the INI code
  Scenario Outline: Entering the INI code
    Given Open analysis page "https://lk3.invitro.ru/no-registration-results"
    When Enter code analysis in the search field <code>

    Examples:
      | code   |
      | 1515   |
      | 333333 |
      | 3377   |