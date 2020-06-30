### How does the crawler function?

The crawler leverages the Google's [Custom Search Json API](https://developers.google.com/custom-search/v1/introduction). 
By calling the API the crawler issues requests against an existing Custom Search Engine (the _API key_ and the 
_Search Engine Id_ can be found in the `GoogleSearcher` class). The API provides the response in JSON format. It will
contain also the relevant links.

The crawler (`WebCrawler`) distributes the processing of the response links to a number of workers (`ICrawlerWorker`) 
(each worker will be responsible for processing one page).

The crawler worker (`ICrawlerWorker`) will read the html of the URL, given as input, and count the occurrences
of each library. Using the `TechnologyAnalizer` class it is able to determine the name of the library when given the
value of the `src` attribute of a `script` tag as input (for more details see the next paragraph). 

The `WebCrawler` will wait for the completion of all of the workers (`ICrawlerWorker`). Upon completion, it will sum up the 
count of libraries computed by each worker and then output the top 5 most frequently used Javascript libraries.

### Library name determination. Deduplication of libraries with the same name

The deduplication algorithm is based on regex patterns. The application loads a list of script regex patterns from the
`apps.json` file (located in project root). One library name may be associated with one or more regex patterns. 
Each `script/src` attribute is then matched with all of the known script regex patterns and the actual name of
the library is determined.

The `apps.json` file was inspired from the open source project [AliasIO/wappalyzer](https://github.com/AliasIO/wappalyzer).
The original `apps.json` could not be used in the current application. In each of the `apps.script` regex patterns 
the ending `\\;version:\1` had to be removed. 

##### Example:

`"A-Frame": {"cats": [25], "script": "/?([\\d.]+)?/aframe(?:\\.min)?\\.js\\;version:\\1"}`

was changed to:

`"A-Frame": {"cats": [25],"script": "/?([\\d.]+)?/aframe(?:\\.min)?\\.js"}`

### Additional

* [Class diagram](https://github.com/GiVanx/web-crawler/blob/master/docs/class-diagram.pdf) - the diagram was built using 
[Online Visual Paradigm](https://online.visual-paradigm.com/).