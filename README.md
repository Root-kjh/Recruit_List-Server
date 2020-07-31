# Docs

## User

1. Signin(/user/signin)

* POST : userName, password

2. Signup(/user/signup)

* POST : email, userName, password

3. edit_user_info(/user/edit_user_info)

* header(X-AUTH-TOKEN) : jwt
* POST : newUserName, newEmail

4. edit_password(/user/edit_password)

* header(X-AUTH-TOKEN) : jwt
* POST : newPassword

5. withdraw(/user/withdraw)

* header(X-AUTH-TOKEN) : jwt
* DELETE : password

6. getLikeCompany(/user/get_like_company)

* header(X-AUTH-TOKEN) : jwt

7. addLikeCompany(/user/add_like_company)

* header(X-AUTH-TOKEN) : jwt
* PUT : companyId

8. deleteLikeCompany(/user/delete_like_company)

* header(X-AUTH-TOKEN) : jwt
* DELETE : companyId

## Company

1. showCompany(/company/show/{page})

* GET

2. searchCompany(/company/search/{companyName}/{page})

* GET

3. filterCompany(/company/filter)

* POST : isRecruting, foundingYear, employeesNum, page