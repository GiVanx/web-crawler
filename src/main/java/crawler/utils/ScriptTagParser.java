package crawler.utils;

public class ScriptTagParser implements IScriptTagParser {
    @Override
    public String getJsLibraryName(String srcAttributeValue) {
        System.out.println(srcAttributeValue);
        String res = srcAttributeValue.substring(srcAttributeValue.lastIndexOf('/') + 1);

        int dotIndex = res.indexOf('.');
        if (dotIndex >= 0) {
            res = res.substring(0, res.indexOf('.'));
        }
        return res;
    }
}
