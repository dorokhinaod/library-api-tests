Feature: Browser
  User can open browser

  @UI
  @issue=<BUG-100>
  @Link=http://yandex.ru
  @tmsLink=<CSBP-100>
  @severity=blocker
  Scenario: User opens browser firstly
    When User opens browser
    Then User can open library cards page

  @UI
  @issue=<BUG-101>
  @Link=http://yandex.ru
  @tmsLink=<CSBP-101>
  @severity=normal
  Scenario: User opens browser second time
    When User opens browser
    Then User can open library cards page
