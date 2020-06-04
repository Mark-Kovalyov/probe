package org.example;

import com.google.inject.Inject;

import java.io.FileWriter;
import java.io.Writer;

public class WriterServiceJson implements WriterService {

    public Writer writer = new FileWriter("json/members.json");



    @Override
    public void write(MemberInfo memberInfo) {

    }
}
