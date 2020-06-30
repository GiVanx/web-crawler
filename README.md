#### Deduplication of libraries with the same name

The deduplication algorithm is based on regex patterns. The application loads a list of script regex patterns from the
`apps.json` file (located in project root). One library name may be associated with one or more regex patterns. 
Each `script/src` tag is then matched with all of the known script regex patterns and the actual name of
the library is determined.

The `apps.json` file was inspired from the open source project [AliasIO/wappalyzer](https://github.com/AliasIO/wappalyzer).
The original `apps.json` could not be used in the current application.
Thus, in each of the `apps.script` regex patterns the ending `\\;version:\1` was removed. 

##### Example:

`"A-Frame": {"cats": [25], "script": "/?([\\d.]+)?/aframe(?:\\.min)?\\.js\\;version:\\1"}`

was changed to:

`"A-Frame": {"cats": [25],"script": "/?([\\d.]+)?/aframe(?:\\.min)?\\.js"}`