# Docs

* CompanyInfo
    * companyName
    * foundingYear
    * employeesNum
    * companyInfoSiteList
    * recruitListNoticeList

* companyInfoSite
    * siteName
    * uri

* recruitListNoticeList
    * siteName
    * uri

* UserInfo
    * pk
    * userName
    * jwt

## User

1. Signin(/auth/signin)
    * Request(POST)
        * Json: userName, password
    * Response: UserInfo

2. Signup(/auth/signup)
    * Request(POST)
        * Json: email, userName, password
    * Response: Success/ Fail

3. EditUserInfo(/user/{userId})
    * Request(PUT)
        * Auth: jwt
        * Json: userName, email
    * Response: UserInfo

4. EditPassword(/user/{userId})
    * Request(PATCH)
        * Auth: jwt
        * Json: password
    * Response: Success/ Fail

5. Withdraw(/user/{userId})
    * Request(DELETE)
        * Auth: jwt

6. GetLikeCompany(/user/{userId}/companies)
    * Request(GET)
        * Auth: jwt
    * Response: CompanyInfoList

7. AddLikeCompany(/user/{userId}/companies/{companyId})
    * Request(POST)
        * Auth: jwt
    * Response: Success/ Fail

8. DeleteLikeCompany(/user/{userId}/companies/{companyId})
    * Request(DELETE)
        * AUTH: jwt
    * Response: Success/ Fail

## Company

1. ShowCompany(/company/page/{page})
    * Request(GET)
    * Response: CompanyInfoList

2. SearchCompany(/company/companyName/{companyName}/page/{page})
    * Request(GET)
    * Response: CompanyInfoList

3. FilterCompany(/company/filter)
    * Request(POST)
    * Json: isRecruting, foundingYear, employeesNum, page
    > 파라미터의 개수가 너무 많아 PathParameter로 처리하기에 적절하지 않다고 판단.
    * Response: CompanyInfoList
