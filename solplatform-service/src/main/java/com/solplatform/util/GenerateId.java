package com.solplatform.util;

import java.util.UUID;

/**
 * @author sol
 * @create 2020-04-28  23:38
 */
public class GenerateId {

    //得到32位的uuid
    public static String getUUID32() {

        return UUID.randomUUID ().toString ().replace ("-", "").toLowerCase ();

    }

}
