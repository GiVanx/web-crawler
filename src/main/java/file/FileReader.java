package file;

import json.IJsonReader;

import java.io.*;

public class FileReader implements IFileReader {

    private IJsonReader jsonReader;

    public FileReader(IJsonReader jsonReader) {
        this.jsonReader = jsonReader;
    }

    public <T> T read(String filePath, Class outputClass) throws IOException {
        return jsonReader.read(new FileInputStream(filePath), outputClass);
    }
}
