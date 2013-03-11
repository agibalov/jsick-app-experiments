package com.loki2302;

import org.junit.Test;
import static org.junit.Assert.*;

import com.loki2302.parser.Parser;
import com.loki2302.parser.ParserResult;

public class ParserTests {
    @Test
    public void parserEvenWorks() {    	
    	canParse("1;");
    	canParse("int x = 1;");
    	canParse("print(1);");
    	canParse("if (1) 1;");   
    	canParse("if (1) 1 else 2;");
    	canParse("for(;;);");
    	canParse("for(1;;);");
    	canParse("for(;1;);");
    	canParse("for(;;1);");
    	canParse("for(;;)1;");
    	canParse("for(1;1;1)1;");
    }
    	
    private static void canParse(String code) {
    	Parser parser = new Parser();
    	ParserResult parserResult = parser.parse(code);
    	assertTrue(parserResult.ok);
    }
}
