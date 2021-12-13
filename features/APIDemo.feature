Feature: Library cards

  @API
  @issue=<BUG-100>
  @tmsLink=<CSBP-100>
  @severity=blocker
  Scenario Outline: An employee can create, search, update and delete library cards
    Given Library card with fullname "<fullname>" doesn't exist yet
    When An employee creates library card
    Then Library card can be deleted

    Examples:
    | fullname      |
    | Doe John      |
    | Alice Johnson |