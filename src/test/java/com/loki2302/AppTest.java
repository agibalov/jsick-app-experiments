package com.loki2302;

import org.junit.Test;
import static org.junit.Assert.*;

import com.loki2302.parser.Parser;
import com.loki2302.parser.ParserResult;

public class AppTest {
    @Test
    public void dummy() {    	
    	assertOk("1;");
    	assertOk("int x = 1;");
    	assertOk("print(1);");
    	assertOk("if (1) 1;");   
    	assertOk("if (1) 1 else 2;");
    }
    	
    private static void assertOk(String code) {
    	Parser parser = new Parser();
    	ParserResult parserResult = parser.parse(code);
    	assertTrue(parserResult.ok);
    }
}
