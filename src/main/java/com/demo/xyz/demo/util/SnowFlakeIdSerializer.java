package com.demo.xyz.demo.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class SnowFlakeIdSerializer extends StdSerializer<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6905572988876172133L;

	/**
	 * Singleton instance to use.
	 */
	public final static SnowFlakeIdSerializer instance = new SnowFlakeIdSerializer();

	/**
	 * <p>
	 * Note: usually you should NOT create new instances, but instead use
	 * {@link #instance} which is stateless and fully thread-safe. However, there
	 * are cases where constructor is needed; for example, when using explicit
	 * serializer annotations like
	 * {@link com.fasterxml.jackson.databind.annotation.JsonSerialize#using}.
	 */
	public SnowFlakeIdSerializer() {
		super(Long.class);
	}

	@Override
	public void serialize(Long value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

		// 大于js的数值的最大值，则将类型转换为字符串
		if (value > 9007199254740991L) {

			log.debug("Convert long value:{} to String", value);
			gen.writeString(value.toString());

		} else {

			gen.writeNumber(value);
		}

	}

}
