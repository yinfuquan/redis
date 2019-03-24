/**
 * 
 */
package com.example.first.lang.json;

import com.google.gson.*;
import com.example.first.lang.utils.EnumStatus;
import org.apache.commons.lang3.StringUtils;
import java.lang.reflect.Type;

/**
 * @author Bean
 * 枚举类型在Gson中定制序列化适配
 */
public abstract class EnumSerializer<T extends EnumStatus> implements JsonSerializer<T>, JsonDeserializer<T>  {

	public EnumSerializer() {
	}

	@Override
	public JsonElement serialize(T t, Type type, JsonSerializationContext context) {
		EnumStatus status = (EnumStatus)t;
		return new JsonPrimitive(status.getValue());
	}

	@Override
	public T deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
		return doSerialize((int)json.getAsInt());
	}	
	
	public abstract T doSerialize(int value); 
}
