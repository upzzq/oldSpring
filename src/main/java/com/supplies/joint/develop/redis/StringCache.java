package com.supplies.joint.develop.redis;

import java.nio.charset.Charset;

public abstract class StringCache<V> implements ICache<String, V> {

    protected Charset charset = Charset.forName("UTF-8");

}
