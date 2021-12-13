# library-api-tests

Demo json db: /about/db.json

Library emplyee is able to:
1. Create new library card;
2. Update library card (only fullname);
3. Delete library card;
4. Search library card by:
   - id;
   - reader fullname;

Test:
1. Employee works with library cards (LC) (data driven test)
- Search LC: Empty result
- Create new LC: Successful
- Update fullname: Successful
- Search LC: Library card is found by new fullname
- Delete LC: Successful
- Search LC: Empty result

(!) My goal is not to show my test design skills, but to show my ability to create autotests for API testing

Developed:
- UI/API autotest project (JAVA + Cucumber + Selenide/Rest Assured);
- Allure report with attachments (see short overview in /about/AllureShortOverview.gif).