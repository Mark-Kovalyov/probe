# Google API-s

## Google Custom Search

* https://developers.google.com/custom-search/
* Using REST to invoke API https://developers.google.com/custom-search/v1/using_rest
* https://developers.google.com/custom-search/v1/site_restricted_api
* https://developers.google.com/custom-search/v1/reference/rest/v1/cse/list

Performance tips
* https://developers.google.com/custom-search/v1/performance

Making request
```
GET https://www.googleapis.com/customsearch/v1/siterestrict?[parameters]
```
Parameters:
* API key - Use the key query parameter to identify your application.
* Programmable Search Engine ID - Use cx to specify the Programmable Search Engine you want to use to perform this search. The search engine must be created with the Control Panel Note: The Search Engine ID (cx) can be of different format (e.g. 8ac1ab64606d234f1)
* Search query - Use the q query parameter to specify your search expression.

```
    // "template": "https://www.googleapis.com/customsearch/v1?q={searchTerms}&num={count?}&start={startIndex?}
    //                     &lr={language?}&safe={safe?}&cx={cx?}&sort={sort?}&filter={filter?}&gl={gl?}
    //                     &cr={cr?}&googlehost={googleHost?}&c2coff={disableCnTwTranslation?}&hq={hq?}
    //                     &hl={hl?}&siteSearch={siteSearch?}
    //                     &siteSearchFilter={siteSearchFilter?}
    //                     &exactTerms={exactTerms?}&excludeTerms={excludeTerms?}
    //                     &linkSite={linkSite?}&orTerms={orTerms?}&relatedSite={relatedSite?}
    //                     &dateRestrict={dateRestrict?}&lowRange={lowRange?}&highRange={highRange?}
    //                     &searchType={searchType}&fileType={fileType?}&rights={rights?}&imgSize={imgSize?}
    //                     &imgType={imgType?}&imgColorType={imgColorType?}&imgDominantColor={imgDominantColor?}
    //                     &alt=json"
```