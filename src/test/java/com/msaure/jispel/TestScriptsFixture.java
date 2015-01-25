package com.msaure.jispel;

public class TestScriptsFixture {

    public static String helloWorldClosure() {
        return ";; define a simple closure and try to apply it\n" +
            "(define testfunc (lambda (x) (display x)))\n" +
            "(testfunc \"hello, world\")";
    }
}
