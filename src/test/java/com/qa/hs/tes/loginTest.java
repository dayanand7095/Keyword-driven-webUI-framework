package com.qa.hs.tes;

import org.testng.annotations.Test;

import com.qa.hs.keyword.engine.KeyWordEngine;

public class loginTest {
    public KeyWordEngine keyWordEngine;
@Test
public void LoginTest(){
    keyWordEngine=new KeyWordEngine();
    keyWordEngine.startExecution("login");
}

}
