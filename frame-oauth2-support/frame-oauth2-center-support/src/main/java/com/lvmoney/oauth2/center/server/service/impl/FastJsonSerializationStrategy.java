package com.lvmoney.oauth2.center.server.service.impl;

import com.lvmoney.redis.converter.FrameFastJsonRedisSerializer;
import org.springframework.security.oauth2.provider.token.store.redis.StandardStringSerializationStrategy;

public class FastJsonSerializationStrategy extends StandardStringSerializationStrategy {
    private static final FrameFastJsonRedisSerializer OBJECT_SERIALIZER = new FrameFastJsonRedisSerializer();

    public FastJsonSerializationStrategy() {
    }

    protected <T> T deserializeInternal(byte[] bytes, Class<T> clazz) {
        return (T) OBJECT_SERIALIZER.deserialize(bytes);
    }

    protected byte[] serializeInternal(Object object) {
        return OBJECT_SERIALIZER.serialize(object);
    }
}